package com.trms.reimbursementserv;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.trms.dao.TrmsDao;

/**
 * Servlet implementation class Reimbursement
 */
public class Reimbursement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int coursetype = Integer.parseInt(request.getParameter("coursetype"));
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		double cost = Double.parseDouble(request.getParameter("cost"));
		double requestamount = Double.parseDouble(request.getParameter("requestamount"));
		String location = request.getParameter("location");
		int gradeformat = Integer.parseInt(request.getParameter("gradeformat"));
		String description = request.getParameter("description");
		String workjust = request.getParameter("workjust");
		String currentdate = request.getParameter("currentdate");
		String currenttime = request.getParameter("currenttime");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		boolean allFieldsPassed = true;

		double[] reimbPercentage = { 0.80, 0.60, 0.75, 1.0, 0.90, 0.30 };

		int status = 1;
		if (TrmsDao.checkIfDepHead(username)) {
			status = 3;
		} else if (TrmsDao.checkIfSuper(username)) {
			status = 2;
		}

		Date startdateobj = null;
		try {
			startdateobj = new SimpleDateFormat("MM-dd-yyyy").parse(startdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date enddateobj = null;
		try {
			enddateobj = new SimpleDateFormat("MM-dd-yyyy").parse(enddate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int daysApart = (int) ((enddateobj.getTime() - startdateobj.getTime()) / (1000 * 60 * 60 * 24l));
		System.out.println("day apart start end"+daysApart);
		if (daysApart <= 0) {
			session.setAttribute("enddatetooshort", true);
			allFieldsPassed = false;
		}

		Date currdateobj = null;
		try {
			currdateobj = new SimpleDateFormat("MM/dd/yyyy").parse(currentdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		daysApart = (int) ((startdateobj.getTime() - currdateobj.getTime()) / (1000 * 60 * 60 * 24l));
		System.out.println("day apart curr start"+daysApart);
		if (daysApart < 7) {
			session.setAttribute("invalidstartdate", true);
			allFieldsPassed = false;
		}

		double availFunds = TrmsDao.getAvailFunds(username);

		if (requestamount > availFunds) {
			session.setAttribute("notenoughfunds", true);
			allFieldsPassed = false;
		}

		double availRequest = cost * (reimbPercentage[coursetype - 1]);
		if (requestamount > availRequest) {
			session.setAttribute("requesttoomuch", true);
			allFieldsPassed = false;
		}

		if (allFieldsPassed) {
			TrmsDao.requestReimbursement(coursetype, startdate, enddate, cost, requestamount, location, gradeformat,
					description, workjust, currentdate, currenttime, username, status);
			TrmsDao.updateFunds(username, requestamount);
			if(daysApart <= 14){
				TrmsDao.markUrgent(username);
			}
			response.sendRedirect("requestSubmitted.html");
		}else{
			response.sendRedirect("reimbursement.jsp");
		}

	}

}
