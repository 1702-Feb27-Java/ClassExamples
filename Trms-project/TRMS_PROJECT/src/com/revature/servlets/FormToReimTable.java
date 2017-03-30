package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.*;
import com.revature.pojo.*;
import com.revature.servlets.*;

/**
 * Servlet implementation class FormToReimTable
 */
public class FormToReimTable extends HttpServlet {
	
	public static ArrayList<Reimbursement> this_form = new ArrayList();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormToReimTable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		EmployeeDAOImpl EDAO = new EmployeeDAOImpl();
		Reimbursement Rform = new Reimbursement();
		ReimbursementDAOImpl RfDAO = new ReimbursementDAOImpl();
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String un = request.getParameter("uname");
		
		HttpSession sessh = request.getSession(false);
		Employee e = (Employee)sessh.getAttribute("user");
		//sessh.setAttribute("user", e );
		
		
		String l = request.getParameter("loca");
		String d = request.getParameter("Current_date");	
		String sd = request.getParameter("start_date");
		String ed = request.getParameter("end_date");
		String cl = request.getParameter("course_length");
		String ct = request.getParameter("course_type");
		String gt = request.getParameter("grade type");
		String g = request.getParameter("actual_grade");
		
		Rform.setEmp_id(e.getEmp_id());
		Rform.setLocation_(l);
		Rform.setAdd_date(d);
		Rform.setStart_course(sd);
		Rform.setEnd_course(ed);
		Rform.setCourse_time(cl);
		Rform.setCourse_id( RfDAO.getCourseID(ct) );
		Rform.setGrade_type_id(RfDAO.getGradeID(gt));
		Rform.setGrade(g);
		
		this_form.add(Rform);
		
		//checking reim object before putting it into database
		for(Reimbursement R: this_form ) {
			System.out.println(R);
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
