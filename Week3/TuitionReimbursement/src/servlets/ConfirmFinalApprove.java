package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.pojo.Reimbursement;
import com.revature.service.EmployeeService;

/**
 * Servlet implementation class ConfirmFinalApprove
 */
public class ConfirmFinalApprove extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmFinalApprove() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeService serveEmp = new EmployeeService();
		HttpSession ses = request.getSession();
		
		int reimbId = (int)ses.getAttribute("reimbId");
		int empId = (int)ses.getAttribute("uId");
		
		String act = request.getParameter("edit");
		
		int empMessage = serveEmp.getEmployeeIdByReimbursementId(reimbId);
		String empName = serveEmp.getEmployeeName(empMessage);
		//String reimbursementAmt = request.getParameter("reimbursementAmt");
		
		System.out.println(ses.getAttribute("reimbursementAmt"));
		double reimbursementAmt = (double)ses.getAttribute("reimbursementAmt");
		System.out.println(reimbursementAmt);
		//double reimbAmt = Double.parseDouble(reimbursementAmt);
		
		
		if (act == null) {	
			System.out.println(false);
		} else if (act.equals("approve")) {
		    serveEmp.finalUpdate(reimbId, true);
		    serveEmp.markFinalApprover(reimbId, empId);
			serveEmp.sendMoney(empMessage, reimbursementAmt);
		    serveEmp.addMessage("Reimbursement Processed", empMessage, empId, reimbId);		    
		} else if (act.equals("decline")) {
			String reason = request.getParameter("reason");
			serveEmp.finalUpdate(reimbId, false);
			serveEmp.addMessage("Reimbursement Declined: " + reason, empMessage, empId, reimbId);					
		}
		
		ArrayList<Reimbursement> reimbursements = serveEmp.getReimbursements(5, 0, empId);

		ses.setAttribute("reimbursements", reimbursements);
		request.setAttribute("reimbursements", reimbursements);

		String nextJSP = "/finalApprovals.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
