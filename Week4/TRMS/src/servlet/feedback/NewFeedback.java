package servlet.feedback;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.model.Feedback;
import database.model.Reimbursement;
import database.model.User;
import database.service.Service;

/**
 * Servlet implementation class NewFeedBack
 */
@WebServlet("/NewFeedback")
public class NewFeedback extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewFeedback() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// No OP
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//gets and loads the message and its context from the request
		Integer reimbursementId = Integer.parseInt(request.getParameter("reimburse-id"));
		Integer userToSendId = Integer.parseInt(request.getParameter("feedback-user"));
		User user = (User) request.getSession().getAttribute("loggedInUser");
		User userToSend = Service.getInstance().getUserFromId(userToSendId);
		Reimbursement reimbursement = Service.getInstance().getReimbursementById(reimbursementId);
		String message = request.getParameter("feedback-feedback");
		if(reimbursement != null){
			Feedback feedback = new Feedback(
					null, //added by database
					user.getUserId(),
					userToSend.getUserId(),
					reimbursement.getReimbursementId(),
					null, //added by database
					message
					);
			
			Service.getInstance().addFeedBackToDatabase(feedback);
			
			
			//RequestDispatcher rd = request.getRequestDispatcher("EditReimbursement?id=" + reimbursement.getReimbursementId());
			//rd.forward(request, response);
			response.sendRedirect("/TRMS/EditReimbursement?id=" + reimbursementId);
		}
		//doGet(request, response);
	}

}
