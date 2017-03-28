package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.dao.*;
import com.revature.connect.ConnectionUtil;
import com.revature.service.ServiceDAOImpl;

import javafx.concurrent.WorkerStateEvent;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceDAOImpl DAO = new ServiceDAOImpl();
		
		//doGet(request, response);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String n = request.getParameter("uname");
		String p = request.getParameter("pwd");
		
		if(DAO.EMP_LOGIN(n, p) == true){
			System.out.println("Login and validation successful");
			//need to check the employee role and point them to the page
			if( DAO.Emp_Role_Check(n) == 1 ) {
				//System.out.println("Goes to employee jsp");
				RequestDispatcher rd=request.getRequestDispatcher("Employee.jsp");  
				rd.forward(request,response); 
			}
			
			else if ( DAO.Emp_Role_Check(n) == 2 ) {
				System.out.println("Goes to district supervisor jsp");
				RequestDispatcher rd=request.getRequestDispatcher("Login.html");  
				rd.forward(request,response); 
			}
			
			else if ( DAO.Emp_Role_Check(n) == 3 ) {
				System.out.println("Goes to deparment head jsp");
				RequestDispatcher rd=request.getRequestDispatcher("Login.html");  
				rd.forward(request,response); 	
			}
			
			else if ( DAO.Emp_Role_Check(n) == 4 & DAO.Emp_Role_Check(n) == 1) {
				System.out.println("Goes to Benco jsp");
				RequestDispatcher rd=request.getRequestDispatcher("Login.html");  
				rd.forward(request,response); 
			}
			
		}
		
		else{
			System.out.println("Wrong username and/or password");
			//out.write("Wrong username and/or password");
		    
			
		}
		out.close();
	}

}
