package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		int messageId = Integer.parseInt(request.getParameter("messageId"));
		
		serveEmp.markMessageRead(messageId);
		int messages = serveEmp.getNumberOfMessages(empId);
		ses.setAttribute("messages", messages);
		
		Reimbursement reimbursement = serveEmp.getReimbursementById(reimbId);
		
		request.setAttribute("reimbursement", reimbursement);
		ses.setAttribute("reimbId", reimbId);
		request.setAttribute("messagerId", request.getParameter("messagerId"));
		
		String message = request.getParameter("message");
		
		if(message.equals("Reimbursement Updated")){
			String nextJSP = "/approveReimbursement.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
		}
		else if(message.equals("Reimbursement Processed")){
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
