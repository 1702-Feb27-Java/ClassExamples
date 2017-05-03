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
 * Servlet implementation class EditUserServlet
 */
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserServlet() {
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
		
		UserService.editUser(first, last, name, pwd, 
					mail, role, dept, supid);
		request.getRequestDispatcher("/Home.jsp").include(request, response);
	}
}
