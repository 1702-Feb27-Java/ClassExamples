package servlet.reimbursement;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.model.Reimbursement;
import database.model.User;
import database.service.Service;

/**
 * Servlet implementation class NewReimbursement
 */
public class NewReimbursement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewReimbursement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
		request.setAttribute("maxReimbursement", Service.getInstance().getMaxReimbursementLeft(loggedInUser));
		RequestDispatcher rd = request.getRequestDispatcher("newReimbursement.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String desc = request.getParameter("reimburse-desc");
		String loc = request.getParameter("reimburse-loc");
		String costString = request.getParameter("reimburse-cost");
		
		String eventTypeString =  request.getParameter("reimburse-event-type");
		String gradeFormatString = request.getParameter("reimburse-grade-format");
		String startDateString = request.getParameter("reimburse-date-start");
		String endDateString = request.getParameter("reimburse-date-end");
		String customGradingFormat = request.getParameter("custom-grading-format");
		User user = (User) request.getSession().getAttribute("loggedInUser");
			
		request.setAttribute("reimburse-cost", costString);
		request.setAttribute("reimburse-desc", desc);
		request.setAttribute("reimburse-loc", loc);
		request.setAttribute("reimburse-event-type", eventTypeString);
		request.setAttribute("reimburse-grade-format", gradeFormatString);
		request.setAttribute("reimburse-date-start", startDateString);
		request.setAttribute("reimburse-date-end", endDateString);
		request.setAttribute("custom-grading-format", customGradingFormat);
		
		
		Double cost = null;
		Integer gradeFormatId = null;
		Integer eventTypeId = null;
		Date startDate = null;
		Date endDate = null;
		
		boolean hasError = false;
		try{
		cost = Double.parseDouble(costString);
		//request.setAttribute("callback-cost-missing", false);
		} catch (NumberFormatException e){
			request.setAttribute("callback-cost-missing", true);
			hasError = true;
		}
		try{
			gradeFormatId = Integer.parseInt(gradeFormatString);
			//request.setAttribute("callback-gradeFormat-missing", false);
		} catch (NumberFormatException e){
			request.setAttribute("callback-gradeFormat-missing", true);
			hasError = true;
		}
		
		try{
			eventTypeId = Integer.parseInt(eventTypeString);
			//request.setAttribute("callback-eventType-missing", false);
		} catch(NumberFormatException e){
			request.setAttribute("callback-eventType-missing", true);
			hasError = true;
		}
		try{
			startDate = Date.valueOf(startDateString);
			//request.setAttribute("callback-startDate-missing", false);
		} catch (IllegalArgumentException e){
			request.setAttribute("callback-startDate-missing", true);
			hasError = true;
		}
		try{
			endDate = Date.valueOf(endDateString);
			//request.setAttribute("callback-endDate-missing", false);
		} catch (IllegalArgumentException e){
			request.setAttribute("callback-endDate-missing", true);
			hasError = true;
		}
	 	
		//checks to make sure startDate is before endDate
		if(startDate != null && endDate != null &&
				Period.between(startDate.toLocalDate(), endDate.toLocalDate()).isNegative()){
			request.setAttribute("callback-outoforderdates", true);
			hasError = true;
		} else {
			//request.setAttribute("callback-outoforderdates", false);
		}
		
		if(gradeFormatId == Service.OtherGradingFormat){
			if(customGradingFormat.isEmpty()){
				hasError = true;
				request.setAttribute("callback-customGradeFormat-empty", true);
			}
		} else {
			customGradingFormat = "";
		}
		
		//event most be greater then 1 week away.
		if(startDate != null){
			LocalDate dateNow = LocalDate.now();
			LocalDate dateOfEvent = startDate.toLocalDate();
			
			
			Period p = Period.between(dateNow, dateOfEvent);
			System.out.println(p.getDays());
			if(p.getDays() <= 6 && p.getMonths() <= 0 && p.getYears() <= 0){
				request.setAttribute("callback-startdate-tooclose", true);
				hasError = true;
			} else {
				//request.setAttribute("callback-startdate-tooclose", false);
			}
			
		}
		
		if(!hasError){
			int statusId = Service.getInstance().getStartingStatusId(user);
			double maxReimbursementAmount = Service.getInstance().getMaxReimbursementLeft(user);
			double preCappedReimbursementAmount = cost * Service.getInstance().getEventType().get(eventTypeId).getCoverage();
			double reimbursementAmount = preCappedReimbursementAmount <= maxReimbursementAmount ? preCappedReimbursementAmount : maxReimbursementAmount;
			Reimbursement reimbursement = new Reimbursement(null, 
					user.getUserId(),
					null, //prefilled by database
					desc,
					loc,
					cost,
					gradeFormatId,
					eventTypeId,
					startDate,
					endDate,
					Date.valueOf(LocalDate.now()),
					statusId,
					null, //to be filled in later
					null, //to be filled in later
					reimbursementAmount, 
					customGradingFormat,
					null //to be filled in later, if needed
					);
			
			//TODO: add callback for successful 
			Service.getInstance().InsertOrUpdateReimbursement(reimbursement);
			
			request.setAttribute("callback-new-reimbursement", true);
			
			RequestDispatcher rd = request.getRequestDispatcher("Reimbursement");
			rd.forward(request, response);
			//response.sendRedirect("Reimbursement");
			//RequestDispatcher rd = request.getRequestDispatcher("/ListReimbursements");
			//rd.forward(request, response);
		} else {
			request.setAttribute("has-error", true);
			this.doGet(request, response);
			/*RequestDispatcher rd = request.getRequestDispatcher("NewReimbursement");
			rd.forward(request, response);*/
		}
		
	}

}
