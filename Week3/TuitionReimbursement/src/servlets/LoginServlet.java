package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.service.EmployeeService;

/**
 * Servlet implementation class TestServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("uname");
		String password = request.getParameter("pword");
		
		EmployeeService serveEmp = new EmployeeService();
		
		HttpSession ses = request.getSession();
		int empId = serveEmp.loginEmployee(username, password);
		int balance = serveEmp.getBalance(empId);
		
		ses.setAttribute("balance", balance);
		request.setAttribute("id", empId);
		ses.setAttribute("uId", empId);
		
		if(empId != 0){
			int messages = serveEmp.getNumberOfMessages(empId);
			int roleId = serveEmp.getRoleId(empId);
			int deptId = serveEmp.getDepartment(empId);
			
			request.setAttribute("messages", messages);
			request.setAttribute("roleId", roleId);
			request.setAttribute("deptId", deptId);
			
			ses.setAttribute("roleId", roleId);
			ses.setAttribute("messages", messages);
			ses.setAttribute("deptId", deptId);
		}
		
		if(empId == 0){
			System.out.println(empId);
			String nextJSP = "/index.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
		}else{	
			String nextJSP = "/loggedIn.jsp";
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
