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
		
		// Parse requested resource's url.
		String s = httpRequest.getServletPath().substring(1); //WHERE url LIKE /*
		System.out.println("Resource requested: " + s);
		System.out.println(httpRequest.getRequestURI());
		
		// Handle servlet requests.
		switch (s) {
		case "login.do": case "logout.do": case "submit_application.do": case "update_account.do":
		case "status_reimbursement.do": case "add_grade.do":
			chain.doFilter(request, response);
			return;
		}
		
		// Handle page requests.
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
		case "view_all":
			if (httpRequest.getSession().getAttribute("user") == null) {
				httpResponse.sendRedirect("login");
			}
			else { //user must be logged in
				httpRequest.getRequestDispatcher("view_all.jsp").forward(request, response);
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
		case "view_apps":
			if (httpRequest.getSession().getAttribute("user") == null) {
				httpResponse.sendRedirect("login");
			}
			else { //user must be logged in
				httpRequest.getRequestDispatcher("view_apps.jsp").forward(request, response);
			}
			return;
		case "view_app":
			if (httpRequest.getSession().getAttribute("user") == null) {
				httpResponse.sendRedirect("login");
			}
			else { //user must be logged in
				httpRequest.getRequestDispatcher("view_app.jsp").forward(request, response);
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
		case "landing": case "index.jsp": case "index": 
			httpRequest.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
		// Parse complex urls (e.g., nested directories).
		String medias = s.split("/")[0]; //WHERE url LIKE css/* OR LIKE media/*
		System.out.println("Resource requested: " + medias);
		
		// Handle media requests.
		switch (medias) {
		case "css": case "media":
			chain.doFilter(request, response);
			return;
		}
		
		// Handle requests that caught by none of the above: cannot locate resource.
		httpRequest.getRequestDispatcher("/WEB-INF/404.html").forward(request, response);
		return;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
