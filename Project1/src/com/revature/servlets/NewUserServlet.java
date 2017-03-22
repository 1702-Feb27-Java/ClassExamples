package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.DAOImpl;
import com.revature.trms.UserService;

/**
 * Servlet implementation class NewUserServlet
 */
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String first = request.getParameter("fname");
		String last = request.getParameter("lname");
		String name = request.getParameter("uname");
		String pwd = request.getParameter("password");
		String mail = request.getParameter("email");
		String role = request.getParameter("role");
		String dept = request.getParameter("dept");
		int supid = Integer.parseInt(request.getParameter("supname"));
		
		boolean verify = DAOImpl.verifyInfo(name);
		
		if (verify){
			request.getRequestDispatcher("/NewUser.jsp").include(request, response);
		} else {
			UserService.addNewUser(first, last, name, pwd, 
					mail, role, dept, supid);
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("http://localhost:8085/TRMS/index.jsp");
		}
	}
}
