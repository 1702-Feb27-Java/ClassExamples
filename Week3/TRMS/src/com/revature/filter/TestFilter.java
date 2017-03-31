package com.revature.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class TestFilter
 */
public class TestFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TestFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		String[] sa = req.getRequestURI().split("/");
		String action = sa[sa.length - 1];
		action = action.substring(0, action.length() - 3);
		RequestDispatcher rd;
		System.out.println(action);
		switch(action)
		{
		case "MyReimServlet":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			//^^^^^^^^^^^^^^^^^^FIX ME FIX ME FIX ME FIX ME
			return;
		case "LoginServlet":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		case "SessionHandler":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		case "MakeReimServlet":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		default:
//			rd = req.getRequestDispatcher("DefaultServlet");
//			rd.include(req, res);
			System.out.println("DEFAULT CASE: CRASHING");
			System.exit(0);
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
