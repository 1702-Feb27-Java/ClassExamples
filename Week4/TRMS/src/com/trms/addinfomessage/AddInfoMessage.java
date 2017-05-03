package com.trms.addinfomessage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.trms.dao.TrmsDao;

/**
 * Servlet implementation class AddInfoMessage
 */
public class AddInfoMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddInfoMessage() {
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
		
		String message = request.getParameter("addinfo");
		int reqidtoadd = Integer.parseInt(request.getParameter("addinfotoid"));
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		TrmsDao.requestAdditionalInfo(reqidtoadd);
		TrmsDao.additionalInfoMessage(username, reqidtoadd, message);
		request.getRequestDispatcher("/GeneratePending").forward(request, response);
		
		
		
	}

}
