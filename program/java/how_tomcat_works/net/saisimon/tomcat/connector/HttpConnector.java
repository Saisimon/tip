package net.saisimon.tomcat.connector;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class HttpConnector implements Runnable {
	
	private static final int PORT = 8888;
	private static final String HOST = "localhost";
	
	private String scame = "http";
	boolean stopped;
	
	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(PORT, 1, InetAddress.getByName(HOST));
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		while (!stopped) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
			HttpProcessor processor = new HttpProcessor(this);
			processor.process(socket);
		}
	}

	public String getScame() {
		return scame;
	}
	
}
