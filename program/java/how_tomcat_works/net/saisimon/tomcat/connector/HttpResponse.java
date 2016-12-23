package net.saisimon.tomcat.connector;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Connector;
import org.apache.catalina.Context;
import org.apache.catalina.Globals;
import org.apache.catalina.Request;
import org.apache.catalina.util.CookieTools;

public class HttpResponse implements HttpServletResponse, org.apache.catalina.HttpResponse {
	
	private OutputStream output;
	private Request request;
	private PrintWriter writer;
	private String characterEncoding;
	private String contentType;
	private int contentLength = -1;
	private Connector connector;
	private Context context;
	private boolean committed;
	private int status = 200;
	private String message = "OK";
	private List<Cookie> cookies = new ArrayList<>();
	private Map<String, List<Object>> headers = new HashMap<String, List<Object>>();
	private ServletOutputStream stream;
	private boolean suspended;
	private Locale locale;
	private int bufferCount;
	private byte[] buffer = new byte[1024];
	private int contentCount;
	private boolean error;
	private boolean allowChunking;
	
	@Override
	public String getCharacterEncoding() {
		if (characterEncoding == null)
            return "UTF-8";
        else
            return characterEncoding;
	}

	@Override
	public String getContentType() {
		return contentType;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		if (stream == null)
            stream = createOutputStream();
		((HttpStream) stream).setCommit(true);
		return stream;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		if (writer != null) {
            return writer;
		}
        HttpStream newStream = (HttpStream) createOutputStream();
        newStream.setCommit(false);
        OutputStreamWriter osr = new OutputStreamWriter(newStream, getCharacterEncoding());
        writer = new HttpWriter(osr, newStream);
        stream = newStream;
        return writer;
	}

