package Servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * Servlet implementation class RejectionDirect
 */
public class RejectionDirect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   private String initParamName;
	    private String initParamValue,contValue,contName;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RejectionDirect() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		int val  = Integer.parseInt(request.getParameter("val"));
		HttpSession s  = request.getSession();
		s.setAttribute("val", val);
		//s.getAttribute("val");
		request.getRequestDispatcher("/RejectEmployee.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}