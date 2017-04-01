package Servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RequestDirect
 */
public class RequestDirect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   private String initParamName;
	    private String initParamValue,contValue,contName;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestDirect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try{
			System.out.println("INIT");
			Enumeration<String> iP = config.getInitParameterNames();
			
			initParamName = iP.nextElement();
			initParamValue = config .getInitParameter(initParamName);
			contName = config.getServletContext().getInitParameterNames().nextElement();
			contValue = config.getServletContext().getInitParameter(contName);
			}
			
			catch(Exception e)
			{
				
				
			}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int val  = Integer.parseInt(request.getParameter("val"));
			HttpSession s  = request.getSession();
			s.setAttribute("val", val);
			//s.getAttribute("val");
			request.getRequestDispatcher("/RequestMoreInfo.jsp").forward(request,response);
			
		} catch (Exception e) {
			// TODO: handle exception
		 System.out.println("WHAT THE FUCK IS GOING ON"+e.getMessage());
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
