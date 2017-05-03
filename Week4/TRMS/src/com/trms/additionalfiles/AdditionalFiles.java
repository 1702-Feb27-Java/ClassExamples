package com.trms.additionalfiles;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.trms.dao.TrmsDao;

/**
 * Servlet implementation class AdditionalFiles
 */
public class AdditionalFiles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdditionalFiles() {
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
		String fileLocation = request.getParameter("addfiles");
		HttpSession session = request.getSession();
		int reqid = (int)session.getAttribute("addinforeqid");
		TrmsDao.setAddInfoResolvedToTrue(reqid);
		TrmsDao.newAttachment(fileLocation, reqid);
		TrmsDao.setRequestsAddInfoToFalse(reqid);
		response.sendRedirect("dashboard.jsp");
	}

}
