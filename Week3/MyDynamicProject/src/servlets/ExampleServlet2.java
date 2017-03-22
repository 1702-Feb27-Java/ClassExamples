package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ExampleServelet2
 */
public class ExampleServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String initParamName;
	private String initParamValue;
	
	private String contextParamName;
	private String contextParamValue;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExampleServlet2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		Enumeration<String> initParamNames = config.getInitParameterNames();
		initParamName = initParamNames.nextElement();
		initParamValue = config.getInitParameter(initParamName);
		
		contextParamName = config.getServletContext().getInitParameterNames().nextElement();
		contextParamValue = config.getServletContext().getInitParameter("ApplicationWide");
		System.out.println("Load on startup: 1 - INIT");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Destroy");
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// protected void service(HttpServletRequest request, HttpServletResponse
	// response) throws ServletException, IOException {
	// // TODO Auto-generated method stub
	// System.out.println("Service");
	// }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());

		// User a printwrier object to dynamically write a webpage
		PrintWriter out = response.getWriter();

		Enumeration<String> heads = request.getHeaderNames();

		out.println(
				"<html>\n" 
				+ "<body>\n" 
				+ "Username passed: " + request.getParameter("uname") + "\n"
				+ "Password passed: " + request.getParameter("pwd") + "\n"
				+ "<br><table border=\"solid\"><thead><tr><th>Header Name</th><th>Header Value</th></tr></thead>");

		while(heads.hasMoreElements()) {
			String s = heads.nextElement();
			out.println("<tr><td>" + s + "</td><td>" + request.getHeader(s) + "</td></tr>");
		}

		out.println("</table>" + "<hr>" + "ServletConfig parameters: <br>" + 
		initParamName + ": " + initParamValue + "<br>" + 
				contextParamName + ": " + contextParamValue + "<br>" +
				"</body>\n" + "</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
