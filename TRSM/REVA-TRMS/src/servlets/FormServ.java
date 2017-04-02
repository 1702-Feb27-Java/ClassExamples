package servlets;

import java.io.IOException;

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
		temp.setEvent_date(request.getParameter("eventDate"));
		temp.setEventLength(request.getParameter("eventLength"));
		temp.setGrade(new Integer(request.getParameter("grade")));
		temp.setJustification(request.getParameter("eventJust"));
		temp.setLocation(request.getParameter("eventLocation"));
		temp.setNumDay(0);
		
		
		
		Service serv = new Service();
		HttpSession sess = request.getSession();
		Employee e = ((Employee)sess.getAttribute("employee"));
		int cost = new Integer(request.getParameter("eventCost")).intValue();
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
		
		int isUrgent = 0;
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
