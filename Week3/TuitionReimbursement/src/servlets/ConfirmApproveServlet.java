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
		
		
		System.out.println(reimbId);
		System.out.println(empId);
		System.out.println(roleId);
		System.out.println(deptId);
		
		String act = request.getParameter("edit");
		boolean result = false;
		if (act == null) {
			System.out.println(false);
		} else if (act.equals("approve")) {
		    result = serveEmp.updateReimbursement(reimbId, empId, roleId, deptId, true);
		} else if (act.equals("decline")) {
			result = serveEmp.updateReimbursement(reimbId, empId, roleId, deptId, false);
		} else if (act.equals("request")) {
		    //someone has altered the HTML and sent a different value!
		}
		
		ArrayList<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		if(roleId == 3){//department head
			reimbursements = serveEmp.getReimbursements(2, deptId);
		}
		else if(roleId == 2){//direct supervisor
			reimbursements = serveEmp.getReimbursements(1, deptId);
		}
		else if(deptId == 1){//benco
			reimbursements = serveEmp.getReimbursementsByApprovalStep(3);
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
