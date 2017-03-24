package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.pojo.UserClass;

/**
 * Servlet implementation class ApplicationServlet
 */
public class ApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationServlet() {
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
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		UserClass thisUser = new UserClass();
		thisUser = (UserClass)session.getAttribute("userInfo");
		
		// pull the userID from session object
		int userID = thisUser.getUserID();
		
		String courseType = request.getParameter("event");
		
		// use startdate to write logic for priority level
		String startdate = request.getParameter("startdate"); 
		
		String enddate = request.getParameter("enddate");
		String hours = request.getParameter("hours");
		String location = request.getParameter("location");
		String cost = request.getParameter("cost");
		String gradingFormat = request.getParameter("gradingFormat");
		String gradeCutoff = request.getParameter("gradeCutoff");
		
		
		// call the AppClass DAO method to add application
		
		out.println("<html><head>");
        out.println("<title>Servlet Parameter</title>");
        out.println("<link rel='stylesheet' href='" + request.getContextPath() +  "/css/styles1.css' />");
        out.println("</head>");
        out.println("<body><h1>You've successfully submitted a reimbursement application!</h1>");
        //out.println(firstname + " " + lastname + " " + username + " " + pass + " " + email + " " + dept);
        out.println("<h2>Redirecting in 5 seconds...</h2>");
        out.println("</body></html>");
        
        // where to redirect based on your account type
        if (thisUser.getRoleID() == 1){
        	response.setHeader("Refresh", "5;url=empaccount.jsp");
        } else if (thisUser.getRoleID() == 2){
        	response.setHeader("Refresh", "5;url=supervisoraccount.jsp");
        } else {
        	response.setHeader("Refresh", "5;url=dept-headaccount.jsp");
        }
	}

}
