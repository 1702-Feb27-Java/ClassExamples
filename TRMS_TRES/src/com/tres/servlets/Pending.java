package com.tres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tres.objs.Reimbursement;
import com.tres.service.ServiceImp;

/**
 * Servlet implementation class Pending
 */
public class Pending extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static ServiceImp serve = new ServiceImp();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pending() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpServletRequest req= (HttpServletRequest) request;
		HttpServletResponse res= (HttpServletResponse) response;
		System.out.println("LOGINUR PENDING: " +  req.getRequestURI());
		String [] sa = req.getRequestURI().split("/");
		String action = sa[sa.length-1];
		RequestDispatcher rs; 
		HttpSession sess = request.getSession(false);
		switch (action)
		{
			case "my":
				ArrayList<Reimbursement> mypend = serve.getPending((int)sess.getAttribute("uid")); if (!mypend.isEmpty())
				{
					sess.setAttribute("mypending", mypend);
					rs = request.getRequestDispatcher("/my_pending.jsp");
					rs.forward(request, response);
				}
				else
				{
					rs = request.getRequestDispatcher("/empty.jsp");
					rs.forward(request, response);
				}
				break;
			case "others":
				ArrayList<Reimbursement> otpend = new ArrayList<>();
				if ((int) sess.getAttribute("deptid") == 1 ||  (int)sess.getAttribute("roleid")== 4)
				{
					
					otpend = serve.getHvPending(0);
				}
				else	
				{
					 otpend = serve.getHvPending((int)sess.getAttribute("uid"));
				}
				Set<String> users = new HashSet<String>();
				for (Reimbursement r: otpend)
				{
					users.add(r.getCreator());
				}
				if (!otpend.isEmpty())
				{
					sess.setAttribute("mypending", otpend);
					sess.setAttribute("mybelows", users);
					rs = request.getRequestDispatcher("/otherpending.jsp");
					rs.forward(request, response);
				}
				else
				{
					rs = request.getRequestDispatcher("/empty.jsp");
					rs.forward(request, response);
				}
				break;
			case "decline":
				String reason = "Reason it was Declined: " + request.getParameter("reason");
				int reim_id = Integer.parseInt(request.getParameter("reimid"));
				int rcv_id = Integer.parseInt(request.getParameter("rcvid"));
				serve.updateAddMsg((int)sess.getAttribute("uid"), rcv_id, reason, reim_id, 2);
				rs = request.getRequestDispatcher("/employee.jsp");
				rs.forward(request, response);
				break;
			case "approve":
				System.out.println("APPROVE");
				int uid = (int)sess.getAttribute("uid");
				int repid = (int) sess.getAttribute("repid");
				reim_id = Integer.parseInt(request.getParameter("reimid"));
				rcv_id = Integer.parseInt(request.getParameter("rcvid"));
				int apprid = Integer.parseInt(request.getParameter("apprid"));
				if(apprid == 3)
				{
					
				}
				else
				{
					serve.updateReim(reim_id, apprid+1, repid,(int)sess.getAttribute("uid"),rcv_id);
					System.out.println("UPDATE LVL");
					rs = request.getRequestDispatcher("/employee.jsp");
					rs.forward(request, response);
				}
				break;
			case "infoo":
				
				break;
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
