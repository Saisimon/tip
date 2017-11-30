package net.saisimon.config;

import java.io.File;

import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.security.Constraint;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Configuration
@PropertySource("classpath:https.properties")
@EnableConfigurationProperties(JettyWebConfig.SslProperties.class)
public class JettyWebConfig {

	@Bean
	public EmbeddedServletContainerFactory servletContainer(SslProperties properties) {
		JettyEmbeddedServletContainerFactory jetty = new JettyEmbeddedServletContainerFactory() {
			@Override
			protected void postProcessWebAppContext(WebAppContext webAppContext) {
				// 所有请求都必须为 Https 协议
				ConstraintSecurityHandler securityHandler = new ConstraintSecurityHandler();
				ConstraintMapping mapping = new ConstraintMapping();
				Constraint constraint = new Constraint();
				constraint.setDataConstraint(Constraint.DC_CONFIDENTIAL);
				mapping.setConstraint(constraint);
				mapping.setPathSpec("/*");
				securityHandler.addConstraintMapping(mapping);
				webAppContext.setSecurityHandler(securityHandler);
			}
		};
		jetty.addServerCustomizers(new JettyServerCustomizer() {
			@Override
			public void customize(Server server) {
				// 移除Spring Boot 生成的 Connector
				int httpPort = 80;
				Connector[] connectors = server.getConnectors();
				for (Connector connector : connectors) {
					if (connector instanceof ServerConnector) {
						httpPort = ((ServerConnector) connector).getPort();
					}
					server.removeConnector(connector);
				}
				
				// 配置 Http 协议的 Connector
				HttpConfiguration httpConfig = new HttpConfiguration();
				// 重定向
				httpConfig.setSecureScheme(properties.getScheme());
				httpConfig.setSecurePort(properties.getPort());
				httpConfig.addCustomizer(new SecureRequestCustomizer());
				ServerConnector httpConnector = new ServerConnector(server, new HttpConnectionFactory(httpConfig));
				httpConnector.setPort(httpPort);
				server.addConnector(httpConnector);

				// 配置 Https 协议的 Connector
				HttpConfiguration httpsConfig = new HttpConfiguration(httpConfig);
				httpsConfig.addCustomizer(new SecureRequestCustomizer());
				HttpConnectionFactory connectionFactory = new HttpConnectionFactory(httpsConfig);
				SslContextFactory sslContextFactory = new SslContextFactory();
				File keystoreFile = new File(properties.getKeystore());
				if (keystoreFile.exists()) {
					sslContextFactory.setKeyStorePath(properties.getKeystore());
					sslContextFactory.setKeyStorePassword(properties.getKeystorePassword());
				}
				SslConnectionFactory sslConnectionFactory = new SslConnectionFactory(sslContextFactory,
						HttpVersion.HTTP_1_1.asString());
				ServerConnector serverConnector = new ServerConnector(server, sslConnectionFactory, connectionFactory);
				serverConnector.setPort(properties.getPort());
				server.addConnector(serverConnector);
			}
		});
		return jetty;
	}

	@ConfigurationProperties(prefix = "https")
	@Data
	public static class SslProperties {

		private Integer port;
		private Boolean ssl = true;
		private Boolean secure = true;
		private String scheme = "https";
		private String keystore;
		private String keystorePassword;

	}

}
