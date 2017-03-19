package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.service.EmployeeService;

/**
 * Servlet implementation class CreateReimbursement
 */
public class CreateReimbursement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateReimbursement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("uname");
		
		EmployeeService serveEmp = new EmployeeService();
		
		ArrayList<String> locations = serveEmp.getAllLocations();
		ArrayList<String> gradingTypes = serveEmp.getAllGradingTypes();
		ArrayList<String> eventTypes = serveEmp.getAllTypeOfEvents();
		
		request.setAttribute("locationsList", locations);
		request.setAttribute("gradingTypes", gradingTypes);
		request.setAttribute("eventTypes", eventTypes);
		
		String nextJSP = "/createReimbursement.jsp";
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
