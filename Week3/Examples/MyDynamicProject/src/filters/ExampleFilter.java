package filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class ExampleFilter
 */
public class ExampleFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ExampleFilter() {
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
		System.out.println("Filter Triggered"); //Just a sanity check to show that the filter triggers
		
		//First we cast out parameters as Http versions of request and response.
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		//Next we parse out the url from the request. 
		//getRequestURI() gets us "MyDynamicProject/something.do"
		//We then split that by "/" to get an array of everything in between.
		String[] sa = req.getRequestURI().split("/");
		
		//At this point we only care about the last element which would be "something.do"
		String action = sa[sa.length-1];
		
		//Next we chop off the ".do" portion
		action = action.substring(0, action.length()-3);
		
		//We then create a dispatcher to forward the request and response objects to the appropriate
		//servlet by name, which we cut out of the requestURI.
		RequestDispatcher rd;
		
		switch(action){
		case "SessionHandler":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);			
			return;
		case "ExampleServlet":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);			
			return;
		case "ExampleServlet2":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);			
			return;
		default:
			/*rd = req.getRequestDispatcher("DefaultServlet");
			rd.include(req, res);
			return;*/
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
