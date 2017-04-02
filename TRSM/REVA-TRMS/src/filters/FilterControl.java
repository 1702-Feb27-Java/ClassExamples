package filters;

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
 * Servlet Filter implementation class FilterControl
 */
public class FilterControl implements Filter {

    /**
     * Default constructor. 
     */
    public FilterControl() {
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

		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res =(HttpServletResponse)response;
		
		String[] sa = req.getRequestURI().split("/");
		String action = sa[sa.length - 1];
		action = action.substring(0, action.length() - 3);
		
		RequestDispatcher rd;
		switch(action){
		case "LoginServ":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		case "FormServ":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		case "ViewRequest":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		case "ApproveServ":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		case "MessageServ":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		case "Advance":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		case "EmployeeReimDelete":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
