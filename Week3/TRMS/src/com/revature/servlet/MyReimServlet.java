package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.pojo.Reimburstment;
import com.revature.service.Service;

/**
 * Servlet implementation class MyReimServlet
 */
public class MyReimServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String initParamName;
	private String initParamValue;
	private String configParamName;
	private String configParamValue;
	Service service = new Service();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyReimServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		Enumeration<String> iP = config.getInitParameterNames();
		initParamName = iP.nextElement();
		initParamValue = config.getInitParameter(initParamName);
		configParamName = config.getServletContext().getInitParameterNames().nextElement();
		configParamValue = config.getServletContext().getInitParameter(configParamName);
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		Reimburstment reim = new Reimburstment();
		reim = service.myReimburstment(request.getParameter("eid"));
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
