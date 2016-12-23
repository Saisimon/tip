package net.saisimon.tomcat.startup;

import org.apache.catalina.Context;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Loader;
import org.apache.catalina.Logger;
import org.apache.catalina.Wrapper;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.loader.WebappLoader;

import net.saisimon.tomcat.connector.HttpConnector;
import net.saisimon.tomcat.core.SimpleContextConfig;
import net.saisimon.tomcat.core.SimpleLogger;
import net.saisimon.tomcat.core.SimpleWrapper;

public final class Bootstrap {
	
	public static void main(String[] args) {
		System.setProperty("catalina.base", System.getProperty("user.dir"));
		HttpConnector connector = new HttpConnector();
		connector.setMinProcessors(1);
		Wrapper wrapper1 = new SimpleWrapper();
		wrapper1.setServletClass("HelloServlet");
		wrapper1.setName("Hello");
		
		Wrapper wrapper2 = new SimpleWrapper();
		wrapper2.setServletClass("TestServlet");
		wrapper2.setName("Test");
		
		Context context = new StandardContext();
		context.setPath("/myApp");
		context.setDocBase("myApp");
		
		Logger logger = new SimpleLogger();
		context.setLogger(logger);
		
		context.addChild(wrapper1);
		context.addChild(wrapper2);
		
		context.addServletMapping("/Hello", "Hello");
		context.addServletMapping("/Test", "Test");
		
		LifecycleListener listener = new SimpleContextConfig();
		((Lifecycle)context).addLifecycleListener(listener);
		
		Loader loader = new WebappLoader();
		context.setLoader(loader);
		
		connector.setContainer(context);
		try {
			connector.initialize();
			((Lifecycle)connector).start();
			((Lifecycle)context).start();
			System.in.read();
			((Lifecycle)context).stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
