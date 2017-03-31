package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignOut
 */
public class SignOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignOut() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("LOLOLOLOLOLOL");
		Employee.SignedInEmployee.username = null;
		Employee.SignedInEmployee.address = null;
		Employee.SignedInEmployee.password = null;
		Employee.SignedInEmployee.firstname = null;
		Employee.SignedInEmployee.lastname = null;
		Employee.SignedInEmployee.empID = -1;
		Employee.SignedInEmployee.deptID = -1;
		Employee.SignedInEmployee.signedIn = false;
		Employee.SignedInEmployee.role = -1;
		Employee.SignedInEmployee.email = null;
		Employee.SignedInEmployee.reinallowance = -1;
		Employee.SignedInEmployee.reportstoid = -1;
		 response.sendRedirect("signin.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
