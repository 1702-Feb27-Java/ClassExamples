package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.DAOImpl;

/**
 * Servlet implementation class ValidateLogin
 */
public class ValidateLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateLogin() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("uname");
		String password = request.getParameter("password");
		
		int uid = DAOImpl.loginVerification(username, password);
				
		if (uid != 0){
			HttpSession session = request.getSession();
			session.setAttribute("userid", uid);
			request.getRequestDispatcher("/Home.jsp").include(request, response);
		} else{
			request.getRequestDispatcher("/index.jsp").include(request, response);
		}
	}
}
