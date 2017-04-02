package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.pojo.Reimburstment;
import com.revature.service.Service;

/**
 * Servlet implementation class LoginServlet
 */
public class MakeReimServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String initParamName;
	private String initParamValue;
	private String configParamName;
	private String configParamValue;
	Service service = new Service();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeReimServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		Reimburstment reim = new Reimburstment();
		reim.setEmp_id(Integer.parseInt(request.getParameter("emp_id")));
		reim.setLocation(request.getParameter("relocation"));
		reim.setAddDate(request.getParameter("addDate"));
		reim.setCourseStartDate(request.getParameter("startDate"));
		reim.setCourseEndDate(request.getParameter("endDate"));
		reim.setTime(request.getParameter("courseTime"));
		reim.setCourseCost(Integer.parseInt(request.getParameter("courseCost")));
		reim.setReimburstAmt(Integer.parseInt(request.getParameter("reimAmt")));
		reim.setCourseID(1);
		reim.setApproval(1);
		reim.setGradeTypeID(Integer.parseInt(request.getParameter("gradeTypeID")));
		service.makeReimburstment(reim);
		if(reim.getApproval() == 1)
		{
			RequestDispatcher rd = request.getRequestDispatcher("Dashboard.jsp");
			rd.forward(request, response);
		}
		else if(reim.getApproval() == 2)
		{
			RequestDispatcher rd = request.getRequestDispatcher("DSDashboard.jsp");
			rd.forward(request, response);
		}
		else if(reim.getApproval() == 3)
		{
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
