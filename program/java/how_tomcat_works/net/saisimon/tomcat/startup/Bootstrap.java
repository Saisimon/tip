package net.saisimon.tomcat.startup;

import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Loader;
import org.apache.catalina.Logger;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.Wrapper;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.core.StandardService;
import org.apache.catalina.loader.WebappLoader;

import net.saisimon.tomcat.connector.HttpConnector;
import net.saisimon.tomcat.core.SimpleContextConfig;
import net.saisimon.tomcat.core.SimpleLogger;
import net.saisimon.tomcat.core.SimpleWrapper;

public final class Bootstrap {
	
	public static void main(String[] args) {
		System.setProperty("catalina.base", System.getProperty("user.dir"));
		
		// 配置连接器
		HttpConnector connector = new HttpConnector();
		connector.setMinProcessors(1);
		
		// 配置包装器
		Wrapper wrapper1 = new SimpleWrapper();
		wrapper1.setServletClass("HelloServlet");
		wrapper1.setName("Hello");
		
		Wrapper wrapper2 = new SimpleWrapper();
		wrapper2.setServletClass("TestServlet");
		wrapper2.setName("Test");
		
		// 设置上下文
		Context context = new StandardContext();
		context.setPath("/myApp");
		context.setDocBase("myApp");
		
		// 设置日志
		Logger logger = new SimpleLogger();
		context.setLogger(logger);
		
		context.addChild(wrapper1);
		context.addChild(wrapper2);
		
		context.addServletMapping("/Hello", "Hello");
		context.addServletMapping("/Test", "Test");
		
		// 设置监听器
		LifecycleListener listener = new SimpleContextConfig();
		((Lifecycle)context).addLifecycleListener(listener);
		
		// 设置主机
		Host host = new StandardHost();
		host.addChild(context);
		host.setName("localhost");
		host.setAppBase("webapps");
		
		// 设置加载器
		Loader loader = new WebappLoader();
		context.setLoader(loader);
		
		// 设置引擎
		Engine engine = new StandardEngine();
		engine.addChild(host);
		engine.setDefaultHost("localhost");
		
		// 设置服务
		Service service = new StandardService();
		service.setName("Stand-alone Service");
		service.addConnector(connector);
		service.setContainer(engine);
		
		// 设置服务器
		Server server = new StandardServer();
		server.addService(service);
		
		if (server instanceof Lifecycle) {
			try {
				// 初始化服务器
				server.initialize();
				// 启动服务器
				((Lifecycle)server).start();
				// 监听停机端口
				server.await();
			} catch (LifecycleException e) {
				e.printStackTrace();
			}
		}
		if (server instanceof Lifecycle) {
			try {
				// 停止服务器
				((Lifecycle)server).stop();
			} catch (LifecycleException e) {
				e.printStackTrace();
			}
		}
	}
	
}
