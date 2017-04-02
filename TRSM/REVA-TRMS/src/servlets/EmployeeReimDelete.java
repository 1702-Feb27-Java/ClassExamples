package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objects.Employee;
import objects.Reimburse;
import service.Service;

/**
 * Servlet implementation class EmployeeReimDelete
 */
public class EmployeeReimDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeReimDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sess = request.getSession();
		Employee e = (Employee)sess.getAttribute("employee");
		Reimburse re = (Reimburse)sess.getAttribute("re");
		Service serv = new Service();
		
		if(request.getParameter("delete") != null){
			
			e.setPending(e.getPending() - re.getCost());
			serv.updatePending(e.getUserName(), e.getPending());
			
			serv.deleteReim(re.getReim_id());
		}
		else if(request.getParameter("accepted") != null){
			
			e.setAwarded(e.getAwarded() + re.getCost());
			e.setPending(e.getPending() - re.getCost());
			serv.updatePending(e.getUserName(), e.getPending());
			serv.updateAwarded(e.getUserName(), e.getAwarded());
			serv.updateStatus(re.getReim_id(), 7);
		}
		else{
			serv.deleteReim(re.getReim_id());
		}
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res =(HttpServletResponse)response;
		RequestDispatcher rd;
		rd = req.getRequestDispatcher("Menu.jsp");
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
