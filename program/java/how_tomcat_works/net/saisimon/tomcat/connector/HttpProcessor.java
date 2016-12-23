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
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.util.FastHttpDateFormat;
import org.apache.catalina.util.LifecycleSupport;

public class HttpProcessor implements Lifecycle, Runnable {
	
	private LifecycleSupport support = new LifecycleSupport(this);
	private Socket socket;
	private boolean available;
	private boolean stopped;
	private boolean started;
	private Thread thread;
	private HttpConnector connector;
	private HttpRequest request;
	private HttpResponse response;
	private int proxyPort;
	private int serverPort;
	private InputStream input;
	private OutputStream output;

	public HttpProcessor(HttpConnector connector) {
		super();
        this.connector = connector;
        this.request = (HttpRequest) connector.createRequest();
        this.response = (HttpResponse) connector.createResponse();
	}

	@Override
	public void run() {
		boolean ok = true;
		
		while (!stopped) {
			Socket socket = await();
			if (socket == null) {
				continue;
			}
			try {
				input = socket.getInputStream();
				request.setStream(input);
                request.setResponse(response);
                output = socket.getOutputStream();
                response.setStream(output);
                response.setRequest(request);
				process(socket);
				if (connector.isChunkingAllowed()) {
                    response.setAllowChunking(true);
				}
			} catch (IOException e) {
				try {
                    ((HttpServletResponse) response.getResponse()).sendError
                        (HttpServletResponse.SC_BAD_REQUEST);
                } catch (Exception f) {
                    ;
                }
                ok = false;
			} catch (ServletException e) {
				try {
                    ((HttpServletResponse) response.getResponse()).sendError
                        (HttpServletResponse.SC_BAD_REQUEST);
                } catch (Exception f) {
                    ;
                }
                ok = false;
			}
			((HttpServletResponse) response).setHeader("Date", FastHttpDateFormat.getCurrentDate());
	        if (ok) {
	            try {
					connector.getContainer().invoke(request, response);
				} catch (IOException e) {
					try {
	                    ((HttpServletResponse) response.getResponse()).sendError
	                        (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	                } catch (Exception f) {
	                    ;
	                }
	                ok = false;
				} catch (ServletException e) {
					try {
	                    ((HttpServletResponse) response.getResponse()).sendError
	                        (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	                } catch (Exception f) {
	                    ;
	                }
	                ok = false;
				}
	        }
	        try {
				response.finishResponse();
				request.finishRequest();
				if (output != null) {
					output.flush();
				}
			} catch (IOException e) {
				ok = false;
			}
	        connector.recycle(this);
		}
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				
			}
		}
		socket = null;
	}

	private void process(Socket socket) throws IOException, ServletException {
		parseConnection(socket);
		parseRequest(socket);
	}

	@Override
	public void addLifecycleListener(LifecycleListener listener) {
		support.addLifecycleListener(listener);
	}

	@Override
	public LifecycleListener[] findLifecycleListeners() {
		return support.findLifecycleListeners();
	}

	@Override
	public void removeLifecycleListener(LifecycleListener listener) {
		support.removeLifecycleListener(listener);
	}

	@Override
	public void start() throws LifecycleException {
		support.fireLifecycleEvent(START_EVENT, null);
        started = true;
        thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
	}

	@Override
	public void stop() throws LifecycleException {
		stopped = true;
        assign(null);
        thread = null;
	}
	
	private synchronized Socket await() {
		while (!available) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
		Socket socket = this.socket;
        available = false;
        notifyAll();
        return socket;
	}
	
	synchronized void assign(Socket socket) {
		while (available) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		this.socket = socket;
		available = true;
        notifyAll();
	}
	
	private void parseConnection(Socket socket) throws IOException, ServletException {
        ((HttpRequest) request).setInet(socket.getInetAddress());
        if (proxyPort != 0) {
            request.setServerPort(proxyPort);
        } else {
            request.setServerPort(serverPort);
        }
        request.setSocket(socket);
    }
	
	private void parseRequest(Socket socket) throws IOException, ServletException {
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
