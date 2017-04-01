package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.AppDAOImp;

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
		
		double adjusted = Double.parseDouble(request.getParameter("adjusted").toString());
		String reason = request.getParameter("reason");
		
		AppDAOImp appDAO = new AppDAOImp();
		int appID = Integer.parseInt(session.getAttribute("appID").toString());
		
		appDAO.changeAward(appID, adjusted, reason);
		
		session.removeAttribute("appID");
		
		String destination = "pendingapps.jsp";

		response.sendRedirect(destination);
		
	}

}
