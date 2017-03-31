package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;

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
		String item = request.getParameter("event");
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("userid");
		ArrayList<Event> eventList = (ArrayList<Event>)session.getAttribute("eventList");
		int eventId = Integer.parseInt(request.getParameter("eventNum"));
		int eventNewId = (int)session.getAttribute("eventNewId");
		double cost = Double.parseDouble(request.getParameter("Cost"));

		if (item.equals("delete")){
			EventService.deleteEvent(userId, eventId, cost);
		}else {
			EventService.editEvent(eventList, eventNewId);
		}
		request.getRequestDispatcher("/Home.jsp").include(request, response);
	}
}
