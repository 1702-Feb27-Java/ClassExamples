package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class PendingReimbursementsServlet
 */
public class PendingReimbursementsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PendingReimbursementsServlet() {
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
		int roleId = (int) ses.getAttribute("roleId");
		int deptId = (int) ses.getAttribute("deptId");
		
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
		
		String nextJSP = "/pendingReimbursements.jsp";
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
