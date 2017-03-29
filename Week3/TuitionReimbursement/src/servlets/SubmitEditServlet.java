package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.service.EmployeeService;

/**
 * Servlet implementation class SubmitEditServlet
 */
public class SubmitEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeService serveEmp = new EmployeeService();
		HttpSession ses = request.getSession();
		
		int empId = (int)ses.getAttribute("uId");
		int messagerId = Integer.parseInt(request.getParameter("messagerId"));
		
		String fileInput = request.getParameter("fileInput");
		ArrayList<String> attachmentLinks = new ArrayList<String>();
		attachmentLinks.add(fileInput);
		int reimbId = Integer.parseInt(request.getParameter("reimbId"));
		
		serveEmp.submitEdit(reimbId, attachmentLinks);
		serveEmp.addMessage("Reimbursement Updated", messagerId, empId, reimbId);
		  
		
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
