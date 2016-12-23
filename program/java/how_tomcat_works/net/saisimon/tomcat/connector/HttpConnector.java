package net.saisimon.tomcat.connector;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Stack;

import org.apache.catalina.Connector;
import org.apache.catalina.Container;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Request;
import org.apache.catalina.Response;
import org.apache.catalina.Service;
import org.apache.catalina.net.ServerSocketFactory;
import org.apache.catalina.util.LifecycleSupport;

public class HttpConnector implements Runnable, Lifecycle, Connector {
	
	private static final int PORT = 8888;
	private static final String HOST = "localhost";
	
	private Container container;
	protected LifecycleSupport support = new LifecycleSupport(this);
	
	private String scame = "http";
	private boolean stopped = false;
	private boolean started = false;
	
	private ServerSocket serverSocket = null;
	
	private Thread thread = null;
	private Stack<HttpProcessor> processorPools = new Stack<>();
	
	private boolean allowChunking = true;
	private int minProcessors = 5;
	
	@Override
	public void run() {
		while (!stopped) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
			HttpProcessor processor = processorPools.pop();
			processor.assign(socket);
		}
	}

	public String getScame() {
		return scame;
	}

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
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
        if (started)
            throw new LifecycleException("Connector has been started!");
        support.fireLifecycleEvent(START_EVENT, null);
        started = true;
        thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
        for (int i = 0; i < minProcessors; i++) {
        	HttpProcessor processor = new HttpProcessor(this);
            processor.start();
            processorPools.push(processor);
        }
    }

	@Override
	public void stop() throws LifecycleException {
		if (!started)
            throw new LifecycleException("Connector has not been started!");
        support.fireLifecycleEvent(STOP_EVENT, null);
        started = false;
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        serverSocket = null;
	}

	@Override
	public boolean getEnableLookups() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setEnableLookups(boolean enableLookups) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServerSocketFactory getFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFactory(ServerSocketFactory factory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRedirectPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRedirectPort(int redirectPort) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getScheme() {
		return scame;
	}

	@Override
	public void setScheme(String scheme) {
		this.scame = scheme;
	}

	@Override
	public boolean getSecure() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setSecure(boolean secure) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Service getService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setService(Service service) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Request createRequest() {
		HttpRequest req = new HttpRequest();
		req.setConnector(this);
		return req;
	}

	@Override
	public Response createResponse() {
		HttpResponse resp = new HttpResponse();
		resp.setConnector(this);
		return resp;
	}

	@Override
	public void initialize() throws LifecycleException {
		try {
			serverSocket = new ServerSocket(PORT, 1, InetAddress.getByName(HOST));
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	void recycle(HttpProcessor processor) {
		processorPools.push(processor);
	}
	
	public boolean isChunkingAllowed() {
        return allowChunking;
    }

    public boolean getAllowChunking() {
        return isChunkingAllowed();
    }

    public void setAllowChunking(boolean allowChunking) {
        this.allowChunking = allowChunking;
    }

	public int getMinProcessors() {
		return minProcessors;
	}

	public void setMinProcessors(int minProcessors) {
		this.minProcessors = minProcessors;
	}
	
}
