package net.saisimon.tomcat.connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;

import net.saisimon.tomcat.core.ServletProcessor;
import net.saisimon.tomcat.core.StaticResourceProcessor;

public class HttpProcessor {
	
	private HttpConnector connector;
	private HttpRequest request;
	private HttpResponse response;
	
	public HttpProcessor(HttpConnector connector) {
		this.connector = connector;
	}
	
	public void process(Socket socket) {
		InputStream input = null;
		OutputStream output = null;
		try {
			input = socket.getInputStream();
			request = new HttpRequest(input);
			response = new HttpResponse(output);
			response.setRequest(request);
			response.setHeader("Server", "Hello Servlet Container!");
			parseRequest(input, output);
			if (request.getRequestURI().startsWith("/servlet/")) {
				ServletProcessor processor = new ServletProcessor();
				processor.process(request, response);
			} else {
				StaticResourceProcessor processor = new StaticResourceProcessor();
				processor.process(request, response);
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	private void parseRequest(InputStream input, OutputStream output) throws IOException, ServletException {
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		String line = br.readLine();
		parse(line);
		while (line != null && !"".equals(line)) {
			System.out.println(line);
			line = br.readLine();
			parseHeader(line);
		}
	}

	private void parse(String line) throws ServletException {
		if (line == null) {
			return;
		}
		String[] strs = line.split(" ");
		if (strs.length != 3) {
			throw new ServletException("HTTP请求头错误");
		}
		String method = strs[0];
		if (method.length() == 0) {
			throw new ServletException("HTTP请求方法缺失");
		}
		request.setMethod(method);
		String protocol = strs[2];
		if (protocol.length() == 0) {
			throw new ServletException("HTTP请求协议缺失");
		}
		request.setProtocol(protocol);
		String uri = strs[1];
		if (uri.length() == 0) {
			throw new ServletException("HTTP请求URI缺失");
		}
		int idx = uri.indexOf('?');
		if (idx != -1) {
			String queryString = uri.substring(idx + 1);
			request.setQueryString(queryString);
			uri = uri.substring(0, idx);
		}
		if (!uri.startsWith("/")) {
			idx = uri.indexOf("://");
			if (idx != -1) {
				idx = uri.indexOf('/', idx + 3);
				if (idx != -1) {
					uri = uri.substring(idx, uri.length());
				} else {
					uri = "/";
				}
			}
		}
		idx = uri.indexOf(";jsessionid=");
		if (idx != -1) {
			String jsessionId = uri.substring(idx + 12);
			uri = uri.substring(0, idx);
			idx = jsessionId.indexOf(';');
			if (idx != -1) {
				String other = jsessionId.substring(idx);
				jsessionId = jsessionId.substring(0, idx);
				uri += other;
			}
			request.setRequestedSessionId(jsessionId);
			request.setRequestedSessionIdFromURL(true);
		} else {
			request.setRequestedSessionIdFromURL(false);
		}
		request.setRequestURL(uri);
	}
	
	private void parseHeader(String line) throws ServletException {
		if (line == null || "".equals(line)) {
			return;
		}
		int idx = line.indexOf(":");
		if (idx == -1) {
			throw new ServletException("HTTP请求头错误");
		}
		String key = line.substring(0, idx);
		String value = line.substring(idx + 2);
		if ("cookie".equalsIgnoreCase(key)) {
			Cookie[] cookies = parseCookie(value.trim());
			for (Cookie cookie : cookies) {
				if (cookie.getName().equalsIgnoreCase("jsessionId")) {
					request.setRequestedSessionId(cookie.getValue());
					request.setRequestedSessionIdFromURL(false);
					request.setRequestedSessionIdFromCookie(true);
				}
				request.addCookie(cookie);
			}
		} else if ("content-length".equalsIgnoreCase(key)) {
			int length = Integer.parseInt(value.trim());
			request.setContentLength(length);
		} else if ("content-type".equalsIgnoreCase(key)) {
			request.setContentType(value.trim());
		}
		request.addHeader(key, value);
	}
	
	private Cookie[] parseCookie(String header) throws ServletException {
		if (header == null || header.length() == 0) {
			return new Cookie[0];
		}
		List<Cookie> list = new ArrayList<>();
		while (header.length() != 0) {
			int idx = header.indexOf(';');
            if (idx < 0)
                idx = header.length();
            if (idx == 0)
                break;
            String token = header.substring(0, idx);
            if (idx < header.length())
                header = header.substring(idx + 1);
            else
                header = "";
            int equals = token.indexOf('=');
            if (equals > 0) {
                String name = token.substring(0, equals).trim();
                String value = token.substring(equals + 1).trim();
                list.add(new Cookie(name, value));
            }
		}
		return list.toArray(new Cookie[list.size()]);
	}
	
}
