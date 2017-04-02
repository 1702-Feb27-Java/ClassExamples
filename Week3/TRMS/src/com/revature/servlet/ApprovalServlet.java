package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.pojo.Reimburstment;
import com.revature.service.Service;

/**
 * Servlet implementation class ApprovalServlet
 */
public class ApprovalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String initParamName;
	private String initParamValue;
	private String configParamName;
	private String configParamValue;
	Service service = new Service();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApprovalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		int alevel = Integer.parseInt(request.getParameter("approvelevel"));
		int reimid = Integer.parseInt(request.getParameter("approveid"));
		if(alevel == 1)
		{
			service.dsApprove(reimid);
			RequestDispatcher rd = request.getRequestDispatcher("Dashboard.jsp");
			rd.forward(request, response);
		}
		else if(alevel == 2)
		{
			service.dhApproval(reimid);
			RequestDispatcher rd = request.getRequestDispatcher("DSDashboard.jsp");
			rd.forward(request, response);
		}
		else if(alevel == 3)
		{
			service.bApproval(reimid);
			RequestDispatcher rd = request.getRequestDispatcher("DHDashboard.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
