package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.Reimburse;
import service.Service;

/**
 * Servlet implementation class Advance
 */
public class Advance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Advance() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service serv = new Service();
		serv.advanceDay();
		
		ArrayList<Reimburse> rem = serv.getAllReim();
		
		for (Reimburse r : rem){ //checks if a reimbursement should be auto approved after the day updates
			int stat = serv.getStatus(r.getReim_id());
			if(r.getNumDay() >= 7 && (stat == 1 || stat == 2)){
				serv.updateStatus(r.getReim_id(), 3);
				serv.updateNumDay(r.getReim_id());
			}
			else if(r.getNumDay() >= 7 && (stat == 3 || stat == 4)){
				serv.updateStatus(r.getReim_id(), 5);
				serv.updateNumDay(r.getReim_id());
			}
		}
			
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res =(HttpServletResponse)response;
		RequestDispatcher rd;
		rd = req.getRequestDispatcher("updateDay.jsp");
		rd.forward(req, res);			
		return;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
