package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.service.EmployeeService;

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
		EmployeeService serveEmp = new EmployeeService();
		
		HttpSession ses = request.getSession();
		int emp_id = (int) ses.getAttribute("uId");
		String event = request.getParameter("event");
		String eventDate = request.getParameter("eventDate");
		String time = request.getParameter("time");
		String location = request.getParameter("location");
		String location2 = request.getParameter("location2");
		String description = request.getParameter("description");
		String cost = request.getParameter("cost");
		String gradingId = request.getParameter("gradingId");
		String gradingId2 = request.getParameter("gradingId2");
		String typeOfEvent = request.getParameter("typeOfEvent");
		
		
		
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		Date date = new Date();
		Date todaysDate = new Date();
		try {
			date = format.parse(eventDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int daysApart = (int)((date.getTime() - todaysDate.getTime()) / (1000*60*60*24l));

		PrintWriter out = response.getWriter();
		
		out.println("<html><body>" +
					"DATE TEST " + todaysDate + "<br>" +
					"DATEUUU CONVERTUUUUU : " + date + "<br>" +
					"DAYS APARTUUU : " + daysApart + "<br>" + 
						"emp_id: " + emp_id + "<br>"	+ 
						"event: " + event + "<br>" +
						"eventDate: " + eventDate + "<br>" +
						"time: " + time + "<br>");
		
		if(location2.length() != 0){
			out.println("location2: " + location2 + "<br>");
		}
		else{
			out.println("location: " + location + "<br>");
		}
		
		out.println("description: " + description + "<br>" +
						"cost: " + cost + "<br>");
						
		if(gradingId2.length() != 0){
			out.println("gradingId2: " + gradingId2 + "<br>" );
		}
		else{
			out.println("gradingId: " + gradingId + "<br>" );
		}
		out.println("typeOfEvent: " + typeOfEvent + "<br>" +
						"</body></html>"
				);
		/*out.println(serveEmp.applyForReimbursement(emp_id, event, eventDate, time, location, formDate, description, cost, 
							gradingId, typeOfEventId, urgentId, approvalStepId, approvalCutoff));
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
