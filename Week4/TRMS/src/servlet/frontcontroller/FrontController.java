package servlet.frontcontroller;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.model.User;

/**
 * Servlet Filter implementation class FrontController
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
					, urlPatterns = { "/*" })
public class FrontController implements Filter {

    /**
     * Default constructor. 
     */
    public FrontController() {
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

		// pass the request along the filter chain
		// TODO: add way to filter out invalid commands
		if(request instanceof HttpServletRequest && response instanceof HttpServletResponse){
			HttpServletRequest hsr = (HttpServletRequest) request;
			String[] s =  hsr.getRequestURL().toString().split("/");
			if (hsr.getSession().getAttribute("loggedInUser") == null){
				if(s.length >= 5){
					//forces
					if(!s[4].equals("Login") && !s[4].equals("Logout")){
						((HttpServletResponse) response).sendRedirect("Login");
						return;
					}
				}
			}
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
