package net.saisimon.tomcat.startup;

import net.saisimon.tomcat.connector.HttpConnector;

public final class Bootstrap {
	
	public static void main(String[] args) {
		HttpConnector connector = new HttpConnector();
		connector.start();
	}
	
}
