package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.EmployeeDaoImpl;
import com.revature.service.EmployeeService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String initParamName;
	private String initParamValue;
	private String contextParamName;
	private String contextParamValue;
	EmployeeDaoImpl eDao;
	EmployeeService es;
       
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
		es = new EmployeeService();
		PrintWriter out = response.getWriter();
		out.println("in LoginServlet");
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		out.println(es.login(uname, pass));
		out.println("Employee Service Login method: " + es.login(uname, pass));

		///// SERVICE TEST WORKS FINE
		///// PROBLEM ISOLATED TO LOGINSERVLET
		// doGet retrieves request/response params fine
		
		// forward to next jsp
		String nextJSP = "/reimbursement.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
