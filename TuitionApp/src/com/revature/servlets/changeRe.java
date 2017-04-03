package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.AppDAOImp;
import com.revature.dao.UserDAOImp;
import com.revature.pojo.UserClass;

/**
 * Servlet implementation class changeRe
 */
public class changeRe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changeRe() {
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
		
		double adjusted = Double.parseDouble(request.getParameter("adjusted").toString());
		String reason = request.getParameter("reason");
		
		AppDAOImp appDAO = new AppDAOImp();
		int appID = Integer.parseInt(session.getAttribute("appID").toString());
		
		appDAO.changeAward(appID, adjusted, reason);
		
		UserDAOImp userDAO = new UserDAOImp();
		int userID = userDAO.getUserIDbyAppID(appID);
		
		String notifMes = "Your application no. " + appID + " has had the reimbursement amount adjusted to $" 
		+ adjusted + " by " + thisUser.getFirstname() + " " + thisUser.getLastname() 
		+ " because of the following reason: " + reason;
		
		userDAO.addNotif(userID, thisUser, notifMes);
		
		session.removeAttribute("appID");
		
		String destination = "pendingapps.jsp";

		response.sendRedirect(destination);
		
	}

}
