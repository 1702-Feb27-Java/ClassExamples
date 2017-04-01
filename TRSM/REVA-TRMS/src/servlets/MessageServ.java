package servlets;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class messageServ
 */
public class MessageServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int x = new Integer(request.getParameter("action")).intValue();
		//System.out.println(x);
		Service serv = new Service();
		Reimburse rm = serv.getReimburse(x);
		Employee em = serv.getEmployee(rm.getEmployee_id());
		HttpSession sess = request.getSession();
		sess.setAttribute("re", rm);
		sess.setAttribute("reEm", em);
		//get message
		
		ArrayList<String> mess = serv.getMessage(rm.getReim_id());
		//System.out.println(mess);
		if(mess == null){
			sess.setAttribute("superMessage", null);
			sess.setAttribute("departHeadMessage", null);
			sess.setAttribute("BenCoMessage", null);
		}
		else if(mess.size() == 1){
			sess.setAttribute("superMessage", mess.get(0));
			sess.setAttribute("departHeadMessage", null);
			sess.setAttribute("BenCoMessage", null);
		}
		else if(mess.size() == 2){
			sess.setAttribute("superMessage", mess.get(0));
			sess.setAttribute("departHeadMessage", mess.get(1));
			sess.setAttribute("BenCoMessage", null);
		}
		else if(mess.size() == 3){
			sess.setAttribute("superMessage", mess.get(0));
			sess.setAttribute("departHeadMessage", mess.get(1));
			sess.setAttribute("BenCoMessage", mess.get(2));
		}
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res =(HttpServletResponse)response;
		RequestDispatcher rd;
		rd = req.getRequestDispatcher("employeView.jsp");
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
