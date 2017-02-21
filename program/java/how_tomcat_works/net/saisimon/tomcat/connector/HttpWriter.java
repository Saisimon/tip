package net.saisimon.tomcat.connector;

import java.io.PrintWriter;
import java.io.Writer;

public class HttpWriter extends PrintWriter {
	
	private HttpStream stream;
	
	public HttpWriter(Writer out, HttpStream stream) {
		super(out);
		this.stream = stream;
        this.stream.setCommit(false);
	}

	@Override
	public void flush() {
		stream.setCommit(true);
		super.flush();
		stream.setCommit(false);
	}

	@Override
	public void write(int c) {
		super.write(c);
		super.flush();
	}

	@Override
	public void write(char[] buf, int off, int len) {
		super.write(buf, off, len);
		super.flush();
	}

	@Override
	public void write(char[] buf) {
		super.write(buf);
		super.flush();
	}

	@Override
	public void write(String s, int off, int len) {
		super.write(s, off, len);
		super.flush();
	}

	@Override
	public void write(String s) {
		super.write(s);
		super.flush();
	}

	@Override
	public void print(boolean b) {
		super.print(b);
		super.flush();
	}

	@Override
	public void print(char c) {
		super.print(c);
		super.flush();
	}

	@Override
	public void print(int i) {
		super.print(i);
		super.flush();
	}

	@Override
	public void print(long l) {
		super.print(l);
		super.flush();
	}

	@Override
	public void print(float f) {
		super.print(f);
		super.flush();
	}

	@Override
	public void print(double d) {
		super.print(d);
		super.flush();
	}

	@Override
	public void print(char[] s) {
		super.print(s);
		super.flush();
	}

	@Override
	public void print(String s) {
		super.print(s);
		super.flush();
	}

	@Override
	public void print(Object obj) {
		super.print(obj);
		super.flush();
	}

	@Override
	public void println(boolean x) {
		super.println(x);
		super.flush();
	}

	@Override
	public void println(char x) {
		super.println(x);
		super.flush();
	}

	@Override
	public void println(int x) {
		super.println(x);
		super.flush();
	}

	@Override
	public void println(long x) {
		super.println(x);
		super.flush();
	}

	@Override
	public void println(float x) {
		super.println(x);
		super.flush();
	}

	@Override
	public void println(double x) {
		super.println(x);
		super.flush();
	}

	@Override
	public void println(char[] x) {
		super.println(x);
		super.flush();
	}

	@Override
	public void println(String x) {
		super.println(x);
		super.flush();
	}

	@Override
	public void println(Object x) {
		super.println(x);
		super.flush();
	}
	
}
