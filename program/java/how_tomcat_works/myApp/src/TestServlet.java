import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

public class TestServlet implements Servlet {
	
	private static Logger log = Logger.getLogger(TestServlet.class);

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Servlet Init!");
	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("Servlet Run!");
		PrintWriter writer = res.getWriter();
		Enumeration<?> names = req.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String)names.nextElement();
			log.info(name + " : " + req.getParameter(name));
		}
		writer.println("Test");
		writer.println("OK!");
		writer.flush();
	}

	@Override
	public String getServletInfo() {
		return null;
	}

	@Override
	public void destroy() {
		System.out.println("Servlet Destroy!");
	}
	
}
