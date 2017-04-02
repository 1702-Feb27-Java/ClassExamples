package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Properties;

//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
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
		} else if (request.getParameter("myReqs") != null)
		{
			System.out.println("Button 2");
			LinkedList<Reimbursement> reims = new LinkedList<>();

			reims = service.getReimsByEmpId(((Employee) request.getSession().getAttribute("CurrEmp")).getEmpId());

			ObjectMapper mapper = new ObjectMapper();

			PrintWriter out = response.getWriter();
			out.write(mapper.writeValueAsString(reims));
		} else if (request.getParameter("otherReqs") != null)
		{
			// Write code to get the requests this person needs to approve
			System.out.println("Button 3");

			LinkedList<Reimbursement> reims = new LinkedList<>();

			reims = service.getReimsByApprover(((Employee) request.getSession().getAttribute("CurrEmp")));

			ObjectMapper mapper = new ObjectMapper();

			PrintWriter out = response.getWriter();
			out.write(mapper.writeValueAsString(reims));
		} else if (request.getParameter("modalFill") != null)
		{

			ObjectMapper mapper = new ObjectMapper();

			int id = mapper.readValue(request.getParameter("modalFill"), Integer.class);

			System.out.println("Modal Reim Id: " + id);

			Reimbursement reim = service.getReimsByReimId(id);

			PrintWriter out = response.getWriter();

			System.out.println(reim);

			out.write(mapper.writeValueAsString(reim));
		} else if (request.getParameter("reject") != null)
		{

			ObjectMapper mapper = new ObjectMapper();

			String note = request.getParameter("reject");

			System.out.println("Modal Note: " + note);

			int id = mapper.readValue(request.getParameter("id"), Integer.class);

			System.out.println("Modal Reim Id: " + id);

			Reimbursement reim = service.getReimsByReimId(id);

			reim.setRejectionNote(note);
			reim.setApprovalId(11);

			service.saveReim(reim);

			/////////// PUT EMAIL IN HERE TOO

			// Recipient's email ID needs to be mentioned.
			String to = service.getEmpById(service.getReimsByReimId(id).getEmpId()).getEmail();

			// Sender's email ID needs to be mentioned
			String from = "angelicpyro143";
			String password = "Bellarosegoogle143!";

			// Assuming you are sending email from localhost
			String host = "smtp.gmail.com";

			// Get system properties
			Properties props = System.getProperties();

			String emailPort = "587";
			props = System.getProperties();
			props.put("mail.smtp.port", emailPort);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");

			// Setup mail server
			props.put("mail.smtp.host", host);

//			// Get the default Session object
//			Session session = Session.getDefaultInstance(props);
//
//			// Create a default MimeMessage object.
//			MimeMessage mimeMessage = new MimeMessage(session);

			// Set From: header field of the header.
			try
			{
//				mimeMessage.setFrom(new InternetAddress(from));
//
//				mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

				// Set Subject: header field
				String subject = "";
				int role = ((Employee) request.getSession().getAttribute("CurrEmp")).getRoleId();
				int dept = ((Employee) request.getSession().getAttribute("CurrEmp")).getDeptId();

				if (dept == 1)
				{
					if (role == 1)
					{
						subject = "BenCo Reviewer Rejected Your Reimbursement";
					} else if (role == 2)
					{
						subject = "BenCo Supervisor Rejected Your Reimbursement";
					} else if (role == 3)
					{
						subject = "BenCo Dept Head Rejected Your Reimbursement";
					}
				} else if (dept == 2)
				{
					if (role == 2)
					{
						subject = "Your Supervisor Rejected Your Reimbursement";
					} else if (role == 3)
					{
						subject = "Your Supervisor Rejected Your Reimbursement";
					}
				}

//				mimeMessage.setSubject(subject);
//
//				// Now set the actual message
//				mimeMessage.setText("Why your reimbursement was denied: \n" + note);
//
//				// Send message
//				Transport transport = session.getTransport("smtp");
//				transport.connect(host, from, password);
//				transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
//				transport.close();

			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (request.getParameter("request") != null)
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

			//////// FINISH EMAIL

			// Recipient's email ID needs to be mentioned.
			String to = service.getEmpById(service.getReimsByReimId(id).getEmpId()).getEmail();

			// Sender's email ID needs to be mentioned
			String from = "angelicpyro143";
			String password = "Bellarosegoogle143!";

			// Assuming you are sending email from localhost
			String host = "smtp.gmail.com";

			// Get system properties
			Properties props = System.getProperties();

			String emailPort = "587";
			props = System.getProperties();
			props.put("mail.smtp.port", emailPort);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");

			// Setup mail server
			props.put("mail.smtp.host", host);

			// Get the default Session object
//			Session session = Session.getDefaultInstance(props);
//
//			// Create a default MimeMessage object.
//			MimeMessage mimeMessage = new MimeMessage(session);

			// Set From: header field of the header.
			try
			{
//				mimeMessage.setFrom(new InternetAddress(from));
//
//				mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

				// Set Subject: header field
				String subject = "";
				int role = ((Employee) request.getSession().getAttribute("CurrEmp")).getRoleId();
				int dept = ((Employee) request.getSession().getAttribute("CurrEmp")).getDeptId();

				if (dept == 1)
				{
					if (role == 1)
					{
						subject = "BenCo Reviewer Requests More Information";
					} else if (role == 2)
					{
						subject = "BenCo Supervisor Requests More Information";
					} else if (role == 3)
					{
						subject = "BenCo Dept Head Requests More Information";
					}
				} else if (dept == 2)
				{
					if (role == 2)
					{
						subject = "Your Supervisor Requests More Information";
					} else if (role == 3)
					{
						subject = "Your Supervisor Requests More Information";
					}
				}

//				mimeMessage.setSubject(subject);
//
//				// Now set the actual message
//				mimeMessage.setText(note);
//
//				// Send message
//				Transport transport = session.getTransport("smtp");
//				transport.connect(host, from, password);
//				transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
//				transport.close();

			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

		} else if (request.getParameter("approve") != null)
		{
			
			ObjectMapper mapper = new ObjectMapper();

			int id = mapper.readValue(request.getParameter("approve"), Integer.class);
			
			int newLevel = 11;
			int role = ((Employee) request.getSession().getAttribute("CurrEmp")).getRoleId();
			int dept = ((Employee) request.getSession().getAttribute("CurrEmp")).getDeptId();
			
			if (dept == 1)
			{
				if (role == 1)
				{
					newLevel = 6;
				} else if (role == 2)
				{
					newLevel = 2;
				} else if (role == 3)
				{
					newLevel = 4;
				}
			} else if (dept == 2)
			{
				if (role == 2)
				{
					newLevel = 2;
				} else if (role == 3)
				{
					newLevel = 4;
				}
			}
			
			Reimbursement reimb = service.getReimsByReimId(id);

			reimb.setApprovalId(newLevel);
			
			service.saveReim(reimb);
			
			//////// EMAIL
			// Recipient's email ID needs to be mentioned.
			String to = service.getEmpById(service.getReimsByReimId(id).getEmpId()).getEmail();

			// Sender's email ID needs to be mentioned
			String from = "angelicpyro143";
			String password = "Bellarosegoogle143!";

			// Assuming you are sending email from localhost
			String host = "smtp.gmail.com";

			// Get system properties
			Properties props = System.getProperties();

			String emailPort = "587";
			props = System.getProperties();
			props.put("mail.smtp.port", emailPort);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");

			// Setup mail server
			props.put("mail.smtp.host", host);

//			// Get the default Session object
//			Session session = Session.getDefaultInstance(props);
//
//			// Create a default MimeMessage object.
//			MimeMessage mimeMessage = new MimeMessage(session);

			// Set From: header field of the header.
			try
			{
//				mimeMessage.setFrom(new InternetAddress(from));
//
//				mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

				// Set Subject: header field
				String subject = "";

				if (dept == 1)
				{
					if (role == 1)
					{
						subject = "BenCo Reviewer Approved Your Reimbursement";
					} else if (role == 2)
					{
						subject = "BenCo Supervisor Approved Your Reimbursement";
					} else if (role == 3)
					{
						subject = "BenCo Dept Head Approved Your Reimbursement";
					}
				} else if (dept == 2)
				{
					if (role == 2)
					{
						subject = "Your Supervisor Approved Your Reimbursement";
					} else if (role == 3)
					{
						subject = "Your Supervisor Approved Your Reimbursement";
					}
				}

//				mimeMessage.setSubject(subject);
//
//				// Now set the actual message
//				mimeMessage.setText("Your request has been approved.");
//
//				// Send message
//				Transport transport = session.getTransport("smtp");
//				transport.connect(host, from, password);
//				transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
//				transport.close();

			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

		}

	}

}
