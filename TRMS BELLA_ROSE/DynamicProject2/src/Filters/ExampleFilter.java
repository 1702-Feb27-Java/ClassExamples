package Filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Classes.Employee;
import Servlets.Service;

/**
 * Servlet Filter implementation class ExampleFilter
 */
public class ExampleFilter implements Filter
{

	Service service = new Service();
	
	/**
	 * Default constructor.
	 */
	public ExampleFilter()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy()
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("Filter Triggered");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		if (service.validLogin(request.getParameter("uname"), request.getParameter("pwd")))
		{
			HttpSession session = req.getSession();
			session.setAttribute("CurrEmp", service.getCurrEmp(request.getParameter("uname")));
			session.setAttribute("validLogin", true);
			if (((Employee) req.getSession().getAttribute("CurrEmp")).getDeptId() == 1 || ((Employee) req.getSession().getAttribute("CurrEmp")).getRoleId() > 1)
			{
				System.out.println("RSForm");

				res.sendRedirect("RSForm.jsp");
			}
			else
			{
				System.out.println("RForm");

				res.sendRedirect("RForm.jsp");
			}
			
		} else
		{
			HttpSession session = req.getSession();
			session.setAttribute("validLogin", false);
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
System.out.println("chain");
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException
	{
		// TODO Auto-generated method stub
	}

}
