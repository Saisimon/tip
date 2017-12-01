package net.saisimon.config;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Configuration
@PropertySource("classpath:https.properties")
@EnableConfigurationProperties(TomcatWebConfig.SslProperties.class)
@ConditionalOnClass(Connector.class)
@ConditionalOnProperty(prefix = "mail.service.https", name = "enabled", havingValue = "true", matchIfMissing = true)
public class TomcatWebConfig {

	@Bean
	public EmbeddedServletContainerFactory servletContainer(SslProperties properties) {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
			}
		};
		tomcat.addAdditionalTomcatConnectors(createSslConnector(properties));
		return tomcat;
	}

	private Connector createSslConnector(SslProperties properties) {
		Connector connector = new Connector();
		properties.configureConnector(connector);
		return connector;
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

		public void configureConnector(Connector connector) {
			if (port != null) {
				connector.setPort(port);
			}
			if (secure != null) {
				connector.setSecure(secure);
			}
			if (scheme != null) {
				connector.setScheme(scheme);
			}
			if (ssl != null) {
				connector.setProperty("SSLEnabled", ssl.toString());
			}
			if (StringUtils.isNotBlank(keystore)) {
				File keystoreFile = new File(keystore);
				if (keystoreFile.exists()) {
					connector.setProperty("keystoreFile", keystoreFile.getAbsolutePath());
					connector.setProperty("keystorePass", keystorePassword);
				}
			}
		}
	}

}
