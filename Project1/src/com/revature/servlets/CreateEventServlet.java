package com.revature.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.trms.EventService;
import com.revature.trms.User;
import com.revature.trms.UserService;

/**
 * Servlet implementation class CreateEventServlet
 */
public class CreateEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		try {
			startDate = sdf.parse(request.getParameter("start"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String startTime = request.getParameter("stime");
		
		Date stopDate = null;
		try {
			stopDate = sdf.parse(request.getParameter("stop"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String location = request.getParameter("location");
		String description = request.getParameter("descr");
		double cost = Double.parseDouble(request.getParameter("cost"));
		String justification = request.getParameter("justify");
		String gradeFormat = request.getParameter("grade");
		String eventType = request.getParameter("etype");
		
		Date timeStamp = null;
		try {
			timeStamp = sdf.parse(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long date1 = startDate.getTime();
		long date2 = timeStamp.getTime();
		long days = Math.abs((date1/date2)/(1000*60*60*24));
		
		String priority = "1";
		if (days < 14 ){
			priority = "2";
		}
		
		String role = "4";
		String roleId = "0";
		
		if (role.equals("4"))
			roleId = "3";
		else if (role.equals("3"))
			roleId = "2"; 
		else if (role.equals("2"))
			roleId = "1";
		HttpSession session = request.getSession();
		int userid = (int)session.getAttribute("userid");
		
		User u = UserService.getUserInfo(userid); 

		EventService.addNewEvent(startDate, startTime, stopDate, location, description,
				cost, justification, gradeFormat, eventType, priority, roleId, userid);
		request.getRequestDispatcher("/Home.jsp").include(request, response);
		}

}
