package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
		if(roleId == 3){
			//dept head
			reimbursements = serveEmp.getReimbursements(2, deptId);
		}
		else if(roleId == 2){
			//supervisor
			reimbursements = serveEmp.getReimbursements(1, deptId);
		}
		else if(deptId == 1){
			//benco
			reimbursements = serveEmp.getReimbursementsByApprovalStep(3);
		}
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1> empl id : " + empId + "</h1>");
		out.println("<h1> role id : " + roleId + "</h1>");
		out.println("<h1> dept id : " + deptId + "</h1>");
		out.println("<h1> dept id : " + reimbursements + "</h1>");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
