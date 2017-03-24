package com.revature.servlets;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.UserDAOImp;
import com.revature.pojo.UserClass;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static UserDAOImp userDAO = new UserDAOImp();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
	 * @see Servlet#destroy()
	 */

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// protected void service(HttpServletRequest request, HttpServletResponse
	// response) throws ServletException, IOException {
	// // TODO Auto-generated method stub
	// }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		UserClass thisUser = new UserClass();
		Hashtable<String, String> unamePW = new Hashtable<String, String>();
		unamePW = userDAO.getUsernamePW();

		String uname = request.getParameter("usr");
		String pw = request.getParameter("pwd");

		int it = 0;
		// store all keys into a set
		Set<String> keys = unamePW.keySet();
		for (String s : keys) {
			it++;
			if (uname.equals(s)) {
				if (pw.equals(unamePW.get(s))) {
					// both username and password entered are correct
					
					thisUser = userDAO.getUserByUsername(uname);
					
					// logic for logging into different account types
					if (thisUser.getRoleID() == 1){  // employee
						session.setAttribute("userInfo", thisUser);
						response.sendRedirect("empaccount.jsp");
						return;
					} else if (thisUser.getRoleID() == 2){ // supervisor
						session.setAttribute("userInfo", thisUser);
						response.sendRedirect("supervisoraccount.jsp");
						return;
					} else { // department head
						session.setAttribute("userInfo", thisUser);
						response.sendRedirect("dept-headaccount.jsp");
						return;
					}
					
					
				} else {
					request.setAttribute("error", "You have entered an incorrect username or password. Try again.");
					request.getRequestDispatcher("index.jsp").forward(request, response);

				}
			} 
			else {
				if (it < unamePW.size()) {
					continue;
				} else {
					request.setAttribute("error", "You have entered an incorrect username or password. Try again.");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
			}

		}
	}

}
