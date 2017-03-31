package Servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DAO;

/**
 * Servlet implementation class AttachProcess
 */
public class AttachProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   private String initParamName;
	    private String initParamValue,contValue,contName;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttachProcess() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig config) throws ServletException {
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
		HttpSession s = request.getSession();
		int val =   (int) s.getAttribute("val");
		String msg = request.getParameter("filebutton");
		System.out.println("");
		DAO d = new DAO();
		d.attachfile(val, msg);

		
		 request.getRequestDispatcher("/UploadAttachments.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
