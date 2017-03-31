package servlets;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.revature.pojo.Attachment;
import com.revature.pojo.Reimbursement;
import com.revature.service.EmployeeService;

/**
 * Servlet implementation class EditReimbursementServlet
 */
public class EditReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditReimbursementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeService serveEmp = new EmployeeService();
		HttpSession ses = request.getSession();
		int empId = (int) ses.getAttribute("uId");
		int reimbId = Integer.parseInt(request.getParameter("reimbId"));
		if(request.getParameter("messageId") != null){
			int messageId = Integer.parseInt(request.getParameter("messageId"));
			serveEmp.markMessageRead(messageId);
		}
		
		
		int messages = serveEmp.getNumberOfMessages(empId);
		ses.setAttribute("messages", messages);
		
		Reimbursement reimbursement = serveEmp.getReimbursementById(reimbId);
		
		ArrayList<String> atts = serveEmp.getAttachmentsByReimbursementId(reimbId);
		ArrayList<Attachment> attachments = new ArrayList<Attachment>();
		
		@SuppressWarnings("deprecation")
		AmazonS3 s3Client = new AmazonS3Client(new ProfileCredentialsProvider());
		
		
		for(String s : atts){
			Attachment attachment = new Attachment();
			
			GeneratePresignedUrlRequest re = new GeneratePresignedUrlRequest("tuitionreimbursement", s);
			URL link = s3Client.generatePresignedUrl(re);
			
			attachment.setLink(link);
			attachment.setName(s);
			
			attachments.add(attachment);
			attachment = null;
		}
		
		
		request.setAttribute("attachments", attachments);
		request.setAttribute("reimbursement", reimbursement);
		ses.setAttribute("reimbId", reimbId);
		request.setAttribute("messagerId", request.getParameter("messagerId"));
		
		String message = request.getParameter("message");
		
		if(message != null && message.equals("Reimbursement Updated")){
			String nextJSP = "/approveReimbursement.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
		}
		else if(message != null && message.equals("Reimbursement Processed")){
			String nextJSP = "/finalReimbursement.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
		}
		else if(reimbursement.getApprovalStepId() == 6){
			String nextJSP = "/declinedReimbursement.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
		}
		else{
			String nextJSP = "/editReimbursement.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
