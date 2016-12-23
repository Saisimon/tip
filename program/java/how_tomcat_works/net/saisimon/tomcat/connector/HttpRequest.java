package net.saisimon.tomcat.connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Connector;
import org.apache.catalina.Context;
import org.apache.catalina.Response;
import org.apache.catalina.Wrapper;
import org.apache.catalina.util.Enumerator;
import org.apache.catalina.util.RequestUtil;
import org.apache.commons.lang.StringUtils;

import net.saisimon.tomcat.util.ParameterMap;

public class HttpRequest implements HttpServletRequest, org.apache.catalina.HttpRequest {
	
	private static final String DEFAULT_ENCODE = "UTF-8";
	
	private InputStream input;
	private String characterEncoding;
	private int contentLength;
	private String contentType;
	private String requestURL = "";
	private String method;
	private String protocol;
	private String queryString;
	private String requestedSessionId;
	private boolean requestedSessionIdFromURL;
	private boolean requestedSessionIdFromCookie;
	private boolean parsed;
	private String scheme;
	private Connector connector = null;
	private ParameterMap<String, Object> parameterMap;
	private List<Cookie> cookies = new ArrayList<>();
	private Map<String, List<Object>> headers = new HashMap<String, List<Object>>();
	private Response response;
	private Context context;
	private Socket socket;
	private int serverPort;
	private String serverName;
	private String contextPath = "";
	private Map<String, Object> attributes = new HashMap<>();
	private Wrapper wrapper = null;
	private BufferedReader reader = null;
	private String servletPath;
	private InetAddress inet = null;

	private String decodedRequestURI;
	
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
	
	public void setRequestedSessionIdFromURL(boolean requestedSessionIdFromURL) {
		this.requestedSessionIdFromURL = requestedSessionIdFromURL;
	}
	
	public void setRequestedSessionIdFromCookie(boolean requestedSessionIdFromCookie) {
		this.requestedSessionIdFromCookie = requestedSessionIdFromCookie;
	}
	
