package com.trms.signupserv;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.trms.dao.TrmsDao;

/**
 * Servlet implementation class Signup
 */
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
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
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String pass = request.getParameter("password");
		String rePass = request.getParameter("retypepassword");
		String email = request.getParameter("email");
		int empID = Integer.parseInt(request.getParameter("empid"));
		HttpSession session = request.getSession();
		boolean signUpSuccess = true;
		
		boolean isEmployee = TrmsDao.isEmp(empID);
		boolean userExists = TrmsDao.userExists(username);
		boolean emailInUse = TrmsDao.emailExists(email);
		boolean passMatch = (pass.equals(rePass));
		boolean empidInUse = TrmsDao.empExists(empID);
		
		//sets to signup success to false if any error triggers
		if(!isEmployee){
			session.setAttribute("isEmployee", isEmployee);
			signUpSuccess = false;
		}
		if(empidInUse){
			session.setAttribute("empidInUse", empidInUse);
			signUpSuccess = false;
		}
		if(userExists){
			session.setAttribute("userInUse", userExists);
			signUpSuccess = false;
		}
		if(emailInUse){
			session.setAttribute("emailInUse", emailInUse);
			signUpSuccess = false;
		}
		if(!passMatch){
			session.setAttribute("passMatch", passMatch);
			signUpSuccess = false;
		}
		
		//will only create account if passes all checks
		if(signUpSuccess){
			TrmsDao.createAccount(username, pass, email, empID);
			RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
			rd.forward(request, response);
		}else{
			response.sendRedirect("signup.jsp");
		}
	
	
	}

}
