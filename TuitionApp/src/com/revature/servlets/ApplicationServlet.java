package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.AppDAOImp;
import com.revature.pojo.AppClass;
import com.revature.pojo.CDTClass;
import com.revature.pojo.GradingClass;
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
		AppClass thisApp = new AppClass();
		CDTClass cdt = new CDTClass();
		GradingClass grading = new GradingClass();
		thisUser = (UserClass)session.getAttribute("userInfo");
		
		// pull the userID from session object
		int userID = thisUser.getUserID();
		
		String courseType = request.getParameter("event");
		
		// use startdate to write logic for priority level
		String startdate = request.getParameter("startdate"); 
		String enddate = request.getParameter("enddate");
		String hours = request.getParameter("hours");
		
		// here we will calculate if the application is priority is urgent
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date(); // get current day
		String today = sdf.format(date);
		Date start = null;
		Date todaysDate = null;
		
		try {
			todaysDate = new SimpleDateFormat("MM-dd-yyyy").parse(today);
			start = new SimpleDateFormat("MM-dd-yyyy").parse(startdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long diff = Math.abs(todaysDate.getTime() - start.getTime());
		long diffDays = diff / (24 * 60 * 60 * 1000);
		
		// depending on the # of days between today and the start date
		// we will mark the priority for this application
		
		if (diffDays < 14){ // urgent
			thisApp.setPriority(2);
		} else // normal
			thisApp.setPriority(1);
		
		cdt.setStartdate(startdate);
		cdt.setEnddate(enddate);
		cdt.setHoursPerWeek(Integer.parseInt(hours));
		
		String location = request.getParameter("location");
		String cost = request.getParameter("cost");
		String just = request.getParameter("justification");
		String gradingFormat = request.getParameter("gradingFormat");
		String gradeCutoff = request.getParameter("gradeCutoff");
		
		
		if (gradingFormat.equals("Pass/Fail"))
			grading.setGradingFormatID(1);
		else if (gradingFormat.equals("A-F"))
			grading.setGradingFormatID(2);
		else if (gradingFormat.equals("Percentage"))
			grading.setGradingFormatID(3);
		else 
			grading.setGradingFormatID(4);
		
		grading.setGradeCutoff(gradeCutoff);
				
		thisApp.setUserID(userID);
		
		if (courseType.equals("University Courses"))
			thisApp.setEventID(1);
		else if (courseType.equals("Seminars")) 
			thisApp.setEventID(2);
		else if (courseType.equals("Certification Prep Classes")) 
			thisApp.setEventID(3);
		else if (courseType.equals("Certification")) 
			thisApp.setEventID(4);
		else if (courseType.equals("Technical Training")) 
			thisApp.setEventID(5);
		else 
			thisApp.setEventID(6);
		
		thisApp.setDateCreated(today);
		thisApp.setStatusID(1);
		thisApp.setLoc(location);
		thisApp.setTotalCost(Double.parseDouble(cost));
		thisApp.setJustification(just);
		
		AppDAOImp appDAO = new AppDAOImp();
		appDAO.addApp(thisUser, thisApp, cdt, grading);
		
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
        	response.setHeader("Refresh", "5; url=empaccount.jsp");
        } else if (thisUser.getRoleID() == 2){
        	response.setHeader("Refresh", "5; url=supervisoraccount.jsp");
        } else {
        	response.setHeader("Refresh", "5; url=dept-headaccount.jsp");
        }
	}

}