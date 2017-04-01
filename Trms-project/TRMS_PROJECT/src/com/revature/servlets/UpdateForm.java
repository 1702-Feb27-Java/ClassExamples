package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.*;
import com.revature.pojo.*;

/**
 * Servlet implementation class UpdateForm
 */
public class UpdateForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateForm() {
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
		ReimbursementDAOImpl RfDAO = new ReimbursementDAOImpl();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int emp_id = Integer.parseInt(request.getParameter("emp_id"));
		int app_id = Integer.parseInt(request.getParameter("app_id"));
				
		RfDAO.updateApproval_id(app_id, emp_id);
	}

}
