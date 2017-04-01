package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.EmployeeDaoImpl;
import com.revature.dao.LookupDaoImpl;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.pojo.Employee;
import com.revature.pojo.Reimbursement;
import com.revature.service.EmployeeService;
import com.revature.service.LookupService;
import com.revature.service.ReimbursementService;

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
	ReimbursementDaoImpl rDao;
       
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
		ReimbursementService rs = new ReimbursementService();
		LookupService ls = new LookupService();
		HttpSession currSession = request.getSession(true);
		
		// Store employee object using Employee Service
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		Employee e = null;
		
		// Check if coming from login page
		if((uname != null) && (pass != null)){
			e = es.loginEmployee(uname, pass);
		} else {
			e = (Employee) currSession.getAttribute("employee");
		}
		
		String nextJSP;
		// in Servlet
		if(e != null) {
			// Collect pending reimbursements related to Employee
			ArrayList<Reimbursement> pendingReimbursements = rs.getPendingReimbursementsForEmployee(e);
			// TODO: Collect awarded reimbursements related to Employee
			// ArrayList<Reimbursement> awardedReimbursements = rs.getAwardedReimbursementsForEmployee();
			currSession.setAttribute("employee", e);
			// Store id username password strings of employee in session
			currSession.setAttribute("employeeId", e.getEmployeeId());
			currSession.setAttribute("username", e.getUsername());
			currSession.setAttribute("password", e.getPassword());
			
			// Store firstname lastname strings of employee in session
			String firstname = e.getFirstName();
			String lastname = e.getLastName();
			
			currSession.setAttribute("firstname", firstname);
			currSession.setAttribute("lastname", lastname);
			
			// Store arraylist of pending reimbursements in session scope
			currSession.setAttribute("pendingReimbursements", pendingReimbursements);
			// TODO: Store arraylist of awarded reimbursements in session scope
			// currSession.setAttribute("awardedReimbursements", awardedReimbursements);
			
			// TODO: Refactor project to call service methods from pojos instead of using hashmaps
			
			// If employee an approver
				// Store arraylist of approvable reimbursements in session scope
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
