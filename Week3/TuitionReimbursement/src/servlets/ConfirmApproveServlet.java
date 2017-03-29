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
 * Servlet implementation class ConfirmApproveServlet
 */
public class ConfirmApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmApproveServlet() {
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
		int empId = (int) ses.getAttribute("uId");
		int roleId = (int) ses.getAttribute("roleId");
		int deptId = (int) ses.getAttribute("deptId");
		
		
		
		String act = request.getParameter("edit");
		boolean result = false;
		String empName = "";
		int empMessage = 0;
		if (act == null) {
			System.out.println(false);
		} else if (act.equals("approve")) {
		    result = serveEmp.updateReimbursement(reimbId, empId, roleId, deptId, true, "");
		} else if (act.equals("decline")) {
			String reason = request.getParameter("reason");
			System.out.println(reason);
			result = serveEmp.updateReimbursement(reimbId, empId, roleId, deptId, false, reason);
		} else if (act.equals("request")) {
		   empMessage = serveEmp.getEmployeeIdByReimbursementId(reimbId);
		   empName = serveEmp.getEmployeeName(empMessage);
		}
		
		ArrayList<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		if(roleId == 3){//department head
			reimbursements = serveEmp.getReimbursements(2, deptId, empId);
		}
		else if(roleId == 2){//direct supervisor
			reimbursements = serveEmp.getReimbursements(1, deptId, empId);
		}
		else if(deptId == 1){//benco
			reimbursements = serveEmp.getReimbursementsByApprovalStep(3, empId);
		}
		
		ses.setAttribute("reimbursements", reimbursements);
		request.setAttribute("reimbursements", reimbursements);
		
		if(act.equals("request")){
			request.setAttribute("empName", empName);
			request.setAttribute("empMessage", empMessage);
			
			String nextJSP = "/sendMessage.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
		}
		else{
			String nextJSP = "/pendingReimbursements.jsp";
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
