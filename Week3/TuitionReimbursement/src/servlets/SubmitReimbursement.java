package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SubmitReimbursement
 */
public class SubmitReimbursement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitReimbursement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String event = request.getParameter("event");
		String eventDate = request.getParameter("eventDate");
		String time = request.getParameter("time");
		String location = request.getParameter("location");
		String location2 = request.getParameter("location2");
		String description = request.getParameter("description");
		String cost = request.getParameter("cost");
		String gradingId = request.getParameter("gradingId");
		String typeOfEvent = request.getParameter("typeOfEvent");
		
		PrintWriter out = response.getWriter();
		out.println(
				"<html><body>" +
						"event: " + event + "<br>" +
						"eventDate: " + eventDate + "<br>" +
						"time: " + time + "<br>" +
						"location: " + location + "<br>" +
						"location2: " + location2 + "<br>" +
						"description: " + description + "<br>" +
						"cost: " + cost + "<br>" +
						"gradingId: " + gradingId + "<br>" +
						"typeOfEvent: " + typeOfEvent + "<br>" +
						"</body></html>"
				);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
