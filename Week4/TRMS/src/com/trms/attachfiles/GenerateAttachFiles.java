package com.trms.attachfiles;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.trms.dao.TrmsDao;

/**
 * Servlet implementation class GenerateAttachFiles
 */
public class GenerateAttachFiles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateAttachFiles() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int reqid =  Integer.parseInt(request.getParameter("addinfo"));
		String message = TrmsDao.getAddInfoMessage(reqid);
		int empid = TrmsDao.getAddInfoFrom(reqid);
		String fromUser = TrmsDao.getUsername(empid);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("addinfomessage", message);
		session.setAttribute("addinfofrom", fromUser);
		session.setAttribute("addinforeqid", reqid);
		
		request.getRequestDispatcher("empAttachFiles.jsp").forward(request, response);
		
		
	}

}
