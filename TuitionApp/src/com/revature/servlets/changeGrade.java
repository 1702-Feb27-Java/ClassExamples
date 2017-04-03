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
 * Servlet implementation class changeGrade
 */
public class changeGrade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changeGrade() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		UserClass thisUser = new UserClass();
		thisUser = (UserClass)session.getAttribute("userInfo");

		String finalGrade = request.getParameter("finalGrade").toString();
		String presentation = request.getParameter("presRev").toString();

		AppDAOImp appDAO = new AppDAOImp();
		int appID = Integer.parseInt(session.getAttribute("appID").toString());
		UserDAOImp userDAO = new UserDAOImp();
		int userID = userDAO.getUserIDbyAppID(appID);

		if (finalGrade != null){
			appDAO.changeGrade(appID, finalGrade);
			String notifMes = "Your application no. " + appID + " has been awarded a final grade of " 
					+ finalGrade + " by " + thisUser.getFirstname() + " " + thisUser.getLastname();				
			
			userDAO.addNotif(userID, thisUser, notifMes);
		} else {
			appDAO.changePresentation(appID, presentation);
			
			String notifMes = "Your application no. " + appID + " has been awarded a presentation review of " 
					+ presentation + " by " + thisUser.getFirstname() + " " + thisUser.getLastname();				
			
			userDAO.addNotif(userID, thisUser, notifMes);
		}
		
		session.removeAttribute("appID");

		String destination = "pendingapps.jsp";

		response.sendRedirect(destination);
	}

}
