package hobbs.project01.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hobbs.project01.pojo.Employee;
import hobbs.project01.service.EmployeeServiceImpl;
import hobbs.project01.util.ConnectionUtil;

/**
 * @author Michael Hobbs
 * 
 * Servlet implementation class UpdateEmployee
 */
public class UpdateEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmployee() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee user = (Employee)request.getSession().getAttribute("user");
		
		String newFirstName = request.getParameter("newFirstName");
		String newLastName = request.getParameter("newLastName");
		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		
		// update the first name only if the user input one at all.
		if (!newFirstName.isEmpty()) {
			user.setFirstName(newFirstName);
		}
		// update the last name only if the user input one at all.
		if (!newLastName.isEmpty()) {
			user.setLastName(newLastName);
		}
		// update the password only if the user input the current one correctly.
		if (!currentPassword.isEmpty() && currentPassword.equals(user.getPassword())) {
			user.setPassword(newPassword);
		}
		
		EmployeeServiceImpl.getEmployeeService().updateAccount(user);
		
		// refresh system for user
		Employee refreshedUser = EmployeeServiceImpl.getEmployeeService().refreshUser(user);
		request.getSession().setAttribute("user", refreshedUser);
		
		response.sendRedirect("account");
	}

}
