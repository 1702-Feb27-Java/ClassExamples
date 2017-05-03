package com.trms.pending;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.trms.dao.TrmsDao;

/**
 * Servlet implementation class GeneratePending
 */
public class GeneratePending extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeneratePending() {
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
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		ArrayList<Integer> pending = TrmsDao.getListOfPendingId(username);
		System.out.println(pending);
		
		session.setAttribute("listsize", pending.size());
		for(int i =0; i < pending.size(); i++){
			ArrayList<Object> reqStatAward = TrmsDao.getReqStatAward(pending.get(i));
			session.setAttribute("reqid"+i, pending.get(i));
			session.setAttribute("submitdate"+i, TrmsDao.getDateSubmitted(pending.get(i)));
			session.setAttribute("coursetype"+i, TrmsDao.getCourseType(pending.get(i)));
			session.setAttribute("reqamount"+i, reqStatAward.get(0));
			session.setAttribute("status"+i, reqStatAward.get(1));
			session.setAttribute("award"+i, reqStatAward.get(2));
			session.setAttribute("addinfoemp"+i, TrmsDao.getAddInfoStatus(pending.get(i)));
		}
		
		boolean isDepHead = TrmsDao.checkIfDepHead(username);
		boolean isSuper = TrmsDao.checkIfSuper(username);
		boolean isBenCo = TrmsDao.checkIfBenCo(username);
		
		ArrayList<Integer> pendingDepHead = TrmsDao.getListOfPendingDepHead(username);
		ArrayList<Integer> pendingSuper = TrmsDao.getListOfPendingSuper(username);
		ArrayList<Integer> pendingBenCo = TrmsDao.getListOfPendingBenCo(username);
		if(isDepHead){
			session.setAttribute("listsizeDepHead", pendingDepHead.size());
			 for(int i =0; i < pendingDepHead.size(); i++){
				 ArrayList<Object> reqStatAward = TrmsDao.getReqStatAward(pendingDepHead.get(i));
				 session.setAttribute("reqiddephead"+i, pendingDepHead.get(i));
				 session.setAttribute("submitdatedephead"+i, TrmsDao.getDateSubmitted(pendingDepHead.get(i)));
				 session.setAttribute("coursetypedephead"+i, TrmsDao.getCourseType(pendingDepHead.get(i)));
				 session.setAttribute("reqamountdephead"+i, reqStatAward.get(0));
				 session.setAttribute("statusdephead"+i, reqStatAward.get(1));
				 session.setAttribute("awarddephead"+i, reqStatAward.get(2));
				 session.setAttribute("urgency"+i, TrmsDao.getUrgent(pendingDepHead.get(i)));
				 session.setAttribute("addinfo"+i, TrmsDao.getAddInfoStatus(pendingDepHead.get(i)));
			 }
		}else if(isSuper){
			session.setAttribute("listsizeSuper", pendingSuper.size());
			 for(int i =0; i < pendingSuper.size(); i++){
				 ArrayList<Object> reqStatAward = TrmsDao.getReqStatAward(pendingSuper.get(i));
				 session.setAttribute("reqidsuper"+i, pendingSuper.get(i));
				 session.setAttribute("submitdatesuper"+i, TrmsDao.getDateSubmitted(pendingSuper.get(i)));
				 session.setAttribute("coursetypesuper"+i, TrmsDao.getCourseType(pendingSuper.get(i)));
				 session.setAttribute("reqamountsuper"+i, reqStatAward.get(0));
				 session.setAttribute("statussuper"+i, reqStatAward.get(1));
				 session.setAttribute("awardsuper"+i, reqStatAward.get(2));
				 session.setAttribute("urgency"+i, TrmsDao.getUrgent(pendingSuper.get(i)));
				 session.setAttribute("addinfo"+i, TrmsDao.getAddInfoStatus(pendingSuper.get(i)));
			 }
			
		}
		
		if(isBenCo){
			session.setAttribute("listsizeBenCo", pendingBenCo.size());
			for(int i =0; i < pendingBenCo.size(); i++){
				 ArrayList<Object> reqStatAward = TrmsDao.getReqStatAward(pendingBenCo.get(i));
				 session.setAttribute("reqidbenco"+i, pendingBenCo.get(i));
				 session.setAttribute("submitdatebenco"+i, TrmsDao.getDateSubmitted(pendingBenCo.get(i)));
				 session.setAttribute("coursetypebenco"+i, TrmsDao.getCourseType(pendingBenCo.get(i)));
				 session.setAttribute("reqamountbenco"+i, reqStatAward.get(0));
				 session.setAttribute("statusbenco"+i, reqStatAward.get(1));
				 session.setAttribute("awardbenco"+i, reqStatAward.get(2));
				 session.setAttribute("urgency"+i, TrmsDao.getUrgent(pendingBenCo.get(i)));
				 session.setAttribute("addinfo"+i, TrmsDao.getAddInfoStatus(pendingBenCo.get(i)));
			 }
			
			
		}
		
		
		session.setAttribute("isDepHead", isDepHead);
		session.setAttribute("isSuper", isSuper);
		session.setAttribute("isBenCo", isBenCo);
		response.sendRedirect("pending.jsp");
				
	}

}

























