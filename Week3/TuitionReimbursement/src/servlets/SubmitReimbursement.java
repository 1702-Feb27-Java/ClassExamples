package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
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

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.revature.service.EmployeeService;


/**
 * Servlet implementation class SubmitReimbursement
 */
public class SubmitReimbursement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String bucketName = "tuitionreimbursement"; //bucket's name in s3. hardcoded here.
	private String keyName; //file's name in s3. should be unique name within bucket. to be written by user.
	private InputStream file; //file uploaded.
       
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
		ArrayList<String> files = new ArrayList<String>();
		
		HttpSession ses = request.getSession();
		int emp_id = (int) ses.getAttribute("uId");
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
		
		@SuppressWarnings("deprecation")
		AmazonS3 s3Client = new AmazonS3Client(new ProfileCredentialsProvider()); //accesses default profile defined in C:/users/YOUR_USER_NAME/.aws/credentials.
		
		// parse form fields and uploaded file.
		try {
	        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	        for (FileItem item : items) {
	            if (item.isFormField()) {
	            	String input = item.getFieldName();
	            	switch(input){
	            	case "event" : 
	            		event = item.getString();
	            		break;
	            	case "eventDate" :
	            		eventDate = item.getString();
	            		break;
	            	case "time" :
	            		time = item.getString();
	            		break;
	            	case "location" :
	            		location = item.getString();
	            		break;
	            	case "location2" :
	            		location2 = item.getString();
	            		break;
	            	case "description" :
	            		description = item.getString();
	            		break;
	            	case "cost" : 
	            		cost2 = item.getString();
	            		break;
	            	case "gradingId" :
	            		gradingId = item.getString();
	            		break;
	            	case "gradingId2" :
	            		gradingId2 = item.getString();
	            		break;
	            	case "typeOfEvent" :
	            		typeOfEvent = item.getString();
	            		break;
	            	case "passingGrade" :
	            		passingGrade = item.getString();
	            		break;
	            	}
	            	
	            	
	                if (item.getFieldName().equals("filename")) {
	                	keyName = item.getString(); //get filename input by user.
	                	
	                }
	            } else {
	                // the <input type="file"> field will be processed here.
	                //String fieldName = item.getFieldName();
	                //String fileName = FilenameUtils.getName(item.getName()); //getting filename of file uploaded; but not using..
	                //fileName += FilenameUtils.getExtension(item.getName()); //add extension onto name
	            	keyName += "." + FilenameUtils.getExtension(item.getName());
	            	files.add(keyName);
	                file = item.getInputStream(); //get file uploaded by user.
	            }
	        }
	    } catch (FileUploadException e) {
			e.printStackTrace();
		}
		System.out.println(file);
		System.out.println(keyName); //test printing the file name written by user.
		
		s3Client.putObject(new PutObjectRequest(bucketName, keyName, file, new ObjectMetadata())); //upload file to s3 bucket with the given name.
		
		file.close();		
		
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
		int rId = serveEmp.applyForReimbursement(emp_id, event, sqlDate, time, locationId, sqlDateToday, description, cost,
				gradeId, typeOfEventId, urgent, roleId, cutoffDate);
		
		serveEmp.submitEdit(rId, files);
		//request.setAttribute("submitResult", submitResult);
		
		String nextJSP = "/loggedIn.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
