package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.EmployeeDaoImpl;
import com.revature.pojo.Employee;
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
		EmployeeService es = new EmployeeService();
		HttpSession currSession = request.getSession(true);
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		
		Employee e = es.loginEmployee(uname, pass);
		String nextJSP;
		
		// TODO: Store Reimbursement info related to employee
		// in Servlet
		if(e != null) {
			currSession.setAttribute("employee", e);
			
			currSession.setAttribute("username", e.getUsername());
			currSession.setAttribute("password", e.getPassword());
			
			String firstname = e.getFirstName();
			String lastname = e.getLastName();
			
			currSession.setAttribute("firstname", firstname);
			currSession.setAttribute("lastname", lastname);
			nextJSP = "/employee_menu.jsp";
		}
		else {
			currSession.invalidate();
			System.out.println("changing nextJSP to home");
			nextJSP = "/home.jsp";
		}
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
