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
 * Servlet Filter implementation class RoutingFilter
 */
public class RoutingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public RoutingFilter() {
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
		HttpServletResponse res = (HttpServletResponse)response;
		
		String[] sa = req.getRequestURI().split("/");
		
		String action = sa[sa.length-1];
		
		
		action = action.substring(0, action.length()-3);
		RequestDispatcher rd;
		
		switch(action){
		case "SessionHandler2":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		case "LoginServlet":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		case "CreateReimbursement":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		case "SubmitReimbursement":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		case "MessageServlet":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		case "ReimbursementsServlet":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		case "LogoutServlet":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		case "EditReimbursementServlet":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		case "SubmitEditServlet":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		case "PendingReimbursementsServlet":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		case "ApproveReimbursementServlet":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		case "ConfirmApproveServlet":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		case "SendMessageServlet":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		case "GetApprovedReimbursements":
			rd = req.getRequestDispatcher(action);
			rd.forward(req, res);
			return;
		case "AddGrade":
			rd = req.getRequestDispatcher(action);
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
