package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.pojo.Employee;
import com.revature.pojo.Reimbursement;
import com.revature.service.ReimbursementService;

/**
 * Servlet implementation class ApproveServlet
 */
public class ApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ReimbursementService rs = new ReimbursementService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession currSession = request.getSession();
		Employee e = (Employee) currSession.getAttribute("employee");
		if(request.getParameterMap().containsKey("reimbursementId")) {
			int rId = Integer.parseInt(request.getParameter("reimbursementId"));
			Reimbursement r = rs.getReimbursementsByReimbursementId(rId);	
			rs.approve(r, e);
		}
		if(request.getParameterMap().containsKey("finalizableId")) {
			int fId = Integer.parseInt(request.getParameter("finalizableId"));
			Reimbursement r = rs.getReimbursementsByReimbursementId(fId);
			rs.benCoApprove(r);
		}
		if(request.getParameterMap().containsKey("declinedReimbId")) {
			int fId = Integer.parseInt(request.getParameter("declinedReimbId"));
			Reimbursement r = rs.getReimbursementsByReimbursementId(fId);
			rs.benCoApprove(r);
		}
		if(request.getParameterMap().containsKey("benCoDeniedReimbId")) {
			int fId = Integer.parseInt(request.getParameter("benCoDeniedReimbId"));
			Reimbursement r = rs.getReimbursementsByReimbursementId(fId);
			rs.benCoApprove(r);
		}
		if(request.getParameterMap().containsKey("requestInfoId")) {
			int fId = Integer.parseInt(request.getParameter("requestInfoId"));
			Reimbursement r = rs.getReimbursementsByReimbursementId(fId);
			rs.benCoApprove(r);
		}
		String nextJSP = "/completion.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(nextJSP);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
