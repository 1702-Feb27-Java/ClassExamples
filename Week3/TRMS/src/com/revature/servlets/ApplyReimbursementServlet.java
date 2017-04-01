package com.revature.servlets;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.pojo.Grade;
import com.revature.pojo.Reimbursement;
import com.revature.service.LookupService;
import com.revature.service.ReimbursementService;

/**
 * Servlet implementation class ApplyReimbursementServlet
 */
public class ApplyReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ReimbursementService rs = new ReimbursementService();
    LookupService ls = new LookupService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyReimbursementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// parse input information (Strings) and store in reimbursement
		System.out.println("in doGet");
		Reimbursement r = new Reimbursement();
		
		HttpSession currSession = request.getSession();
		// session
		
		// employee_id
		r.setEmployeeId((int) currSession.getAttribute("employeeId"));
		// location
		// other location
		r.setLocation(request.getParameter("location"));
		// event_title from event_title_id
		if(request.getParameter("otherEventTitle").isEmpty()) {
			System.out.println(request.getParameter("event-type"));
			r.setEventTitleId((Integer.parseInt(request.getParameter("event-type"))));
		} else
			ls.insertEvent(request.getParameter("otherEventTitle"));
		// event_description
		r.setEventDesc(request.getParameter("eventDesc"));
		// work_justification
		r.setWorkJust(request.getParameter("workJust"));
		// grade from grade_id
		if(request.getParameter("otherGradeFormat").isEmpty())
			r.setGradeId(Integer.parseInt(request.getParameter("grade-format")));
		else {
			String gradeFormat = request.getParameter("otherGradeFormat");
			String passingGrade = request.getParameter("otherGradePassingGrade");
			boolean presReq = Boolean.parseBoolean(request.getParameter("otherGradePresReq"));
			Grade g = new Grade(gradeFormat, passingGrade, presReq);
			// return new grade_id
			r.setGradeId(ls.insertGrade(g));
		}
		try {
			// format date string to java.util.Date type
			SimpleDateFormat parser=new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date utilDate = parser.parse(request.getParameter("eventDate"));
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getDate());
			r.setEventDate(sqlDate);
			// format cost string to double with 2 decimal places
			DecimalFormat df = new DecimalFormat("#.00");
			r.setCost((df.parse(request.getParameter("costOfEvent")).doubleValue()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(r);
		rs.insertReimbursement(r);
		
		String nextJSP = "/reimbursement_completion.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(nextJSP);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("in doPost");
		doGet(request, response);
	}

}
