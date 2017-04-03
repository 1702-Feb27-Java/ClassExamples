package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.AppDAOImp;
import com.revature.dao.UserDAOImp;
import com.revature.pojo.UserClass;

/**
 * Servlet implementation class AddInfo
 */
public class AddInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddInfo() {
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
		int appID = Integer.parseInt(request.getParameter("appID"));
		String message = request.getParameter("info");
		int flag = (Integer)session.getAttribute("flag");

		AppDAOImp appDAO = new AppDAOImp();
		
		appDAO.infoRequestAsManager(appID, thisUser, message);
	
		UserDAOImp userDAO = new UserDAOImp();
		int userID = userDAO.getUserIDbyAppID(appID);
		
		String notifMes = thisUser.getFirstname() + " " + thisUser.getLastname() + " has requested more information on app no. " 
				+ appID + " with the message: " + message;
		
		userDAO.addNotif(userID, thisUser, notifMes);
		
		String destination = "/pendingapps.jsp";

        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        rd.forward(request, response);
	}

}
