package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.UserDAOImp;
import com.revature.pojo.UserClass;

/**
 * Servlet implementation class SignupServlet
 */
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = request.getParameter("username");
		String pass = request.getParameter("password");
		String email = request.getParameter("email");
		String dept = request.getParameter("dept");
		String roleID = request.getParameter("role");
		
		UserClass signupUser = new UserClass();
		signupUser.setFirstname(firstname);
		signupUser.setLastname(lastname);
		signupUser.setUsername(username);
		signupUser.setPw(pass);
		signupUser.setEmail(email);
		
		if (dept.equals("Marketing")){
			signupUser.setDeptID(1);
		} else if (dept.equals("Human Resources")){
			signupUser.setDeptID(2);
		} else {
			signupUser.setDeptID(3);
		}
		
		if (roleID.equals("Employee")){
			signupUser.setRoleID(1);
		} else if (roleID.equals("Supervisor")){
			signupUser.setRoleID(2);
		} else {
			signupUser.setRoleID(3);
		}
		
		UserDAOImp userDAO = new UserDAOImp();
		userDAO.addUser(signupUser);
				
        out.println("<html><head>");
        out.println("<title>Servlet Parameter</title>");
        out.println("<link rel='stylesheet' href='" + request.getContextPath() +  "/css/styles1.css' />");
        out.println("</head>");
        out.println("<body><h1>You registered!</h1>");
        //out.println(firstname + " " + lastname + " " + username + " " + pass + " " + email + " " + dept);
        out.println("<h2>Redirecting in 5 seconds...</h2>");
        out.println("</body></html>");
        
        response.setHeader("Refresh", "5;url=index.jsp");
	}

}
