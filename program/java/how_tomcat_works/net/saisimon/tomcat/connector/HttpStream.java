package net.saisimon.tomcat.connector;

import java.io.IOException;

import javax.servlet.ServletOutputStream;

import org.apache.catalina.Response;

public class HttpStream extends ServletOutputStream {
	
	private boolean commit;
	private int count;
	private Response response;
	private boolean suspended;
	protected int length = -1;
	private boolean useChunking;
	private boolean writingChunk;

	public HttpStream(HttpResponse response) {
        super();
        commit = false;
        count = 0;
        this.response = response;
        this.suspended = response.isSuspended();
        checkChunking(response);
    }

	@Override
	public void write(int b) throws IOException {
		if (suspended)
            return;
		
		if (useChunking && !writingChunk) {
            writingChunk = true;
            try {
                print("1\r\n");
                ((HttpResponse)response).write(b);
        		count++;
                println();
            } finally {
                writingChunk = false;
            }
        } else {
        	((HttpResponse)response).write(b);
    		count++;
        }
	}
	
	@Override
	public void write(byte[] b) throws IOException {
		if (suspended)
            return;
        write(b, 0, b.length);
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		if (suspended)
            return;
		
		if (useChunking && !writingChunk) {
            if (len > 0) {
                writingChunk = true;
                try {
                    println(Integer.toHexString(len));
                    int actual = len;
                    if ((length > 0) && ((count + len) >= length)) {
                        actual = length - count;
                    }
                    ((HttpResponse)response).write(b, off, len);
                    count += actual;
                    println();
                } finally {
                    writingChunk = false;
                }
            }
        } else {
        	int actual = len;
            if ((length > 0) && ((count + len) >= length)) {
                actual = length - count;
            }
            ((HttpResponse)response).write(b, off, len);
            count += actual;
        }
	}

	@Override
	public void flush() throws IOException {
		if (commit) {
            response.getResponse().flushBuffer();
		}
	}

	public boolean isCommit() {
		return commit;
	}

	public void setCommit(boolean commit) {
		this.commit = commit;
	}

	public boolean isSuspended() {
		return suspended;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	@Override
	public void close() throws IOException {
		if (useChunking) {
            writingChunk = true;
            try {
                print("0\r\n\r\n");
            } finally {
                writingChunk = false;
            }
        }
		response.getResponse().flushBuffer();
	}
	
	void checkChunking(HttpResponse response) {
        if (count != 0)
            return;
        useChunking = !response.isCommitted() && response.getContentLength() == -1;
        if (!response.isChunkingAllowed() && useChunking) {
            response.setHeader("Connection", "close");
        }
        useChunking = (useChunking && !response.isCloseConnection());
        if (useChunking) {
            response.setHeader("Transfer-Encoding", "chunked");
        } else if (response.isChunkingAllowed()) {
            response.removeHeader("Transfer-Encoding", "chunked");
        }
    }
	
}
