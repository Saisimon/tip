package net.saisimon.tomcat.connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.saisimon.tomcat.util.ParameterMap;

public class HttpRequest implements HttpServletRequest {
	
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
	
	protected ParameterMap<String, Object> parameterMap;
	protected List<Cookie> cookies = new ArrayList<>();
	protected Map<String, String> headers = new HashMap<String, String>();
	
	public HttpRequest(InputStream input) {
		this.input = input;
	}
	
	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	
	public void setRequestedSessionId(String requestedSessionId) {
		this.requestedSessionId = requestedSessionId;
	}
	
	public void setRequestedSessionIdFromURL(boolean requestedSessionIdFromURL) {
		this.requestedSessionIdFromURL = requestedSessionIdFromURL;
	}
	
	public void setRequestedSessionIdFromCookie(boolean requestedSessionIdFromCookie) {
		this.requestedSessionIdFromCookie = requestedSessionIdFromCookie;
	}
	
	public void addCookie(Cookie cookie) {
		cookies.add(cookie);
	}
	
	public void addHeader(String key, String value) {
		headers.put(key, value);
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
		String queryString = getQueryString();
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
	
	private void parseQuery(String query) {
		
	}

	@Override
	public Object getAttribute(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<?> getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCharacterEncoding() {
		return characterEncoding;
	}

	@Override
	public void setCharacterEncoding(String characterEncoding) throws UnsupportedEncodingException {
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
	public ServletInputStream getInputStream() throws IOException {
		return (ServletInputStream) input;
	}

	@Override
	public String getParameter(String name) {
		parseParameterMap();
		return (String) parameterMap.get(name);
	}

	@Override
	public Enumeration<?> getParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getParameterValues(String name) {
		return null;
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
		return null;
	}

	@Override
	public String getServerName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getServerPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BufferedReader getReader() throws IOException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAttribute(String name) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getDateHeader(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getHeader(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<?> getHeaders(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<?> getHeaderNames() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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

}