	public void parseParameterMap() {
		if (parsed) {
			return;
		}
		ParameterMap<String, Object> result = parameterMap;
		if (result == null) {
			result = new ParameterMap<>();
		}
		result.setLocked(false);
		if (getCharacterEncoding() == null) {
			try {
				setCharacterEncoding(DEFAULT_ENCODE);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		String contentType = getContentType();
		if (contentType == null) {
			contentType = "";
		}
		int idx = contentType.indexOf(';');
		if (idx != -1) {
			contentType = contentType.substring(0, idx).trim();
		} else {
			contentType = contentType.trim();
		}
		if ("POST".equalsIgnoreCase(getMethod()) && getContentLength() > 0 && "application/x-www-form-urlencoded".equalsIgnoreCase(contentType)) {
			
		}
		result.setLocked(true);
		parsed = true;
		parameterMap = result;
	}
	
	@Override
	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}
	
	@Override
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	@Override
	public void setMethod(String method) {
		this.method = method;
	}
	
	@Override
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	
	@Override
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	
	@Override
	public void setRequestedSessionId(String requestedSessionId) {
		this.requestedSessionId = requestedSessionId;
	}

	@Override
	public Object getAttribute(String name) {
		return attributes.get(name);
	}

	@Override
	public Enumeration<?> getAttributeNames() {
		return new Enumerator(attributes.keySet());
	}

	@Override
	public String getCharacterEncoding() {
		return characterEncoding;
	}

	@Override
	public void setCharacterEncoding(String characterEncoding) throws UnsupportedEncodingException {
		if (StringUtils.isEmpty(characterEncoding)) {
			characterEncoding = DEFAULT_ENCODE;
		}
		this.characterEncoding = characterEncoding;
	}

	@Override
	public int getContentLength() {
		return contentLength;
	}

	@Override
	public String getContentType() {
		return contentType;
	}
	
	@Override
	public void addCookie(Cookie cookie) {
		cookies.add(cookie);
	}
	
	@Override
	public void addHeader(String key, String value) {
		key = key.toLowerCase();
        List<Object> values = headers.get(key);
        if (values == null) {
            values = new ArrayList<>();
            headers.put(key, values);
        }
        values.add(value);
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return (ServletInputStream) input;
	}

	@Override
	public String getParameter(String name) {
		parseParameterMap();
        String values[] = (String[]) parameterMap.get(name);
        if (values != null) {
        	return values[0];
        } else {
        	return null;
        }
	}

	@Override
	public Enumeration<?> getParameterNames() {
		parseParameterMap();
		return new Enumerator(parameterMap.keySet());
	}

	@Override
	public String[] getParameterValues(String name) {
		parseParameterMap();
        String values[] = (String[]) parameterMap.get(name);
        if (values != null) {
        	return values;
        } else {
        	return null;
        }
	}

	@Override
	public Map<?, ?> getParameterMap() {
		parseParameterMap();
		return parameterMap;
	}

	@Override
	public String getProtocol() {
		return protocol;
	}

	@Override
	public String getScheme() {
		return scheme;
	}

	@Override
	public String getServerName() {
		return serverName;
	}

	@Override
	public int getServerPort() {
		return serverPort;
	}

	@Override
	public BufferedReader getReader() throws IOException {
		if (reader == null) {
            String encoding = getCharacterEncoding();
            if (encoding == null)
                encoding = "ISO-8859-1";
            InputStreamReader isr =
                new InputStreamReader(createInputStream(), encoding);
            reader = new BufferedReader(isr);
        }
        return (reader);
	}

	@Override
	public String getRemoteAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRemoteHost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttribute(String name, Object o) {
		attributes.put(name, o);
	}

	@Override
	public void removeAttribute(String name) {
		attributes.remove(name);
	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<?> getLocales() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RequestDispatcher getRequestDispatcher(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRealPath(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRemotePort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getLocalName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLocalAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLocalPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getAuthType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cookie[] getCookies() {
		if (cookies.size() < 1) {
			return null;
		}
        return cookies.toArray(new Cookie[cookies.size()]);
	}

	@Override
	public long getDateHeader(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getHeader(String name) {
		name = name.toLowerCase();
        List<Object> values = headers.get(name);
        if (values != null) {
            return (String) values.get(0);
        } else {
            return null;
        }
	}

	@Override
	public Enumeration<?> getHeaders(String name) {
		name = name.toLowerCase();
        List<Object> values = headers.get(name);
        if (values != null) {
            return new Enumerator(values);
        } else {
            return new Enumerator(new ArrayList<>());
        }
	}

	@Override
	public Enumeration<?> getHeaderNames() {
		return new Enumerator(headers.keySet());
	}

	@Override
	public int getIntHeader(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getMethod() {
		return method;
	}

	@Override
	public String getPathInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPathTranslated() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContextPath() {
		return contextPath;
	}

	@Override
	public String getQueryString() {
		return queryString;
	}

	@Override
	public String getRemoteUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUserInRole(String role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRequestedSessionId() {
		return requestedSessionId;
	}

	@Override
	public String getRequestURI() {
		return requestURL;
	}

	@Override
	public StringBuffer getRequestURL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletPath() {
		return servletPath;
	}

	@Override
	public HttpSession getSession(boolean create) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpSession getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRequestedSessionIdValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromCookie() {
		return requestedSessionIdFromCookie;
	}

	@Override
	public boolean isRequestedSessionIdFromURL() {
		return requestedSessionIdFromURL;
	}

	@Override
	@Deprecated
	public boolean isRequestedSessionIdFromUrl() {
		return requestedSessionIdFromURL;
	}

	@Override
	public String getAuthorization() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAuthorization(String authorization) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Connector getConnector() {
		return connector;
	}

	@Override
	public void setConnector(Connector connector) {
		this.connector = connector;
	}

	@Override
	public Context getContext() {
		return context;
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletRequest getRequest() {
		return this;
	}

	@Override
	public Response getResponse() {
		return response;
	}

	@Override
	public void setResponse(Response response) {
		this.response = response;
	}

	@Override
	public Socket getSocket() {
		return socket;
	}

	@Override
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	@Override
	public InputStream getStream() {
		return input;
	}

	@Override
	public void setStream(InputStream input) {
		this.input = input;
	}

	@Override
	public Wrapper getWrapper() {
		return wrapper;
	}

	@Override
	public void setWrapper(Wrapper wrapper) {
		this.wrapper = wrapper;
	}

	@Override
	public ServletInputStream createInputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void finishRequest() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getNote(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<?> getNoteNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void recycle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeNote(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNote(String name, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRemoteAddr(String remote) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	@Override
	public void setSecure(boolean secure) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	@Override
	public void setServerPort(int port) {
		this.serverPort = port;
	}

	@Override
	public void addLocale(Locale locale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addParameter(String name, String[] values) {
		parameterMap.put(name, values);
	}

	@Override
	public void clearCookies() {
		cookies.clear();
	}

	@Override
	public void clearHeaders() {
		headers.clear();
	}

	@Override
	public void clearLocales() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearParameters() {
		if (parameterMap != null) {
			parameterMap.setLocked(false);
			parameterMap.clear();
        } else {
        	parameterMap = new ParameterMap<>();
        }
	}

	@Override
	public void setAuthType(String type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	@Override
	public void setPathInfo(String path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRequestedSessionCookie(boolean flag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRequestedSessionURL(boolean flag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRequestURI(String uri) {
		this.requestURL = uri;
	}

	@Override
	public void setDecodedRequestURI(String uri) {
		this.decodedRequestURI = uri;
	}

	@Override
	public String getDecodedRequestURI() {
		if (decodedRequestURI == null)
            decodedRequestURI = RequestUtil.URLDecode(getRequestURI());
        return decodedRequestURI;
	}

	@Override
	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}

	@Override
	public void setUserPrincipal(Principal principal) {
		// TODO Auto-generated method stub
		
	}

	public InetAddress getInet() {
		return inet;
	}

	public void setInet(InetAddress inet) {
		this.inet = inet;
	}

}
