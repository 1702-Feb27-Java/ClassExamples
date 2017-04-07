package Servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAO;

/**
 * Servlet implementation class SignUpFormHandler
 */
public class SignUpFormHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String initParamName;
    private String initParamValue,contValue,contName;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpFormHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
		try
		{
		System.out.println("INIT HELLO WORLD");
		Enumeration<String> iP = config.getInitParameterNames();
		
		initParamName = iP.nextElement();
		initParamValue = config .getInitParameter(initParamName);
		contName = config.getServletContext().getInitParameterNames().nextElement();
		contValue = config.getServletContext().getInitParameter(contName);
	    
		
		}
		
		catch(Exception e)
		{
			
			System.out.println(e.getMessage());
		}
		
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String appdate = request.getParameter("AppDate");
		String  startdate = request.getParameter("startdate");
		String enddate  = request.getParameter("enddate");
		int totalcoursetime = Integer.parseInt(request.getParameter("totalcoursetime"));
		String location = request.getParameter("Location");
		String description = request.getParameter("Description");
		int Cost = Integer.parseInt(request.getParameter("Cost"));
		String justify = request.getParameter("Justify");
		int eventtype = Integer.parseInt(request.getParameter("EventType"));
		int gradetype = Integer.parseInt(request.getParameter("gradetype"));
		String approvalemail = request.getParameter("approveemail");
		
		DAO d = new DAO();
    	d.submitReinbursment(startdate,enddate,totalcoursetime,location,description,Cost,justify,eventtype,gradetype,approvalemail,appdate);

		 request.getRequestDispatcher("/Employee.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
