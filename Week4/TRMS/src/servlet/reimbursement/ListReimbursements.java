package servlet.reimbursement;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.model.Reimbursement;
import database.model.User;
import database.service.Service;
//impor database.model.Reimbursement;

/**
 * Servlet implementation class showTransactions
 */
public class ListReimbursements extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListReimbursements() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String path = request.getServletPath();
		User user = (User) request.getSession().getAttribute("loggedInUser");

		List<Reimbursement> list = Service.getInstance().getReimbursementsFromUser(user);
		request.setAttribute("list", list);
		
		List<Reimbursement> listOfReimbursementsToApprove = getListOfReimbursementsToApprove(user);
		request.setAttribute("toApprove", listOfReimbursementsToApprove);
		
		RequestDispatcher rd = request.getRequestDispatcher("listReimbursements.jsp");
		rd.forward(request, response);
	
	}



	private List<Reimbursement> getListOfReimbursementsToApprove(User user) {
		// TODO Auto-generated method stub
		List<Reimbursement> reimbursements = new LinkedList<Reimbursement>();
		/*
		if (user.getDeptId() != Service.BenCoDept){
			List<User> usersSupervised = Service.getInstance().getUsersBySupervisor(user);
			Set<User> users = new HashSet<User>();
			users.addAll(usersSupervised);
			
			if(user.getRoleId() == Service.DeptHead || user.getRoleId() == Service.Manager){
				users.addAll(Service.getInstance().getUsersByDept(user.getDept()));
			}
			
			for (User u: users){
				List<Reimbursement> reimbursementsFromUser = Service.getInstance().getReimbursementsFromUser(u);
				if()
				
				reimbursements.addAll(
						Service.getInstance().getReimbursementsFromUser(u));
				}
			
		}*/
		//user is BenCo, can see all pending reimbursements
		if (user.getDeptId() == Service.BenCoDept){
			reimbursements = Service.getInstance().getAllPendingReimbursements();
		}
		
		//User is Manager, can see all pending completeion reimbursements requireing presentation 
		if(user.getRoleId() == Service.Manager){
			List<User> users= Service.getInstance().getUsersByDept(user.getDept());
			for(User u : users){
				List<Reimbursement> reimbursementsToCheck = Service.getInstance().getReimbursementsFromUser(u);
				for(Reimbursement r : reimbursementsToCheck) {
					if (r.getStatus().getStatusId() == Service.AwaitComplete && r.getGradeFormat().getRequiresPresentation()){
						reimbursements.add(r);
					}
				}
			}
		}
		
		//User is Dept Head, can see reimbursement requests requiring their approval
		else if(user.getRoleId() == Service.DeptHead){
			List<User> users= Service.getInstance().getUsersByDept(user.getDept());
			for(User u : users){
				List<Reimbursement> reimbursementsToCheck = Service.getInstance().getReimbursementsFromUser(u);
				for(Reimbursement r : reimbursementsToCheck) {
					if (r.getStatus().getStatusId() >= Service.AwaitDepHeadApp && r.getStatus().getStatusId() <= Service.Approved){
						reimbursements.add(r);
					}
				}
			}
		}
		
		else if(user.getRoleId() == Service.Supervisor ){
			List<User> users= Service.getInstance().getUsersBySupervisor(user);
			for(User u : users){
				reimbursements = Service.getInstance().getReimbursementsFromUser(u);
			}
		}
		
		
		//Sort Reimbursements by Start Date
		Collections.sort(reimbursements, new Comparator<Reimbursement>() {

			@Override
			public int compare(Reimbursement o1, Reimbursement o2) {
				// TODO Auto-generated method stub
				return o1.getStartDate().compareTo(o2.getStartDate());
			}
		});
		
		return reimbursements;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
