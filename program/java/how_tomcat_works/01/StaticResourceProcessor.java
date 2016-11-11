package net.saisimon.tomcat.server;

import java.io.IOException;

public class StaticResourceProcessor {
	
	public void process(Request request, Response response) {
		try {
			response.send();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
