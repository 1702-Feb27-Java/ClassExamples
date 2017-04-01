package com.tres.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tres.objs.Employee;
import com.tres.service.ServiceImp;

/**
 * Servlet implementation class MyTest
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static ServiceImp serve = new ServiceImp();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpServletRequest req= (HttpServletRequest) request;
		System.out.println("LOGINUR LOGIN: " +  req.getRequestURI());
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("password");
		Employee emp = serve.getUser(uname, pwd);
		RequestDispatcher rs; 
		if (emp == null)
		{
			response.sendRedirect("index.jsp");
		}
		else
		{
			HttpSession sess = request.getSession(true);
			sess.setAttribute("uname", emp.getUsername());
			sess.setAttribute("uid", emp.getId());
			sess.setAttribute("balance", emp.getBalance());
			sess.setAttribute("dept", emp.getDept());
			sess.setAttribute("deptid", emp.getDeptid());
			sess.setAttribute("email", emp.getEmail());
			if (emp.getReportsTo() != null)
			{
				sess.setAttribute("repto", emp.getReportsTo());
				sess.setAttribute("repid", emp.getRepid());
			}
			else				
			{
				sess.setAttribute("repto", "N/A");
				sess.setAttribute("repid", 0);
			}
			sess.setAttribute("fname", emp.getFname());
			sess.setAttribute("pwd", emp.getPwd());
			sess.setAttribute("role", emp.getRole());
			sess.setAttribute("roleid", emp.getRoleid());
			//if ( emp.getDeptid() != 1 && emp.getRoleid() == 1)
				rs = request.getRequestDispatcher("/employee.jsp");
			//else
			//	rs = request.getRequestDispatcher("/higherups.jsp");
			rs.forward(request, response);
			
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
