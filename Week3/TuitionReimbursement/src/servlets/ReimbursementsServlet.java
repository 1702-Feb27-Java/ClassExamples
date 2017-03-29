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
 * Servlet implementation class ReimbursementsServlet
 */
public class ReimbursementsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		EmployeeService serveEmp = new EmployeeService();
		
		ArrayList<String> locations = serveEmp.getAllLocations();
		ArrayList<String> gradingTypes = serveEmp.getAllGradingTypes();
		ArrayList<String> eventTypes = serveEmp.getAllTypeOfEvents();
		System.out.println(locations);
		System.out.println(gradingTypes);
		System.out.println(eventTypes);
		
		request.setAttribute("locationsList", locations);
		request.setAttribute("gradingTypes", gradingTypes);
		request.setAttribute("eventTypes", eventTypes);
		
		HttpSession ses = request.getSession();
		int empId = (int)ses.getAttribute("uId");
		ses.setAttribute("locationsList", locations);
		ses.setAttribute("gradingTypes", gradingTypes);
		ses.setAttribute("eventTypes", eventTypes);
		
		ArrayList<Reimbursement> reimbursements = serveEmp.getPendingReimbursements(empId);
		
		ses.setAttribute("reimbursements", reimbursements);
		request.setAttribute("reimbursements", reimbursements);
		
		String nextJSP = "/reimbursements.jsp";
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
