package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String initParamName;
	private String initParamValue;
	private String contextParamName;
	private String contextParamValue;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();

		Enumeration<String> heads = request.getHeaderNames();

		out.println("<html>\n" + "<body>\n" + "Username passed: " + request.getParameter("uname") + "\n"
				+ "Password passed: " + request.getParameter("pwd") + "\n"
				+ "<br><table border=\"solid\"><thead><tr><th>Header Name</th><th>Header Value</th></tr></thead>");

		while(heads.hasMoreElements()){
			String s = heads.nextElement();
			out.println(
					"<tr><td>" + s + "</td><td>" + request.getHeader(s) + "</td></tr>"
					);
		}
		
		out.println(
				"</table>"
				+ "<hr>"
				+ "ServletConfig parameters: <br>"
				+ initParamName + ": " + initParamValue + "<br>"
				+ contextParamName + ": " + contextParamValue + "<br>"
				+ "</hr>"
				+ "</body>\n"
				+ "</html>"
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
