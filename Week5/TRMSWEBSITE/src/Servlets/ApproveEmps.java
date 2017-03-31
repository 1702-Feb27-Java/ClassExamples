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
 * Servlet implementation class ApproveEmps
 */
public class ApproveEmps extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String initParamName;
    private String initParamValue,contValue,contName;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveEmps() {
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
			}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int approved =  Integer.parseInt(request.getParameter("val"));
		DAO d = new DAO();
		d.approveVal(approved);
		System.out.println("THE VALUE OF THIS BUTTON PRESSED IS--> " +approved );
		
		 request.getRequestDispatcher("/ApproveEmployeeLevel.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
