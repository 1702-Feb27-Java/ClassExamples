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
 * Servlet implementation class ToApprove
 */
public class ToApprove extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToApprove() {
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
		String message = request.getParameter("message");
		int flag = (Integer)session.getAttribute("flag");

		AppDAOImp appDAO = new AppDAOImp();
		
		//System.out.println(thisUser.getDeptID());
		// if in depts not in benco
		if (thisUser.getDeptID() == 1 || thisUser.getDeptID() == 2) {
			if (flag > 0) { // if flag indicates there's no supervisors in this
							// dept
				int apprLvl = thisUser.getRoleID() - 2;
				appDAO.approveAsManager(appID, apprLvl, thisUser, message);
				appDAO.updateAsManager(appID, thisUser.getRoleID());

			} else {
				int apprLvl = thisUser.getRoleID() - 1;
				appDAO.approveAsManager(appID, apprLvl, thisUser, message);
				appDAO.updateAsManager(appID, thisUser.getRoleID());
			}
		} else { // in benco
			appDAO.approveAsBenco(appID, 3, thisUser, message);

		}

		UserDAOImp userDAO = new UserDAOImp();
		int userID = userDAO.getUserIDbyAppID(appID);
		
		String notifMes = "Your application no. " + appID + " has been approved by " 
		+ thisUser.getFirstname() + " " + thisUser.getLastname() + " with the message: " + message;
		
		userDAO.addNotif(userID, thisUser, notifMes);
		
		String destination = "/approval.jsp";

        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        rd.forward(request, response);
	}

}
