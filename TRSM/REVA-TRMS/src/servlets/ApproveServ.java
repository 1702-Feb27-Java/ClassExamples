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
		String mess = request.getParameter("message");
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
				serv.updateNumDay(re.getReim_id());
			}
			else if(stat == 3 || stat == 4){
				serv.updateStatus(re.getReim_id(), 5);
				serv.setApprover(re.getReim_id(), 21);
				//serv.updateNumDay(re.getReim_id());
				//put into benCo
			}
			else if(stat == 5 || stat == 6 || stat == 7 || stat == 8){
				if(request.getParameter("newAmount") != null){
					int old = re.getCost();
					try{
						int newCost = new Integer(request.getParameter("newAmount")).intValue();
						if(old - newCost < 0){
							serv.updateStatus(re.getReim_id(), 8);
							serv.updateCost(re.getReim_id(), newCost);
							e.setAwarded(e.getAwarded() + newCost);
							e.setPending(e.getPending() - re.getCost());
							serv.updatePending(e.getUserName(), e.getPending());
							serv.updateAwarded(e.getUserName(), e.getAwarded());
							serv.addMessage(re.getReim_id(), mess, 3);
						}
						else{
							serv.updateCost(re.getReim_id(), newCost);
							serv.updateStatus(re.getReim_id(), 9);
							e.setPending(e.getPending() - re.getCost());
							serv.updatePending(e.getUserName(), newCost);
							serv.addMessage(re.getReim_id(), mess, 3);
						}
					}catch(Exception ex){
						HttpServletRequest req = (HttpServletRequest)request;
						HttpServletResponse res =(HttpServletResponse)response;
						RequestDispatcher rd;
						rd = req.getRequestDispatcher("approve.jsp");
						rd.forward(req, res);			
						return;
					}
				}
				else{
					serv.updateStatus(re.getReim_id(), 7);
					
					e.setAwarded(e.getAwarded() + re.getCost());
					e.setPending(e.getPending() - re.getCost());
					serv.updatePending(e.getUserName(), e.getPending());
					serv.updateAwarded(e.getUserName(), e.getAwarded());
				}
				
				//we good
			}
		}
		else if(decision.equals("request info")){
			Reimburse re = ((Reimburse)sess.getAttribute("reim"));
			int stat = serv.getStatus(re.getReim_id());
			
			if(stat == 1 || stat == 2){
				serv.updateStatus(re.getReim_id(), 2);
				serv.addMessage(re.getReim_id(), mess, 1);
			}
			else if(stat == 3 || stat == 4){
				serv.updateStatus(re.getReim_id(), 4);
				serv.addMessage(re.getReim_id(), mess, 2);
			}
			else if(stat == 5 || stat == 8 || stat == 9){
				serv.updateStatus(re.getReim_id(), 6);
				serv.addMessage(re.getReim_id(), mess, 3);
			}
				
		}
		else if(decision.equals("deny")){
			Reimburse re = ((Reimburse)sess.getAttribute("reim"));
			
			int stat = serv.getStatus(re.getReim_id());
			serv.updateStatus(re.getReim_id(), 10);
			Employee e = serv.getEmployee(re.getEmployee_id());
			e.setPending(e.getPending() - re.getCost());
			serv.updatePending(e.getUserName(), e.getPending());
			
			if(stat <= 2){
				serv.addMessage(re.getReim_id(), mess, 1);
			}
			else if(stat > 2 && stat <=4){
				serv.addMessage(re.getReim_id(), mess, 2);
			}
			else{
				serv.addMessage(re.getReim_id(), mess, 3);
			}
			
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
