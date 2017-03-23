package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import objects.Employee;
import service.Service;
import dao.DAOObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServ
 */
public class LoginServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServ() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Service serv = new Service();
		
		
		if(!serv.doesUserNameAndPassMatch(request.getParameter("username"), request.getParameter("pwd"))){
			
			
			
			HttpServletRequest req = (HttpServletRequest)request;
			HttpServletResponse res =(HttpServletResponse)response;
			RequestDispatcher rd;
			rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, res);			
			return;
		}
		Employee e = serv.getEmployee( request.getParameter("username"));
		//request.setAttribute("employee", e);
		HttpSession sess = request.getSession(true);
		sess.setAttribute("employee", e);
		//sess.setAttribute("userName", e.getUserName());
	//	System.out.println(e.getUserName());
			
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res =(HttpServletResponse)response;
		RequestDispatcher rd;
		rd = req.getRequestDispatcher("Menu.jsp");
		rd.forward(req, res);			
		return;
		
//		out.println("<html>\n"
//				+ "<body>\n"
//				+ "Username passed: " + request.getParameter("username") + "\n"
//				+ "Password passed: " + request.getParameter("pwd")
//				+ "\n</body></html>");
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
