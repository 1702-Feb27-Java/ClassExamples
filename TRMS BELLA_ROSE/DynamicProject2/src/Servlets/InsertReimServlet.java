package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import Classes.ChatMessage;
import Classes.Employee;
import Classes.Reimbursement;

/**
 * Servlet implementation class InsertReimServet
 */
public class InsertReimServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	Service service = new Service();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertReimServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub

		if (request.getParameter("reim") != null)
		{
			System.out.println("Button 1");
			Reimbursement reim = new Reimbursement();

			System.out.println(request.getParameter("reim"));

			ObjectMapper mapper = new ObjectMapper();

			reim = mapper.readValue(request.getParameter("reim"), Reimbursement.class);

			reim.setEmpId(((Employee) request.getSession().getAttribute("CurrEmp")).getEmpId());

			if (service.insertReim(reim, ((Employee) request.getSession().getAttribute("CurrEmp"))))
			{
				PrintWriter out = response.getWriter();
				out.write("Reimbursement Submitted");
			} else
			{
				PrintWriter out = response.getWriter();
				out.write("Submission Failed");
			}
		}
		else if (request.getParameter("myReqs") != null)
		{
			System.out.println("Button 2");
			LinkedList<Reimbursement> reims = new LinkedList<>();
			
			reims = service.getReimsByEmpId(((Employee) request.getSession().getAttribute("CurrEmp")).getEmpId());
			
			ObjectMapper mapper = new ObjectMapper();
			
			PrintWriter out = response.getWriter();
			out.write(mapper.writeValueAsString(reims));
		}
		else if (request.getParameter("otherReqs") != null)
		{
			//Write code to get the requests this person needs to approve
			System.out.println("Button 3");
			
			LinkedList<Reimbursement> reims = new LinkedList<>();
			
			reims = service.getReimsByApprover(((Employee) request.getSession().getAttribute("CurrEmp")));
			
			ObjectMapper mapper = new ObjectMapper();
			
			PrintWriter out = response.getWriter();
			out.write(mapper.writeValueAsString(reims));
		}
		else if (request.getParameter("modalFill") != null)
		{
			
			ObjectMapper mapper = new ObjectMapper();

			int id = mapper.readValue(request.getParameter("modalFill"), Integer.class);

			System.out.println("Modal Reim Id: " + id);
			
			Reimbursement reim  = service.getReimsByReimId(id);
			
			PrintWriter out = response.getWriter();
			
			System.out.println(reim);
			
			out.write(mapper.writeValueAsString(reim));
		}
		else if (request.getParameter("reject") != null)
		{
			
			ObjectMapper mapper = new ObjectMapper();

			String note = request.getParameter("reject");

			System.out.println("Modal Note: " + note);
			
			int id = mapper.readValue(request.getParameter("id"), Integer.class);

			System.out.println("Modal Reim Id: " + id);
			
			Reimbursement reim  = service.getReimsByReimId(id);
			
			reim.setRejectionNote(note);
			reim.setApprovalId(11);
			
			service.saveReim(reim);
			
			///////////PUT EMAIL IN HERE TOO
			
		}
		else if(request.getParameter("request") != null)
		{
			ObjectMapper mapper = new ObjectMapper();

			String note = request.getParameter("request");

			System.out.println("Note: " + note);
			
			int id = mapper.readValue(request.getParameter("id"), Integer.class);

			System.out.println("Email Reim Id: " + id);
			
			ChatMessage message = new ChatMessage();
			
			message.setNote(note);
			message.setSent_to_id(service.getReimsByReimId(id).getEmpId());
			message.setAuthor_id(((Employee) request.getSession().getAttribute("CurrEmp")).getEmpId());
			message.setReim_id(id);
			
			service.insertMessage(message);
			
			////////FINISH EMAIL
		}

	}

}
