package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.pojo.Employee;
import com.revature.service.Service;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String initParamName;
	private String initParamValue;
	private String configParamName;
	private String configParamValue;
	Service service = new Service();
	
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
		Employee emp = service.viewAccount(request.getParameter("username"));
		request.getSession().setAttribute("username", emp.getUsername());
		request.getSession().setAttribute("password", emp.getPassword());
		request.getSession().setAttribute("empid", emp.getEmp_id());
		if(request.getParameter("password").equals(emp.getPassword()) && emp.getRole_id() == 1)
		{
			response.sendRedirect("Dashboard.jsp");
		}
		else if(request.getParameter("password").equals(emp.getPassword()) && emp.getRole_id() == 2)
		{
			response.sendRedirect("DSDashboard.jsp");
		}
		else if(request.getParameter("password").equals(emp.getPassword()) && emp.getRole_id() == 3)
		{
			response.sendRedirect("DHDashboard.jsp");
		}
		//System.out.println(emp.getUsername());
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().append("For User: " + request.getParameter("username"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
