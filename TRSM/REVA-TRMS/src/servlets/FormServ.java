package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import objects.Employee;
import objects.Reimburse;
import service.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FormServ
 */
public class FormServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Reimburse temp = new Reimburse();
		//temp.setCost(new Integer(request.getParameter("eventCost")));
		temp.setCourseID(new Integer(request.getParameter("courseType")));
		temp.setDescription(request.getParameter("eventDescription"));
		
		
		
		
		String input = request.getParameter("eventDate");
		int isUrgent = 0;
		try {
		    DateTimeFormatter formatter =
		                      DateTimeFormatter.ofPattern("MM/dd/yyyy");
		    LocalDate date = LocalDate.parse(input, formatter);
		   // System.out.printf("%s%n", date);
		    LocalDate today = LocalDate.now();
//			LocalDate birthday = LocalDate.of(2017, Month.DECEMBER, 2);
			long p2 = ChronoUnit.DAYS.between(today, date);
			if(p2 <= 7){
				HttpSession ses = request.getSession();
				ses.setAttribute("formError", "request must be submitted more than a week from start date of event");
				HttpServletRequest req = (HttpServletRequest)request;
				HttpServletResponse res =(HttpServletResponse)response;
				RequestDispatcher rd;
				rd = req.getRequestDispatcher("form.jsp");
				rd.forward(req, res);			
				return;
			}
			
			if(p2 <= 14){
				isUrgent = 1;
			}
			
			
		}
		catch (DateTimeParseException exc) {
			HttpSession ses = request.getSession();
			ses.setAttribute("formError", "date is incorrectly formated");
			HttpServletRequest req = (HttpServletRequest)request;
			HttpServletResponse res =(HttpServletResponse)response;
			RequestDispatcher rd;
			rd = req.getRequestDispatcher("form.jsp");
			rd.forward(req, res);			
			return;
		}
		
		
		
		
		
		temp.setEvent_date(request.getParameter("eventDate"));
		temp.setEventLength(request.getParameter("eventLength"));
		temp.setGrade(new Integer(request.getParameter("grade")));
		temp.setJustification(request.getParameter("eventJust"));
		temp.setLocation(request.getParameter("eventLocation"));
		temp.setNumDay(0);
		
		
		
		Service serv = new Service();
		HttpSession sess = request.getSession();
		Employee e = ((Employee)sess.getAttribute("employee"));
		int cost = 0;
		try{
			cost = new Integer(request.getParameter("eventCost")).intValue();
		}
		catch (Exception ex){
			HttpSession ses = request.getSession();
			ses.setAttribute("formError", "cost field is incorrectly formated");
			HttpServletRequest req = (HttpServletRequest)request;
			HttpServletResponse res =(HttpServletResponse)response;
			RequestDispatcher rd;
			rd = req.getRequestDispatcher("form.jsp");
			rd.forward(req, res);			
			return;
		}
		int courseType = new Integer(request.getParameter("courseType")).intValue();
		
		if(courseType == 1){
			cost *= .8;
		}
		if(courseType == 2){
			cost *= .6;
		}
		if(courseType == 3){
			cost *= .75;
		}
		if(courseType == 4){
			cost *= 1;
		}
		if(courseType == 5){
			cost *= .9;
		}
		if(courseType == 6){
			cost *= .3;
		}
		
		temp.setCost(cost);
		
		//update pending reimbursements
		e.setPending(cost + e.getPending());
		serv.updatePending(e.getUserName(), e.getPending());
		
		String str = ((Employee)sess.getAttribute("employee")).getUserName();
		
		
		serv.addReimburseRequest(str, temp, e, isUrgent);
		
		
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res =(HttpServletResponse)response;
		RequestDispatcher rd;
		rd = req.getRequestDispatcher("submitPage.html");
		rd.forward(req, res);			
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	

}