	@Override
	public void setCharacterEncoding(String characterEncoding) {
		this.characterEncoding = characterEncoding;
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
	public void setBufferSize(int size) {
		if (buffer.length >= size)
            return;
        buffer = new byte[size];
	}

	@Override
	public int getBufferSize() {
		return buffer.length;
	}

	@Override
	public void flushBuffer() throws IOException {
		if (!isCommitted())
            sendHeaders();
		
		committed = true;
        if (bufferCount > 0) {
            try {
                output.write(buffer, 0, bufferCount);
            } catch(IOException ioe) {
            	
            } finally {
                bufferCount = 0;
            }
        }
	}

	@Override
	public void resetBuffer() {
		bufferCount = 0;
	}

	@Override
	public boolean isCommitted() {
		return committed;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	@Override
	public Locale getLocale() {
		return locale;
	}

	@Override
	public void addCookie(Cookie cookie) {
		cookies.add(cookie);
	}

	@Override
	public boolean containsHeader(String name) {
		return headers.get(name) != null;
	}

	@Override
	public String encodeURL(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeRedirectURL(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeRedirectUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendError(int sc, String msg) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendError(int sc) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendRedirect(String location) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDateHeader(String name, long date) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addDateHeader(String name, long date) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHeader(String name, String value) {
		if (isCommitted())
            return;
		List<Object> values = new ArrayList<>();
        values.add(value);
        headers.put(name, values);

        String match = name.toLowerCase();
        if (match.equals("content-length")) {
            int contentLength = -1;
            try {
                contentLength = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                ;
            }
            if (contentLength >= 0)
                setContentLength(contentLength);
        } else if (match.equals("content-type")) {
            setContentType(value);
        }
	}

	@Override
	public void addHeader(String name, String value) {
		List<Object> values = headers.get(name);
        if (values == null) {
            values = new ArrayList<>();
            headers.put(name, values);
        }
        values.add(value);
	}

	@Override
	public void setIntHeader(String name, int value) {
		setHeader(name, "" + value);
	}

	@Override
	public void addIntHeader(String name, int value) {
		addHeader(name, "" + value);
	}

	@Override
	public void setStatus(int sc) {
		this.status = sc;
	}

	@Override
	public void setStatus(int sc, String sm) {
		this.status = sc;
        this.message = sm;
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
	public int getContentCount() {
		return contentCount;
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
	public void setAppCommitted(boolean appCommitted) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAppCommitted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getIncluded() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setIncluded(boolean included) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getInfo() {
		return null;
	}

	@Override
	public Request getRequest() {
		return request;
	}

	@Override
	public void setRequest(Request request) {
		this.request = (HttpRequest) request;
	}

	@Override
	public ServletResponse getResponse() {
		return this;
	}

	@Override
	public OutputStream getStream() {
		return output;
	}

	@Override
	public void setStream(OutputStream stream) {
		this.output = stream;
	}

	@Override
	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
		if (stream != null)
            ((HttpStream) stream).setSuspended(suspended);
	}

	@Override
	public boolean isSuspended() {
		return suspended;
	}

	@Override
	public void setError() {
		this.error = true;
	}

	@Override
	public boolean isError() {
		return error;
	}

	@Override
	public ServletOutputStream createOutputStream() throws IOException {
		return new HttpStream(this);
	}

	@Override
	public int getContentLength() {
		return contentLength;
	}

	@Override
	public PrintWriter getReporter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void recycle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendAcknowledgement() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cookie[] getCookies() {
		return (Cookie[]) cookies.toArray(new Cookie[cookies.size()]);
	}

	@Override
	public String getHeader(String name) {
		ArrayList<Object> values = null;
        values = (ArrayList<Object>) headers.get(name);
        if (values != null)
            return ((String) values.get(0));
        else
            return (null);
	}

	@Override
	public String[] getHeaderNames() {
		String results[] = new String[headers.size()];
        return (String[]) headers.keySet().toArray(results);
	}

	@Override
	public String[] getHeaderValues(String name) {
		ArrayList<Object> values = null;
        values = (ArrayList<Object>) headers.get(name);
        if (values == null)
            return (new String[0]);
        String results[] = new String[values.size()];
        return (String[]) values.toArray(results);
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public int getStatus() {
		return status;
	}

	@Override
	public void reset(int status, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finishResponse() throws IOException {
		sendHeaders();
		if (this.stream == null) {
            ServletOutputStream sos = getOutputStream();
            sos.flush();
            sos.close();
            return;
        }
		if (writer != null) {
			writer.flush();
			writer.close();
		} else {
            stream.flush();
            stream.close();
        }
		
	}
	
	protected void sendHeaders() throws IOException {

        if (isCommitted())
            return;

        OutputStreamWriter osr = null;
        try {
            osr = new OutputStreamWriter(getStream(), getCharacterEncoding());
        } catch (UnsupportedEncodingException e) {
            osr = new OutputStreamWriter(getStream());
        }
        final PrintWriter outputWriter = new PrintWriter(osr);

        outputWriter.print(this.getProtocol());
        outputWriter.print(" ");
        outputWriter.print(status);
        if (message != null) {
            outputWriter.print(" ");
            outputWriter.print(message);
        }
        outputWriter.print("\r\n");
        if (getContentType() != null) {
            outputWriter.print("Content-Type: " + getContentType() + "\r\n");
            // System.out.println(" Content-Type: " + getContentType());
        }
        if (getContentLength() >= 0) {
            outputWriter.print("Content-Length: " + getContentLength() +
                               "\r\n");
            // System.out.println(" Content-Length: " + getContentLength());
        }

        Iterator<String> names = headers.keySet().iterator();
        while (names.hasNext()) {
            String name = names.next();
            ArrayList<Object> values = (ArrayList<Object>) headers.get(name);
            Iterator<Object> items = values.iterator();
            while (items.hasNext()) {
                String value = (String) items.next();
                    outputWriter.print(name);
                    outputWriter.print(": ");
                    outputWriter.print(value);
                    outputWriter.print("\r\n");
            }
        }

        HttpServletRequest hreq = (HttpServletRequest) request.getRequest();
        HttpSession session = hreq.getSession(false);

        if ((session != null) && session.isNew() && (getContext() != null)
            && getContext().getCookies()) {
            Cookie cookie = new Cookie(Globals.SESSION_COOKIE_NAME,
                                       session.getId());
            cookie.setMaxAge(-1);
            String contextPath = null;
            if (context != null)
                contextPath = context.getPath();
            if ((contextPath != null) && (contextPath.length() > 0))
                cookie.setPath(contextPath);
            else
                cookie.setPath("/");
            if (hreq.isSecure())
                cookie.setSecure(true);
            addCookie(cookie);
        }

        Iterator<Cookie> items = cookies.iterator();
        while (items.hasNext()) {
            Cookie cookie = items.next();
            outputWriter.print(CookieTools.getCookieHeaderName(cookie));
            outputWriter.print(": ");
            outputWriter.print(CookieTools.getCookieHeaderValue(cookie));
            outputWriter.print("\r\n");
            //System.out.println(" " +
            //                   CookieTools.getCookieHeaderName(cookie) +
            //                   ": " +
            //                   CookieTools.getCookieHeaderValue(cookie));
        }

        outputWriter.print("\r\n");
        outputWriter.flush();
        committed = true;
    }
	
	protected String getProtocol() {
        return(request.getRequest().getProtocol());
    }
	
	public void write(int b) throws IOException {
        if (bufferCount >= buffer.length)
            flushBuffer();
        buffer[bufferCount++] = (byte) b;
        contentCount++;
    }
	
	public void write(byte b[]) throws IOException {
        write(b, 0, b.length);
    }
	
	public void write(byte b[], int off, int len) throws IOException {
        if (len == 0)
            return;
        if (len <= (buffer.length - bufferCount)) {
            System.arraycopy(b, off, buffer, bufferCount, len);
            bufferCount += len;
            contentCount += len;
            return;
        }

        flushBuffer();
        int iterations = len / buffer.length;
        int leftoverStart = iterations * buffer.length;
        int leftoverLen = len - leftoverStart;
        for (int i = 0; i < iterations; i++)
            write(b, off + (i * buffer.length), buffer.length);

        if (leftoverLen > 0)
            write(b, off + leftoverStart, leftoverLen);

    }
	
	void setAllowChunking(boolean allowChunking) {
        this.allowChunking = allowChunking;
    }

    public boolean isChunkingAllowed() {
        return allowChunking;
    }
    
    public void removeHeader(String name, String value) {
        if (isCommitted()) {
            return;
        }
        List<Object> values = headers.get(name);
        if ((values != null) && (!values.isEmpty())) {
            values.remove(value);
            if (values.isEmpty()) {
                headers.remove(name);
            }
        }
    }
    
    public boolean isCloseConnection() {
        String connectionValue = (String) getHeader("Connection");
        return connectionValue != null && connectionValue.equals("close");
    }

}
