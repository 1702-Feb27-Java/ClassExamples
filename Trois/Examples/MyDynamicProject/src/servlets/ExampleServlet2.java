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
 * Servlet implementation class ExampleServlet2
 */
public class ExampleServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String initParamName, contParamName;
	private String initParamValue, contParamValue;
       
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
		Enumeration<String> iP = config.getInitParameterNames();
		initParamName = iP.nextElement();
		initParamValue = config.getInitParameter(initParamName);
		
		contParamName = config.getServletContext().getInitParameterNames().nextElement();
		contParamValue = config.getServletContext().getInitParameter(contParamName);
		
		System.out.println("Load on startup: 1 - INIT()");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("DESTROY()");
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		//Use a printwriter object to dynamically write a webpage.
		PrintWriter out = response.getWriter();
		
		Enumeration<String> heads = request.getHeaderNames();
		out.append(
				"<html>\n"
				+ "<body>\n"
				+ "Username passed: " + request.getParameter("uname") + "\n"
				+ "Password passed: " + request.getParameter("pwd") + "\n"
				+ "<br><table border=\"solid\"><thead><tr><th>Header Name</th><th>Header Value</th></tr></thead> ");
		
			while(heads.hasMoreElements()){
				String s = heads.nextElement();
				out.println(
						"<tr><td>" + s + "</td><td>" + request.getHeader(s) + "</td></tr>"
						);
			}
			out.println( 
				"</table>"  +
				"<hr>" +
				"ServletConfig parameters: <br>" +
				initParamName + ": " + initParamValue + "<br>" +
				contParamName + ": " + contParamValue + "<br>" +
				"</body>\n"  +
				"</html>"
				);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
