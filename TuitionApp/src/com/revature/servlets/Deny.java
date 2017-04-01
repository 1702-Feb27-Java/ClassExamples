package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.AppDAOImp;
import com.revature.pojo.UserClass;

/**
 * Servlet implementation class Deny
 */
public class Deny extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deny() {
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
				int apprLvl = thisUser.getRoleID()- 2;
				appDAO.denyAsManager(appID, apprLvl, thisUser, message);
			} else {
				int apprLvl = thisUser.getRoleID() - 1;
				appDAO.denyAsManager(appID, apprLvl, thisUser, message);
			}
		} else { // in benco
			appDAO.denyAsManager(appID, 3, thisUser, message);
		}

		String destination = "/approval.jsp";

        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        rd.forward(request, response);
	}

}
