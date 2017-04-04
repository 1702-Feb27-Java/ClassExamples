package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import objects.Employee;
import objects.Reimburse;
import service.Service;

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
import org.apache.tomcat.util.http.fileupload.FileUtils;

/**
 * Servlet implementation class FormServ
 */
public class FormServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String SAVED_DIR = "C:\\Users\\Zachary\\Desktop\\TRMSFILES";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Reimburse temp = new Reimburse();
		//temp.setCost(new Integer(request.getParameter("eventCost")));		
		
		
		Service serv = new Service();
		HttpSession sess = request.getSession();
		Employee e = ((Employee)sess.getAttribute("employee"));
		
		//update pending reimbursements
		
		
		String str = ((Employee)sess.getAttribute("employee")).getUserName();
		
		
	
		int isUrgent = 0;
		String input = "";
		String costT = "";
		String cType = "";
		 try {
			 //get items
		        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
		         input = items.get(3).getString();
		         costT = items.get(6).getString();
		         cType = items.get(0).getString();
		         temp.setCourseID(new Integer(items.get(0).getString()));
			        //System.out.println("this is courseid" + temp.getCourseID());
	            	temp.setGrade(new Integer(items.get(1).getString()));
	            	temp.setDescription(items.get(2).getString());
	        
	        		temp.setEventLength(items.get(4).getString());
	        		temp.setLocation(items.get(5).getString());
	        		temp.setJustification(items.get(7).getString());
		       
		        //System.out.println(items.get(8).getString());
		      
	        		//upload file
	        		 for (int i = 0; i < items.size(); i++) {
	 		            if (!items.get(i).isFormField() && items.get(i).getSize() > 0) {

	 		            	String name = new File(items.get(i).getName()).getName();
	 				        items.get(i).write( new File(SAVED_DIR + File.separator + name));
	 				       // System.out.print(SAVED_DIR + File.separator + name);
	 				      
	 		                // Process form file field (input type="file").
//	 		                String fieldName = item.getFieldName();
//	 		                String fileName = FileUtils.getName(item.getName());
//	 		                InputStream fileContent = item.getInputStream();
	 		                // ... (do your job here)		            	
	 		            } 
	        		 }
		     
        		
        		
        		temp.setNumDay(0);
        		
        		
            	
            	
		    } 		 	
		 	catch (FileUploadException exx) {
		        throw new ServletException("Cannot parse multipart request.");
		    } catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		
			
	    	
        	//check the date
    		try{
    		    DateTimeFormatter formatter =
    		                      DateTimeFormatter.ofPattern("MM/dd/yyyy");
    		    LocalDate date = LocalDate.parse(input, formatter);
    		   // System.out.printf("%s%n", date);
    		    LocalDate today = LocalDate.now();
//    			LocalDate birthday = LocalDate.of(2017, Month.DECEMBER, 2);
    			long p2 = ChronoUnit.DAYS.between(today, date);
    			if(p2 <= 7){ //error too close to make a request
    				HttpSession ses = request.getSession();
    				ses.setAttribute("formError", "request must be submitted more than a week from start date of event");
    				HttpServletRequest req = (HttpServletRequest)request;
    				HttpServletResponse res =(HttpServletResponse)response;
    				RequestDispatcher rd;
    				rd = req.getRequestDispatcher("form.jsp");
    				rd.forward(req, res);			
    				return;
    			}
    			
    			if(p2 <= 14){ //mark as urgent under 2 weeks
    				isUrgent = 1;
    			}
    		}catch (Exception exc) {
    			System.out.println("ex");
     			HttpSession ses = request.getSession();
     			ses.setAttribute("formError", "date is incorrectly formated");
     			HttpServletRequest req = (HttpServletRequest)request;
     			HttpServletResponse res =(HttpServletResponse)response;
     			RequestDispatcher rd;
     			rd = req.getRequestDispatcher("form.jsp");
     			rd.forward(req, res);			
     			return;
    		 	}
    		
    		
    	int cost = 0;
    		try{ //check the cost
    			cost = new Integer(costT).intValue();
    		}
    		catch (Exception ex){
    			HttpSession ses = request.getSession();
    			ses.setAttribute("formError", "cost field is incorrectly formated");
    			HttpServletRequest req = (HttpServletRequest)request;
    			HttpServletResponse res =(HttpServletResponse)response;
    			RequestDispatcher rd;
    			rd = req.getRequestDispatcher("form.jsp");
    			rd.forward(req, res);			
    			return;
    		}
    		int courseType = new Integer(cType).intValue();
    		//set the cost
    		if(courseType == 1){
    			cost *= .8;
    		}
    		if(courseType == 2){
    			cost *= .6;
    		}
    		if(courseType == 3){
    			cost *= .75;
    		}
    		if(courseType == 4){
    			cost *= 1;
    		}
    		if(courseType == 5){
    			cost *= .9;
    		}
    		if(courseType == 6){
    			cost *= .3;
    		}
    		
    		temp.setCost(cost);
    		e.setPending(cost + e.getPending());
    		serv.updatePending(e.getUserName(), e.getPending());
    			
    			
    		
    		
    		
    		temp.setEvent_date(input);		
    		
			serv.addReimburseRequest(str, temp, e, isUrgent);
			
			
			
			
			
			
			
			
		
		
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res =(HttpServletResponse)response;
		RequestDispatcher rd;
		rd = req.getRequestDispatcher("submitPage.html");
		rd.forward(req, res);			
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	

}
