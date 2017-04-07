package Servlets;



/*
 
 
<% %> is a scriptlet
<%! %> is a decleration
<%= %> is a expression
<jsp:include> action
<%-- --%> is a comment
<%@  %> is a directive
  
 
 
 
 
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Employee.*;

import DAO.*;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String initParamName;
    private String initParamValue,contValue,contName;
    //static Employee  = new Employee(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try
		{
		System.out.println("INIT HELLO WORLD");
		Enumeration<String> iP = config.getInitParameterNames();
		
		initParamName = iP.nextElement();
		initParamValue = config .getInitParameter(initParamName);
		contName = config.getServletContext().getInitParameterNames().nextElement();
		contValue = config.getServletContext().getInitParameter(contName);
		
		
		}
		
		catch(Exception e)
		{
			
			System.out.println(e.getMessage());
		}
		
		
		try
		{
			
			DAO d = new DAO();
			d.pullAllEmpDown();
			d.pullAllReinDown();
		for(Employees e : Collecters.emp)
		{
			
			System.out.println(e.toString());
		}
		for(Reinbursment r: Collecters.rein)
		{
			
			System.out.println(r.toString());
		}
			
			System.out.println("FINISHED");
			
		}
		catch(Exception e){
			
			
		}
		}
	

	
	
	protected void validatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		boolean flagit = false;
		String user = request.getParameter("uname");
		String password = request.getParameter("pwd");
		
		
		
		
		
		
		
		String s = "";
		String pass = "";
		int roleid = -1;
		int deptid = -1;
		int empid = -1;
		String FirstName = "";
		String LastName = "";
		int reportstoid = -1;
		int reinallowance = -1;
		String email = "";
		String address = "";
		int am = -1;
		String sql = "select pass,username,role_id,dept_id,emp_id,first_name,last_name,reports_to_id,email,rein_allowance,address,approvedammount from employee ";
	
		try{
			
			
			 Connection cont = ConnectionUtil.getConnection();
				
				PreparedStatement ps = cont.prepareStatement(sql);
			//	ps.setString(1, user);
				//ps.setString(2, password);
			
				ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				
			   pass = rs.getString(1);
		        s = rs.getString(2);
				roleid = rs.getInt(3);
				deptid = rs.getInt(4);
				empid = rs.getInt(5);
				FirstName = rs.getString(6);
				LastName = rs.getString(7);
				reportstoid = rs.getInt(8);
				email = rs.getString(9);
				reinallowance = rs.getInt(10);
				address = rs.getString(11);
				am = rs.getInt(12);

				//		deptid = rs.getInt(6);
				
				
		System.out.println("Username = " + s + " Password = " +pass);
		if(s.equals(user)&& pass.equals(password))
		{
			flagit = true;
			Employee.SignedInEmployee.failedlogin = false;
					Employee.SignedInEmployee.username = s;
					Employee.SignedInEmployee.address = address;
					Employee.SignedInEmployee.password = pass;
					Employee.SignedInEmployee.firstname = FirstName;
					Employee.SignedInEmployee.lastname = LastName;
					Employee.SignedInEmployee.empID = empid;
					Employee.SignedInEmployee.deptID = deptid;
					Employee.SignedInEmployee.signedIn = true;
					Employee.SignedInEmployee.role = roleid;
					Employee.SignedInEmployee.email = email;
					Employee.SignedInEmployee.reinallowance = reinallowance;
					Employee.SignedInEmployee.reportstoid = reportstoid;
					Employee.SignedInEmployee.am = am;
					response.getWriter().append("BIRD UP!!!" + ""
			+ "Username passed: " +request.getParameter("uname") +"\n"
			+ "Password passed: " + request.getParameter("pwd")+"\n");
			break;
			
		}
			}

		
			
		}
		catch(Exception e)
		{
			System.out.println("FAILURE");
			
			System.out.println(e.getMessage());
		}
		
		
		
		DAO d = new DAO();
		
		 Employee.Collecters.emp = new ArrayList<Employee.Employees>();
		  Employee.Collecters.rein =new ArrayList<Employee.Reinbursment>();
		 d.pullAllReinDown();
		d.pullAllEmpDown();
		
		if(flagit==false)
		{
			SignedInEmployee.failedlogin= true;
			request.getRequestDispatcher("/signin.jsp").forward(request,response);
		}
		else
		{
			
			System.out.println("FLAG WAS TRUE");
			//RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Employee.jsp");
			//dispatcher.forward(request,response);
			 request.getRequestDispatcher("/Employee.jsp").forward(request,response);
		}
		
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		////////////////////////////////////////////////////////////////////////////////
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try
		{
		if(Employee.SignedInEmployee.signedIn==true)
		{
			
			request.getRequestDispatcher("/Employee.jsp").forward(request,response);
		}
		validatePassword(request,response);
		}
		catch(Exception e)
		{
			
		System.out.println(e.getMessage());	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
