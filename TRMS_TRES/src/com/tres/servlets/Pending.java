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
				ArrayList<Double>usersBal = new ArrayList<>();
				for (Reimbursement r: otpend)
				{
					users.add(r.getCreator());
					usersBal.add(serve.getBalance(r.getEmid()));
				}
				if (!otpend.isEmpty())
				{
					sess.setAttribute("mypending", otpend);
					sess.setAttribute("mybelows", users);
					sess.setAttribute("theirBal", usersBal);
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
				double bal = serve.getBalance(rcv_id);
				serve.updateAddMsg((int)sess.getAttribute("uid"), rcv_id, reason, reim_id, 2);
				rs = request.getRequestDispatcher("/employee.jsp");
				rs.forward(request, response);
				break;
			case "approve":
				String msg = "APPROVED ";
				System.out.println("APPROVE");
				int uid = (int)sess.getAttribute("uid");
				int repid = (int) sess.getAttribute("repid");
				reim_id = Integer.parseInt(request.getParameter("reimid"));
				double crscost = Double.parseDouble(request.getParameter("crscost"));
				rcv_id = Integer.parseInt(request.getParameter("rcvid"));
				int apprid = Integer.parseInt(request.getParameter("apprid"));
				bal = serve.getBalance(rcv_id);
				if(apprid == 3)
				{
				// DO SOMETHING 
					msg = "Finally Approved";
					serve.updateAddMsg(uid, rcv_id, msg, reim_id, 3);
					bal -= crscost;
					serve.updateUser(rcv_id, bal);
					rs = request.getRequestDispatcher("/employee.jsp");
					rs.forward(request, response);
				}
				else
				{
					serve.updateReim(reim_id, apprid+1, repid,(int)sess.getAttribute("uid"),rcv_id, msg);
					System.out.println("UPDATE LVL");
					rs = request.getRequestDispatcher("/employee.jsp");
					rs.forward(request, response);
				}
				break;
			case "infoo":
				System.out.println("INFO");
				reason = "Info Needed: " + request.getParameter("infoReq");
				uid = (int)sess.getAttribute("uid");
				reim_id = Integer.parseInt(request.getParameter("reimid"));
				rcv_id = Integer.parseInt(request.getParameter("rcvid"));
				serve.addMsg(reim_id, uid, rcv_id, reason );
				rs = request.getRequestDispatcher("/employee.jsp");
				rs.forward(request, response);
				break;
			case "approve2":
				uid = (int)sess.getAttribute("uid");
				repid = (int) sess.getAttribute("repid");
				reim_id = Integer.parseInt(request.getParameter("reimid"));
				rcv_id = Integer.parseInt(request.getParameter("rcvid"));
				double cst = Double.parseDouble(request.getParameter("cst"));
				bal = serve.getBalance(rcv_id);
				if( cst > bal)
				{
					bal =0;
				}
				else
					bal-=cst;
				msg = "APPROVED ADJUSTED AMOUNT: " + request.getParameter("reason");
				serve.updateAddMsg(uid, rcv_id, msg, reim_id, 3);
				serve.updateUser(rcv_id, bal);
				serve.updateReimAmnt(reim_id, cst);
				rs = request.getRequestDispatcher("/employee.jsp");
				rs.forward(request, response);

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
