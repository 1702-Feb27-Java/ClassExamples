package hobbs.project01.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hobbs.project01.pojo.Employee;
import hobbs.project01.service.EmployeeServiceImpl;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee employee = EmployeeServiceImpl.getEmployeeService().login(request.getParameter("username"), request.getParameter("password"));
		if (employee != null) {
			request.getSession().setAttribute("user", employee);
			response.sendRedirect("account");
		}
		else {
			response.sendRedirect("login");
		}
	}

}
