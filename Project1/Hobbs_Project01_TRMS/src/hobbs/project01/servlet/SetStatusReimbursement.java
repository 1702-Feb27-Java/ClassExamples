package hobbs.project01.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hobbs.project01.pojo.Employee;
import hobbs.project01.pojo.Reimbursement;
import hobbs.project01.service.ReimbursementServiceImpl;

/**
 * Servlet implementation class SetStatusReimbursement
 */
public class SetStatusReimbursement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetStatusReimbursement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee adder = (Employee)request.getSession().getAttribute("user");
		Reimbursement reimbursement = (Reimbursement)request.getSession().getAttribute("reimbursement");
		String reason = request.getParameter("reason");

		if (!request.getParameter("approve").isEmpty()) {
			ReimbursementServiceImpl.getReimbursementService().approveReimbursement(adder, reimbursement, reason);
		}
		else if (!request.getParameter("deny").isEmpty()) {
			ReimbursementServiceImpl.getReimbursementService().denyReimbursement(adder, reimbursement, reason);
		}
		else { //unhandled request to this servlet
			response.sendRedirect("404");
		}
		
		request.getSession().removeAttribute("reimbursement");
		response.sendRedirect("view_apps");
		return;
	}

}
