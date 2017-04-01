package servlet.reimbursement;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.model.Attachment;
import database.model.Feedback;
import database.model.Reimbursement;
import database.model.User;
import database.service.Service;

/**
 * Servlet implementation class EditReimbursement
 */
@WebServlet("/EditReimbursement")
public class EditReimbursement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditReimbursement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		User user = (User) request.getSession().getAttribute("loggedInUser");
		if(request.getParameter("id")!= null && user != null){
			//checks if reimbursement exists and if user has access
			try{
				Integer id = Integer.valueOf(request.getParameter("id"));
				Reimbursement reimbursement = Service.getInstance().getReimbursementById(id);
				List<Feedback> feedbacks = Service.getInstance().getFeedbackFromReimbursementForUser(reimbursement, user);
				
				
				List<User> usersToMessage = Service.getInstance().getUsersToMessage(user, reimbursement.getUserId());
				List<Attachment> attachments = Service.getInstance().getAttachmentByReimbursement(reimbursement);
				
				//System.out.println(usersToMessage);
				
				request.setAttribute("maxReimbursement", Service.getInstance().getMaxReimbursementLeft(reimbursement.getUser()));
				request.setAttribute("reimbursement", reimbursement);
				request.setAttribute("feedbacks", feedbacks);
				request.setAttribute("usersToMessage", usersToMessage);
				request.setAttribute("canEditReimbursement", false);
				request.setAttribute("attachments", attachments);
				
				//TODO: reduce repeated Service Calls
				
				//User can cancel a reimbursement at any time and send attachment
				if(reimbursement.getUser().equals(user) && reimbursement.getStatus().getStatusId() <= Service.AwaitComplete){
					request.setAttribute("canCancel", new Boolean(true));
					request.setAttribute("canSendAttachments", new Boolean(true));
					if (reimbursement.getStatusId().equals(Service.AwaitComplete) 
							&& !reimbursement.getGradeFormat().getRequiresPresentation()){
						request.setAttribute("canEditGrade", new Boolean(true));
					}
				}
				
				//Supervisor Approvals
				if(reimbursement.getStatus().getStatusId().equals(Service.AwaitSuperApp)
						&& reimbursement.getUser().getSupervisor() != null && reimbursement.getUser().getSupervisor().equals(user)){
					request.setAttribute("canApprove", new Boolean(true));
					request.setAttribute("canReject", new Boolean(true));
				}
				
				//Dept Head Approvals
				if(reimbursement.getStatus().getStatusId().equals(Service.AwaitDepHeadApp)
						&& Service.getInstance().getDeptHead(reimbursement.getUser().getDept()).equals(user)){
					request.setAttribute("canApprove", new Boolean(true));
					request.setAttribute("canReject", new Boolean(true));
				}
				
				//BenCo Approves
				if(user.getDeptId().equals(Service.BenCoDept)
						&& (reimbursement.getStatus().getStatusId().equals(Service.AwaitBenCoApp))){
					
					request.setAttribute("canApprove", new Boolean(true));
					request.setAttribute("canReject", new Boolean(true));
					request.setAttribute("canEditReimbursement", true);
				}
				
				//BenCo Approves Grade
				if(user.getDeptId().equals(Service.BenCoDept)
						 && (reimbursement.getStatusId().equals(Service.AwaitComplete) 
								 && !reimbursement.getGradeFormat().getRequiresPresentation())){
					request.setAttribute("canApprove", new Boolean(true));
					request.setAttribute("canReject", new Boolean(true));
				}
				
				//Manager Approves
				if(user.getRoleId().equals(Service.Manager)
						&& reimbursement.getStatusId().equals(Service.AwaitComplete) 
						&& reimbursement.getGradeFormat().getRequiresPresentation()
						){
					request.setAttribute("canApprove", new Boolean(true));
					request.setAttribute("canReject", new Boolean(true));					
				}
				
				RequestDispatcher rd = request.getRequestDispatcher("viewReimbursement.jsp");
				rd.forward(request, response);
				
				
				
			} catch (NumberFormatException e){
				Service.getInstance().log(Service.ERROR, "Id For reimbursement was NaN");
			}			
		}
		
		//requires parameter and valid id
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO: add support for adding feedback and attachments
		try{
			User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
			Integer reimbursementId = (Integer) Integer.parseInt(request.getParameter("reimburseId"));
			Reimbursement reimbursement = Service.getInstance().getReimbursementById(reimbursementId);
			
			Boolean sentCancel = "Cancel".equals(request.getParameter("statusChange"));
			Boolean sentReject = "Confirm Reject".equals(request.getParameter("statusChange"));
			Boolean sentApprove = "Approve".equals(request.getParameter("statusChange"));
			Boolean sentEdit = "Edit".equals(request.getParameter("statusChange"));
			Map<String, String[]> map = request.getParameterMap();
			
			//user who made reimbursement request can cancel
			if(reimbursement.getUser().equals(loggedInUser)){
			if (sentCancel){
				reimbursement.setStatusId(Service.Canceled);
				Service.getInstance().InsertOrUpdateReimbursement(reimbursement);
			}
			
			if(sentEdit 
					&& reimbursement.getUserId().equals(loggedInUser.getUserId())
					&& reimbursement.getStatusId().equals(Service.AwaitComplete) 
					&& !reimbursement.getGradeFormat().getRequiresPresentation()){
				String grade = request.getParameter("reimburse-grade");
				String passingGrade = request.getParameter("reimburse-passingGrade");
				reimbursement.setGrade(grade);
				reimbursement.setPassingGrade(passingGrade);
				Service.getInstance().InsertOrUpdateReimbursement(reimbursement);
			}
			
			
			}
		
			
			//Supervisor Approves or Rejects
			if(reimbursement.getUser().getSupervisor().equals(loggedInUser) && reimbursement.getStatusId() == Service.AwaitSuperApp){
				if(sentReject){
					processRejection(reimbursement, request, response);
				} else if (sentApprove){
					reimbursement.setStatusId(Service.AwaitDepHeadApp);
					Service.getInstance().InsertOrUpdateReimbursement(reimbursement);
				}
			}
			
			//Dept Head Approves or Rejects
			if(Service.getInstance().getDeptHead(reimbursement.getUser().getDept()).equals(loggedInUser)
					&& reimbursement.getStatusId() == Service.AwaitDepHeadApp){
				if(sentReject){
					processRejection(reimbursement, request, response);
				} else if (sentApprove){
					reimbursement.setStatusId(Service.AwaitBenCoApp);
					Service.getInstance().InsertOrUpdateReimbursement(reimbursement);
				}
			}
			
			
			//BenCo Approves when grade format does not require presentation
			if(reimbursement.getStatusId() == Service.AwaitComplete 
					&& loggedInUser.getDeptId().equals(Service.BenCoDept)
					&& !reimbursement.getGradeFormat().getRequiresPresentation()){
				if(sentReject){
					processRejection(reimbursement, request, response);
				} else if (sentApprove){
					reimbursement.setStatusId(Service.Approved);
					Service.getInstance().InsertOrUpdateReimbursement(reimbursement);
				}
			}	
			//TODO: Manager approves if it require presentation
			if(loggedInUser.getRoleId() == Service.Manager
					&& reimbursement.getGradeFormat().getRequiresPresentation()
					&& reimbursement.getStatusId().equals(Service.AwaitComplete)){
				
				if(sentReject){
					processRejection(reimbursement, request, response);
				} else if (sentApprove){
					reimbursement.setStatusId(Service.Approved);
					Service.getInstance().InsertOrUpdateReimbursement(reimbursement);
				}
				
				
			}
			
			
			//BenCo Approves
			if(reimbursement.getStatusId() == Service.AwaitBenCoApp && loggedInUser.getDeptId().equals(Service.BenCoDept)){
				if (sentReject){
					processRejection(reimbursement, request, response);
				} else if (sentApprove){
					String reimbursementAmount = request.getParameter("reimburse-reimburse");
					reimbursement.setReimbursement(Double.parseDouble(reimbursementAmount)); 
					reimbursement.setStatusId(Service.AwaitComplete);
					Service.getInstance().InsertOrUpdateReimbursement(reimbursement);
				}
			}
			
			
			response.sendRedirect("EditReimbursement?id="+reimbursementId);
			//doGet(request, response);
			
		
		}catch (NumberFormatException e){
			e.printStackTrace();
			doGet(request, response);
		}
	
		//response.sendError(500);
		
		
	}

	/**
	 * @param reimbursement
	 * @param request TODO
	 * @param response TODO
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void processRejection(Reimbursement reimbursement, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("loggedInUser");
		reimbursement.setStatusId(Service.Rejected);
		
		RequestDispatcher rd = request.getRequestDispatcher("NewFeedback");
		rd.include(request, response);
		
		List<Feedback> list = Service.getInstance().getFeedbackFromReimbursementForUser(reimbursement, user);
		
		//get newly created feedback, this is the rejection feedback, save id to reimbursement
		Feedback fb = list.get(list.size() - 1);
		reimbursement.setRejectionFeedbackId(fb.getFeedbackId());
		
		Service.getInstance().InsertOrUpdateReimbursement(reimbursement);
		
	}

}
