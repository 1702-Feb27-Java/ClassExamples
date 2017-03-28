package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.revature.service.EmployeeService;

/**
 * Servlet implementation class SubmitReimbursement
 */
public class SubmitReimbursement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitReimbursement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeService serveEmp = new EmployeeService();
/*		int emp_id = 0;
		String event = ""; 
		String eventDate = ""; 
		String time = "";
		String location = ""; 
		String location2 = ""; 
		String description = "";
		String cost2 = ""; 
		String gradingId = ""; 
		String gradingId2 = ""; 
		String typeOfEvent = ""; 
		String passingGrade = "";
		
		
		 try {
		        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
		        for (FileItem item : items) {
		            if (item.isFormField()) {
		                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
		                String fieldName = item.getFieldName();
		                String fieldValue = item.getString();
		                switch(fieldName){
			                case "uId":
			                	emp_id = Integer.parseInt(fieldValue);
			                	break;
			                case "event":
			                	event = fieldValue;
			                	break;
			                case "eventDate":
			                	eventDate = fieldValue;
			                	break;
			                case "time":
			                	time = fieldValue;
			                	break;
			                case "location":
			                	location = fieldValue;
			                	break;
			                case "location2":
			                	location2 = fieldValue;
			                	break;
			                case "description":
			                	description = fieldValue;
			                	break;
			                case "cost2":
			                	cost2 = fieldValue;
			                	break;
			                case "gradingId":
			                	gradingId = fieldValue;
			                	break;
			                case "gradingId2":
			                	gradingId2 = fieldValue;
			                	break;
			                case "typeOfEvent":
			                	typeOfEvent = fieldValue;
			                	break;
			                case "passingGrade":
			                	passingGrade = fieldValue;
			                	break;                 	
		                }
		            } else {
		                // Process form file field (input type="file").
		                String fieldName = item.getFieldName();
		                String fileName = FilenameUtils.getName(item.getName());
		                InputStream fileContent = item.getInputStream();
		                System.out.println(fileContent);
		                // ... (do your job here)
		            }
		        }
		    } catch (FileUploadException e) {
		        throw new ServletException("Cannot parse multipart request.", e);
		    }*/
		
		
		
		
		HttpSession ses = request.getSession();
		int emp_id = (int) ses.getAttribute("uId");
		String event = request.getParameter("event");
		String eventDate = request.getParameter("eventDate");
		String time = request.getParameter("time");
		String location = request.getParameter("location");
		String location2 = request.getParameter("location2");
		String description = request.getParameter("description");
		String cost2 = request.getParameter("cost");
		String gradingId = request.getParameter("gradingId");
		String gradingId2 = request.getParameter("gradingId2");
		String typeOfEvent = request.getParameter("typeOfEvent");
		String passingGrade = request.getParameter("passingGrade");
		
		System.out.println(event);
		System.out.println(description);
		int cost = Integer.parseInt(cost2);
		
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		Date date = new Date();
		Date todaysDate = new Date();
		Date cutoffDate2;
		
		//add 7 days to current day to get cutoff day
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 7);  // number of days to add
		cutoffDate2 = c.getTime();
		

		try {
			date = format.parse(eventDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int daysApart = (int)((date.getTime() - todaysDate.getTime()) / (1000*60*60*24l));
		
		int urgent = 2;
		if (daysApart < 7)
			urgent = 1;
		
		//convert java dates to sql dates
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		java.sql.Date sqlDateToday = new java.sql.Date(todaysDate.getTime());
		java.sql.Date cutoffDate = new java.sql.Date(cutoffDate2.getTime());
		
		int roleId = serveEmp.getRoleId(emp_id);
		int typeOfEventId = serveEmp.getTypeOfEventid(typeOfEvent);
		
		int locationId = 0;
		int gradeId = 0;
		if(location2.length() != 0){
			locationId = serveEmp.addLocation(location2);
		}
		else{
			locationId = serveEmp.getLocationId(location);
		}
		
		if(gradingId2.length() != 0){
			gradeId = serveEmp.addGrading(gradingId2, passingGrade);
			
		}
		else{
			gradeId = serveEmp.getGradingId(gradingId);
		}
		boolean submitResult = serveEmp.applyForReimbursement(emp_id, event, sqlDate, time, locationId, sqlDateToday, description, cost,
				gradeId, typeOfEventId, urgent, roleId, cutoffDate);
		request.setAttribute("submitResult", submitResult);
		
/*		String nextJSP = "/createReimbursement.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
