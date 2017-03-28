package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.service.EmployeeService;

/**
 * Servlet implementation class SendMessageServlet
 */
public class SendMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses = request.getSession();
		EmployeeService empServe = new EmployeeService();
		
		String message = request.getParameter("message");
		System.out.println(message);
		
		int empMessage = Integer.parseInt(request.getParameter("empMessage"));
		System.out.println(empMessage);
		
		int empId = (int)ses.getAttribute("uId");
		System.out.println(empId);
		
		int reimbId = (int)ses.getAttribute("reimbId");
		System.out.println(reimbId);
		
		empServe.addMessage(message, empMessage, empId, reimbId);
		
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
