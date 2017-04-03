package servlet.reimbursement;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.model.Reimbursement;
import database.model.User;
import database.service.Service;
//impor database.model.Reimbursement;

/**
 * Servlet implementation class showTransactions
 */
public class ListReimbursements extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListReimbursements() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String path = request.getServletPath();
		User user = (User) request.getSession().getAttribute("loggedInUser");

		List<Reimbursement> list = Service.getInstance().getReimbursementsFromUser(user);
		request.setAttribute("list", list);
		

		
		RequestDispatcher rd = request.getRequestDispatcher("listReimbursements.jsp");
		rd.forward(request, response);
	
	}



	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
