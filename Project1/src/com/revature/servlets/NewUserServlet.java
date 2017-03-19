package com.revature.servlets;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		int roleid = Integer.parseInt(request.getParameter("role"));
		int deptid = Integer.parseInt(request.getParameter("dept"));
		int supid = Integer.parseInt(request.getParameter("supname"));
		UserService.addNewUser(first, last, name, pwd, 
				mail, roleid, deptid, supid);
		request.getRequestDispatcher("/index.jsp").include(request, response);
	}
}
