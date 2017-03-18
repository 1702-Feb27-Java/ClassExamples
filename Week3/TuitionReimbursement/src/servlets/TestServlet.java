package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.service.EmployeeService;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
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
		// TODO Auto-generated method stub
		//System.out.println("testu");
		PrintWriter out = response.getWriter();
		
		//Use printwriter object to dynamically write a webpage
		String username = request.getParameter("uname");
		String password = request.getParameter("pword");
		//out.println("username " + username + " password " + password);
		//System.out.println("username " + username + " password " + password);
		
		


		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();
		properties.load(classLoader.getResourceAsStream("DBProp.properties"));
		
		EmployeeService serveEmp = new EmployeeService(properties);
		
		int empId = serveEmp.loginEmployee(username, password);
		System.out.println("test");
		System.out.println("emp Id " + empId);
		request.setAttribute("id", empId);
		

		out.println("<html>\n" + "<body>\n" + "Username: " + username + "\n<br>"
				+ "Password: " + password + "\n<br>"
						+ "ID: " + empId);
		
		out.println(
				"</table>"
				+ "</body>\n"
				+ "</html>"
				);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
