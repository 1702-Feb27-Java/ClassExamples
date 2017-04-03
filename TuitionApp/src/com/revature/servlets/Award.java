package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.AppDAOImp;

/**
 * Servlet implementation class Award
 */
public class Award extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Award() {
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
		PrintWriter out = response.getWriter();
		
		int appID = Integer.parseInt(session.getAttribute("appID").toString());
		
		AppDAOImp appDAO = new AppDAOImp();
		
		appDAO.awardAsBenco(appID);
		
		session.removeAttribute("appID");
		
		out.println("<html><head>");
        out.println("<title>Servlet Parameter</title>");
        out.println("<link rel='stylesheet' href='" + request.getContextPath() +  "/css/styles1.css' />");
        out.println("</head>");
        out.println("<body><h1>Reimbursement awarded</h1>");
        //out.println(firstname + " " + lastname + " " + username + " " + pass + " " + email + " " + dept);
        out.println("<h2>Redirecting in 5 seconds...</h2>");
        out.println("</body></html>");
        
        response.setHeader("Refresh", "5;url=pendingapps.jsp");
	}

}
