package Servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DAOimp;
import User.UserInfo;
import User.appinfo;

public class AppFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HttpSession var; 
	public AppFormServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		var = request.getSession();
		UserInfo user = (UserInfo) var.getAttribute("currentuarr");
		int eid =user.getEmployee_id();
		doGet(request, response);

		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String location = request.getParameter("location");
		String enddate = request.getParameter("enddate");
		String startdate = request.getParameter("startdate");
		int coursecost = Integer.parseInt(request.getParameter("coursecost"));
		String selectcoursetype = request.getParameter("selectcoursetype");
		String coursetypetext = request.getParameter("coursetypetext");
		String gradeformat = request.getParameter("gradeformat");
		String gradeformattext = request.getParameter("gradeformattext");
		String reason = request.getParameter("reason");
		int courseduration = Integer.parseInt(request.getParameter("courseduration"));
	
		
		
		appinfo insertuserinfo = new appinfo();
		insertuserinfo.setFirstname(firstname);
		insertuserinfo.setLastname(lastname);
		insertuserinfo.setLocation(location);
		insertuserinfo.setEnddate(enddate);
		insertuserinfo.setStartdate(startdate);
		insertuserinfo.setCoursecost(coursecost);
		insertuserinfo.setSelectcoursetype(selectcoursetype);
		insertuserinfo.setCoursetypetext(coursetypetext);
		insertuserinfo.setGradeformat(gradeformat);
		insertuserinfo.setGradeformattext(gradeformattext);
		insertuserinfo.setReason(reason);
		insertuserinfo.setEmployee_id(eid);
		
		DAOimp insert = new DAOimp();
		
		insert.ApplicationUpdate(insertuserinfo, request);
		
		request.getRequestDispatcher("/Signin.jsp").forward(request, response);

		
	}

}
