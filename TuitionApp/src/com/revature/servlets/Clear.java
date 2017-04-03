package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.UserDAOImp;
import com.revature.pojo.NotifClass;
import com.revature.pojo.UserClass;

/**
 * Servlet implementation class Clear
 */
public class Clear extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Clear() {
        super();
        // TODO Auto-generated constructor stub
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
		
		HttpSession session = request.getSession();

		UserClass thisUser = new UserClass();
		thisUser = (UserClass)session.getAttribute("userInfo");
		UserDAOImp userDAO = new UserDAOImp();
		
		@SuppressWarnings("unchecked")
		ArrayList<NotifClass> notifications = (ArrayList<NotifClass>)session.getAttribute("notif");
		
		for (NotifClass n : notifications){
			userDAO.clearNotification(n);
		}
		
		session.removeAttribute("notif");
		
		if (thisUser.getRoleID() == 1){
			request.getRequestDispatcher("empaccount.jsp").forward(request, response);
		} else if (thisUser.getRoleID() == 2){
			request.getRequestDispatcher("supervisoraccount.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("dept-headaccount.jsp").forward(request, response);
		}
	}

}
