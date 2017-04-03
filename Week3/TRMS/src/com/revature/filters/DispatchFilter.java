package com.revature.filters;

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
 * Servlet Filter implementation class DispatchFilter
 */
public class DispatchFilter implements Filter {

    /**
     * Default constructor. 
     */
    public DispatchFilter() {
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
		System.out.println("Filter triggered");
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		String[] sa = req.getRequestURI().split("/");
		
		String action = sa[sa.length-1];
		
		action = action.substring(0, action.length()-3); // leaves us with word session handler
		RequestDispatcher rd;
		
		
		switch(action){
		case "LoginServlet":
			// gets action we want to handle
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			// now sessionhandler receives req, res through request dispatcher
			return;
		case "ExampleServlet":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		case "ReimbursementServlet":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		case "ApplyReimbursementServlet":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		case "ApproveServlet":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		default:
			rd = req.getRequestDispatcher("DefaultServlet");
			rd.include(req, res);
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
