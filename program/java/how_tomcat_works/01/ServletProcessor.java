package net.saisimon.tomcat.server;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

public class ServletProcessor {
	
	public void process(Request request, Response response) {
		String uri = request.getUri();
		int idx = uri.indexOf('?');
		String servletName = uri.substring(uri.lastIndexOf('/') + 1, idx == -1 ? uri.length() : idx);
		URLClassLoader loader = null;
		try {
			URL[] urls = new URL[1];
			URLStreamHandler handler = null;
			File classPath = new File(System.getProperty("user.dir"));
			String repo = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
			urls[0] = new URL(null, repo, handler);
			loader = new URLClassLoader(urls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Class<?> clazz = null;
		try {
			clazz = loader.loadClass(servletName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Servlet servlet = null;
		RequestFacade req = new RequestFacade(request);
		ResponseFacade resp = new ResponseFacade(response);
		try {
			servlet = (Servlet) clazz.newInstance();
			servlet.service(req, resp);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
