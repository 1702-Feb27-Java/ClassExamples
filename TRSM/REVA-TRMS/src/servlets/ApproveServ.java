package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objects.Employee;
import objects.Reimburse;
import service.Service;

/**
 * Servlet implementation class ApproveServ
 */
public class ApproveServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(request.getParameter("choice"));
		System.out.println(request.getParameter("message"));
		HttpSession sess = request.getSession();
		Service serv = new Service();
		String decision = request.getParameter("choice");
		
		if(decision.equals("approve")){
			Reimburse re = ((Reimburse)sess.getAttribute("reim"));
			Employee e = ((Employee)sess.getAttribute("reimEm"));
			int stat = serv.getStatus(re.getReim_id());
			
			
			if(stat == 1 || stat == 2){ //superviser approved need to set approve to dehead
				serv.updateStatus(re.getReim_id(), 3);
				Employee dH = serv.getDepartmentHead(e.getReportsto());
				serv.setApprover(re.getReim_id(), dH.geteId());
			}
			else if(stat == 3 || stat == 4){
				serv.updateStatus(re.getReim_id(), 5);
				//put into benCo
			}
			else if(stat == 5 || stat == 6 || stat == 7){
				serv.updateStatus(re.getReim_id(), 7);
				//we good
			}
		}
		else if(decision.equals("request info")){
			Reimburse re = ((Reimburse)sess.getAttribute("reim"));
			int stat = serv.getStatus(re.getReim_id());
			
			if(stat == 1 || stat == 2){
				serv.updateStatus(re.getReim_id(), 2);
			}
			else if(stat == 3 || stat == 4){
				serv.updateStatus(re.getReim_id(), 4);
			}
			else if(stat == 5 || stat == 8 || stat == 9){
				serv.updateStatus(re.getReim_id(), 6);
			}
				
		}
		else if(decision.equals("deny")){
			Reimburse re = ((Reimburse)sess.getAttribute("reim"));
			serv.updateStatus(re.getReim_id(), 10);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
