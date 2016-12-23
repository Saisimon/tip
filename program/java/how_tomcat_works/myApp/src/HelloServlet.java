import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloServlet implements Servlet {

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
		writer.println("Hello");
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
