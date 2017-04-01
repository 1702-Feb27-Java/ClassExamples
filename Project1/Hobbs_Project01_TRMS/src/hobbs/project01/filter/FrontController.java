package hobbs.project01.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handles all navigation requests for the application by routing requests to the appropriate resource.
 *
 * Servlet Filter implementation class FrontController
 * 
 * @author Michael Hobbs
 */
public class FrontController implements Filter {

    /**
     * Default constructor. 
     */
    public FrontController() {
    	
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * Handles navigation requests for the entire application.
	 * 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("filtered @ " + new Date());
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		
		/*
		//System.out.println(r2.getContextPath());	//root?? (Day14DemoDynamicProject)
		//System.out.println(r2.getPathInfo()); 		//null (null)
		System.out.println(httpRequest.getServletPath());	//servlet's path invoked! (/SessionHandler.do)
		//action of forms can have servlet url appended with .do so that servlet url can remain simple and here servleturl.do can be specially handled here.
		
		String[] actionString = httpRequest.getRequestURI().split("/");
		String action = actionString[actionString.length-1];
		String a = action.substring(0, action.length()-3);
		System.out.println("action substring: " + a);
		*/
		
		String s = httpRequest.getServletPath().substring(1);
		System.out.println(s);
		System.out.println(httpRequest.getRequestURI());
		
		switch (s) {
		case "login":
			if (httpRequest.getSession().getAttribute("user") == null) {
				httpRequest.getRequestDispatcher("login.jsp").forward(request, response);
			}
			else { //user must already be logged in
				httpResponse.sendRedirect("account");
			}
			return;
		case "account":
			if (httpRequest.getSession().getAttribute("user") == null) {
				httpResponse.sendRedirect("login");
			}
			else { //user must be logged in
				httpRequest.getRequestDispatcher("home.jsp").forward(request, response);
			}
			return;
		case "apply":
			if (httpRequest.getSession().getAttribute("user") == null) {
				httpResponse.sendRedirect("login");
			}
			else { //user must be logged in
				httpRequest.getRequestDispatcher("apply.jsp").forward(request, response);
			}
			return;
		case "app":
			if (httpRequest.getSession().getAttribute("user") == null) {
				httpResponse.sendRedirect("login");
			}
			else { //user must be logged in
				httpRequest.getRequestDispatcher("app.jsp").forward(request, response);
			}
			return;
		case "view_all":
			if (httpRequest.getSession().getAttribute("user") == null) {
				httpResponse.sendRedirect("login");
			}
			else { //user must be logged in
				httpRequest.getRequestDispatcher("view_all.jsp").forward(request, response);
			}
			return;
		case "view_apps":
			if (httpRequest.getSession().getAttribute("user") == null) {
				httpResponse.sendRedirect("login");
			}
			else { //user must be logged in
				httpRequest.getRequestDispatcher("view_apps.jsp").forward(request, response);
			}
			return;
		case "account_details":
			if (httpRequest.getSession().getAttribute("user") == null) {
				httpResponse.sendRedirect("login");
			}
			else { //user must be logged in
				httpRequest.getRequestDispatcher("acc.jsp").forward(request, response);
			}
			return;
		case "index.jsp": case "landing":
			httpRequest.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		case "css/main.css": case "media/network.jpg":
			chain.doFilter(request, response);
			return;
		case "login.do":
			chain.doFilter(request, response); //servlet handle
			return;
		case "logout.do":
			chain.doFilter(request, response); //servlet handle
			return;
		case "submit_application.do":
			chain.doFilter(request, response); //servlet handle
			return;
		default:
			httpRequest.getRequestDispatcher("/WEB-INF/404.html").forward(request, response);
			return;
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
