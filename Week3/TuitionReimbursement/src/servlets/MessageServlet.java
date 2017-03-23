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

import com.revature.pojo.Message;
import com.revature.service.EmployeeService;

/**
 * Servlet implementation class MessageServlet
 */
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static EmployeeService serveEmp = new EmployeeService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession ses = request.getSession();
		int emp_id = (int) ses.getAttribute("uId");
		System.out.println(emp_id);
		
		ArrayList<Message> messages = serveEmp.getMessages(emp_id);
		
		request.setAttribute("messageList", messages);
		
		String nextJSP = "/messages.jsp";
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
