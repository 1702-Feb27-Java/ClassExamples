package Servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAO;
import Employee.Collecters;

/**
 * Servlet implementation class ApproveBenco
 */
public class ApproveBenco extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String initParamName;
    private String initParamValue,contValue,contName;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveBenco() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
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
	}
	
	private double finder(int val)
	{
		switch(val)
		{
	case 1:
			return .8;
		case 2:
			return .6;
		case 3:
			return .75;
		case 4:
			return 1;
		case 5:
			return .9;
		case 6:
			return .3;
	}
		return val;
		
		
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int approved =  Integer.parseInt(request.getParameter("val"));
		DAO d = new DAO();
		
		d.failone(approved,(int)((double)Collecters.rein.get(approved).getCoursecost() * (double)finder(Collecters.rein.get(approved).getAPP_ID())));
		d.approveVal(approved,0);
		System.out.println("THE VALUE OF THIS BUTTON PRESSED IS--> " +approved );
		
		 request.getRequestDispatcher("/BencoApproval.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
