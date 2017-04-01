package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DAOimp;
import Main.Main;
import User.UserInfo;

public class LoginServlet extends HttpServlet {

	private String initParamName, contextparamname;
	private String initParamValue, contextparamvalue;
	private UserInfo user;
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
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

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean loginSuccess = false;
		
		String Username = request.getParameter("uname");
		String Password = request.getParameter("pwd");
		// int dep_id = request.getParameter

		int role_id = 0;
		user = new UserInfo();
		user.setEmail(Username);
		HttpSession session = request.getSession(true);
		DAOimp temparr = new DAOimp();
		HttpSession helduserinfo = request.getSession();
		helduserinfo.setAttribute("currentuarr", temparr.getUserInfo(user) );
		
		System.out.println(helduserinfo.toString() + "did it work?");
		// session.setAttribute("LOL", "LOL234");
		try {

			PrintWriter out = response.getWriter();
			/* pulling information from login page to check against DB */
			// out.println("<br>");
			// response.getWriter().append(Username);
			// out.println("<br>");
			// response.getWriter().append(Password);
			// out.println("</body></html>");
			// out.println("<html><body>");
			DAOimp hnad = new DAOimp();
			
			System.out.println(Username + Password);
			// user = hnad.CreateRequest();
			user = hnad.getUserInfo(user);

			// user.setRole_id(role_id);
			loginSuccess = Main.validLogin(user, Password);
			System.out.println(loginSuccess);
			System.out.println(user.getRole_id());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (loginSuccess == true) {

			if (user.getDep_id() == 102) {
				request.getRequestDispatcher("/BenCo.jsp").forward(request, response);

			} else if (user.getRole_id() == 1) {
				request.getRequestDispatcher("/Employee.jsp").forward(request, response);
			} else if (user.getRole_id() == 2) {
				request.getRequestDispatcher("/Supervisor.jsp").forward(request, response);

			} else if (user.getRole_id() == 3) {
				request.getRequestDispatcher("/DepHead.jsp").forward(request, response);

			} else {
				request.getRequestDispatcher("/Signin.jsp").forward(request, response);

			}
		}

	}
}