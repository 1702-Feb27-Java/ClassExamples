package Servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class updateForm extends HttpServlet {

	private String initParamName, contextparamname;
	private String initParamValue, contextparamvalue;

	private static final long serialVersionUID = 1L;

	public updateForm() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		try {
			contextparamname = config.getServletContext().getInitParameterNames().nextElement();
			contextparamvalue = config.getServletContext().getInitParameter("ApplicationWide");
			Enumeration<String> ip = config.getInitParameterNames();

			initParamName = ip.nextElement();
			initParamValue = config.getInitParameter(initParamName);
		} catch (Exception e) {

		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
