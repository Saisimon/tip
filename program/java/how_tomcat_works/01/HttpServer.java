package net.saisimon.tomcat.server;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
	
	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";
	private static final String SHUTDOWN = "/shutdown";
	private boolean shutdown = false;
	
	public static void main(String[] args) {
		HttpServer server = new HttpServer();
		server.await();
	}
	
	public void await() {
		ServerSocket ss = null;
		int port = 8888;
		try {
			ss = new ServerSocket(port, 1, InetAddress.getByName("localhost"));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		while (!shutdown) {
			Socket socket = null;
			InputStream in = null;
			OutputStream out = null;
			try {
				socket = ss.accept();
				in = socket.getInputStream();
				out = socket.getOutputStream();
				Request request = new Request(in);
				request.parse();
				Response response = new Response(out);
				response.setRequest(request);
				if (request.getUri().startsWith("/servlet/")) {
					ServletProcessor processor = new ServletProcessor();
					processor.process(request, response);
				} else {
					StaticResourceProcessor processor = new StaticResourceProcessor();
					processor.process(request, response);
				}
				socket.close();
				shutdown = SHUTDOWN.equals(request.getUri());
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
	}
}
