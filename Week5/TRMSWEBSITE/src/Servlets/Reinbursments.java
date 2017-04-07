package Servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Reinbursments
 */
public class Reinbursments extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String initParamName;
    private String initParamValue,contValue,contName;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reinbursments() {
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
		
		
		try
		{
		String selection = request.getParameter("item");
		response.getWriter().append(selection);
		
		
		
		
		switch(selection)
		{
		
		case "":
			 request.getRequestDispatcher("Controller").forward(request,response);
			break;
		
		case "SignUpForReinbursment":
			 request.getRequestDispatcher("/SignUpForReinbursment.jsp").forward(request,response);
			break;
		case "ViewReinbursmentStatus":
			 request.getRequestDispatcher("/ReinbursmentStatus.jsp").forward(request,response);
			break;
		case "InitiateApproval":
			 request.getRequestDispatcher("/ApprovalEmail.jsp").forward(request,response);
			break;
		case "ApproveApplications":
			 request.getRequestDispatcher("/ApproveEmployeeLevel.jsp").forward(request,response);
			break;
		case "ApproveReinbursments":
			 request.getRequestDispatcher("/BencoApproval.jsp").forward(request,response);
			break;
		case "UploadAttachments":
			 request.getRequestDispatcher("/UploadAttachments.jsp").forward(request,response);
		
			break;
		case "Messages":
			 request.getRequestDispatcher("/Messages.jsp").forward(request,response);
		default:
				break;
		
		}
		}
		catch(Exception e)
		{
			
			
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
