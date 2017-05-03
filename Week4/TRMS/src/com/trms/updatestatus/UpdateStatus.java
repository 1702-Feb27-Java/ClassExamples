package com.trms.updatestatus;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trms.dao.TrmsDao;

/**
 * Servlet implementation class UpdateStatus
 */
public class UpdateStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStatus() {
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
		System.out.println(request.getParameter("additonalinfo") + "   add info");
		System.out.println(request.getParameter("rejectinput") + "    reject");
		System.out.println(request.getParameter("approveinput")+ "    aapporve");
		
		//statusupdateto 1 will be approve, 2 will be reject, 3 will be more info
		
		if(request.getParameter("approveinput") != null){
			int reqid = Integer.parseInt(request.getParameter("approveinput"));
			int status = TrmsDao.getStatus(reqid);
			if(status < 4){
				status++;
			}
			TrmsDao.updateStatus(reqid, status);
			request.getRequestDispatcher("/GeneratePending").forward(request, response);
		}else if(request.getParameter("rejectinput") != null){
			int reqid = Integer.parseInt(request.getParameter("rejectinput"));
			TrmsDao.updateStatus(reqid, 7);
			request.getRequestDispatcher("/GeneratePending").forward(request, response);
			
		}
		
		if(request.getParameter("additionalinfo") != null){
			int reqid = Integer.parseInt(request.getParameter("additionalinfo"));
			TrmsDao.requestAdditionalInfo(reqid);
			request.getRequestDispatcher("/GeneratePending").forward(request, response);
		}

	}

}
