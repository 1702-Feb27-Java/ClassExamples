package com.tres.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class Filter
 */
public class Filter implements javax.servlet.Filter {

    /**
     * Default constructor. 
     */
    public Filter() {
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
		System.out.println("Filter Triggered");
		
		HttpServletRequest req= (HttpServletRequest) request;
		HttpServletResponse res= (HttpServletResponse) response;
		
		String [] sa = req.getRequestURI().split("/");
		String action = sa[sa.length-1];
		System.out.println("Action: "+action);
		action = action.substring(0, action.indexOf("."));
		
		RequestDispatcher rd;
		switch (action)
		{
			case "Login":
				rd=req.getRequestDispatcher(action);
		System.out.println("LOGINUR: " +  req.getRequestURI());
				rd.forward(req, res);
				return;
			case "Reimbur":
				rd=req.getRequestDispatcher(action+"/trest");
				rd.forward(req, res);
				return;
			case "Pending":
				rd=req.getRequestDispatcher(action+"/hahah");
		System.out.println("LOGINUR: " +  req.getRequestURI());
				rd.forward(req, res);
				return;
			default:
				
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
