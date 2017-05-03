package com.trms.loginserv;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.trms.dao.TrmsDao;

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
		// TODO Auto-generated method stub
		boolean logInSuccess = false;
		String username = request.getParameter("username");
		System.out.println(username);
		String password = request.getParameter("password");
		logInSuccess = TrmsDao.passMatch(username, password);
		System.out.println(password);
		HttpSession session = request.getSession();
		
		if(logInSuccess){
			session.setAttribute("username", username);
			session.setAttribute("loggedIn", logInSuccess);
			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		}else{
			session.setAttribute("loggedIn", logInSuccess);
			response.sendRedirect("logIn.jsp");
		}
		
	}

}
