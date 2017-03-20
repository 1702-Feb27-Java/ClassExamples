package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import objects.Employee;
import dao.DAOObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		DAOObject ob = new DAOObject();
		//System.out.println("here");
		ArrayList<Employee> em = ob.getAllEmployees();
		//System.out.println(em.size());
		System.out.println(em.get(0).getUserName());
		//check if username/password is correct if not go back
		if(!em.get(0).getUserName().equals("BenCo")){
			HttpServletRequest req = (HttpServletRequest)request;
			HttpServletResponse res =(HttpServletResponse)response;
			RequestDispatcher rd;
			rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, res);			
			return;
		}
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res =(HttpServletResponse)response;
		RequestDispatcher rd;
		rd = req.getRequestDispatcher("Users.jsp");
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
