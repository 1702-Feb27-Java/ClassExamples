package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.pojo.Grade;
import com.revature.service.LookupService;

/**
 * Servlet implementation class ReimbursementServlet
 */
public class ReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LookupService ls = new LookupService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession currSession = request.getSession();
		ArrayList<Grade> grades = ls.getGrades();
		ArrayList<String> gradeStrings = new ArrayList<String>();
		for (Grade g : grades){
			gradeStrings.add(g.getGradeFormat());
		}
		request.setAttribute("departments", ls.getDepts());
		request.setAttribute("eventTitles", ls.getEventTitles());
		request.setAttribute("gradeStrings", gradeStrings);
		String nextJSP = "/reimbursement_form.jsp";
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
