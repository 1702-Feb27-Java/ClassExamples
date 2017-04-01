package com.revature.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.DAOImpl;
import com.revature.trms.Event;
import com.revature.trms.EventService;

/**
 * Servlet implementation class DeleteEvent
 */
public class DeleteEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEvent() {
        super();
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("userid");
		
		String item = request.getParameter("event");
		
		int eventId = Integer.parseInt(request.getParameter("eventNum"));
		
		String type = request.getParameter("EventType");
		String pri = request.getParameter("Priority");
		String loca = request.getParameter("Location");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date start = null;
		try {
			start = sdf.parse(request.getParameter("Start"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		double cost = Double.parseDouble(request.getParameter("Cost"));
		
		Date stop = null;
		try {
			stop = sdf.parse(request.getParameter("Stop"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String time = request.getParameter("STime");
		String grade = request.getParameter("Grade");
		String descr = request.getParameter("descr");
		String justify = request.getParameter("justify");
		
		Event ev = new Event(eventId,start, time, stop, loca, descr, cost, justify, grade, type, pri);
		
		if (item.equals("delete")){
			EventService.deleteEvent(userId, eventId, cost);
		}else {
			DAOImpl.editEvents(ev);
		}
		request.getRequestDispatcher("/Home.jsp").include(request, response);
	}
}
