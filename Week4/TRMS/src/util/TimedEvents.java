package util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.model.Reimbursement;
import database.service.Service;

/**
 * Servlet implementation class TimedEvents
 */
@WebServlet(loadOnStartup=1,
description = "a hack to included support for scheduled task, will replace if found better option", 
urlPatterns = { "/TimedEvents" })
public class TimedEvents extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Timer timer = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimedEvents() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
		//run 
		UpdateReimbursementsTask task = new UpdateReimbursementsTask();
		timer = new Timer("updateReimbursements");
		timer.schedule(task, 0, 1000*60*60);
		
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		timer.cancel();
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
		doGet(request, response);
	}

}

class UpdateReimbursementsTask extends TimerTask{

	@Override
	public void run() {
		List<Reimbursement> reimbursements = Service.getInstance().getAllPendingReimbursements();
		LocalDate dateNow = LocalDate.now();
		for(Reimbursement reimbursement:reimbursements){
			LocalDate dateOfStatusChange = reimbursement.getDateOfLastStatusChange().toLocalDate();
			Period p = Period.between(dateOfStatusChange, dateNow);
			
			if (p.getDays() >= 14 || p.getMonths() > 0 || p.getYears() > 0 ){
				if(reimbursement.getStatusId().equals(Service.AwaitSuperApp)){
					reimbursement.setStatusId(Service.AwaitDepHeadApp);
					reimbursement.setDateOfLastStatusChange(Date.valueOf(LocalDate.now()));
					Service.getInstance().InsertOrUpdateReimbursement(reimbursement);
				} else if (reimbursement.getStatusId().equals(Service.AwaitDepHeadApp)){
					reimbursement.setStatusId(Service.AwaitBenCoApp);
					reimbursement.setDateOfLastStatusChange(Date.valueOf(LocalDate.now()));
					Service.getInstance().InsertOrUpdateReimbursement(reimbursement);
				}
			}
			
		}
	
	}
	
}
