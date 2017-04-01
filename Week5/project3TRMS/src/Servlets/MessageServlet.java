package Servlets;

import java.io.IOException;
import java.util.Enumeration;
import DAO.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String initParamName;
    private String initParamValue,contValue,contName;

    public MessageServlet() {
        super();
    }

    		public void init(ServletConfig config) throws ServletException {
    		        try{
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

    
    		    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    

    		        
    		         request.getRequestDispatcher("/UploadAttachments.jsp").forward(request,response);
    		    }

    	
    		    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		        doGet(request, response);
    		    }

    		 

}
