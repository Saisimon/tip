package net.saisimon.tomcat.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

public class Response implements ServletResponse {
	
	private static final int BUFFER_SIZE = 2048;
	
	private OutputStream output;
	private Request request;
	private PrintWriter writer;
	
	public Response(OutputStream output) {
		this.output = output;
	}

	public void setRequest(Request request) {
		this.request = request;
	}
	
	public void send() throws IOException {
		byte[] buffer = new byte[BUFFER_SIZE];
		FileInputStream fis = null;
		try {
			File file = new File(HttpServer.WEB_ROOT, request.getUri());
			if (file.exists() && file.isFile()) {
				output.write("HTTP/1.1 200\r\n".getBytes());
				fis = new FileInputStream(file);
				int read = fis.read(buffer, 0, BUFFER_SIZE);
				while (read != -1) {
					output.write(buffer, 0, read);
					read = fis.read(buffer, 0, BUFFER_SIZE);
				}
			} else {
				String error = "HTTP/1.1 404 File Not Found\r\n" +
						"Content-Type: text/html\r\n" +
						"Content-Length: 23\r\n" +
						"\r\n" +
						"<h1>File Not Found</h1>";
				output.write(error.getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
	}

	@Override
	public String getCharacterEncoding() {
		return null;
	}

	@Override
	public String getContentType() {
		return null;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return null;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		writer = new PrintWriter(output, true);
		return writer;
	}

	@Override
	public void setCharacterEncoding(String charset) {
		
	}

	@Override
	public void setContentLength(int len) {
		
	}

	@Override
	public void setContentType(String type) {
		
	}

	@Override
	public void setBufferSize(int size) {
		
	}

	@Override
	public int getBufferSize() {
		return 0;
	}

	@Override
	public void flushBuffer() throws IOException {
		
	}

	@Override
	public void resetBuffer() {
		
	}

	@Override
	public boolean isCommitted() {
		return false;
	}

	@Override
	public void reset() {
		
	}

	@Override
	public void setLocale(Locale loc) {
		
	}

	@Override
	public Locale getLocale() {
		return null;
	}
	
}
