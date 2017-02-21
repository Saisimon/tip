package net.saisimon.tomcat.startup;

import java.io.OutputStream;
import java.net.Socket;

public class Stopper {
	
	public static void main(String[] args) {
		int port = 8005;
		try {
			Socket socket = new Socket("localhost", port);
			OutputStream writer = socket.getOutputStream();
			String shutdown = "SHUTDOWN";
			for (int i = 0; i < shutdown.length(); i++) {
				writer.write(shutdown.charAt(i));
			}
			writer.flush();
			writer.close();
			socket.close();
			System.out.println("Shut down server success!");
		} catch (Exception e) {
			System.out.println("Server has not been started!");
			e.printStackTrace();
		}
	}
	
}
