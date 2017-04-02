package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
import com.revature.pojo.Reimbursement;
import com.revature.service.EmployeeService;

/**
 * Servlet implementation class SubmitGrade
 */
public class SubmitGrade extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String bucketName = "tuitionreimbursement"; //bucket's name in s3. hardcoded here.
	private String keyName; //file's name in s3. should be unique name within bucket. to be written by user.
	private InputStream file; //file uploaded.
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitGrade() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeService serveEmp = new EmployeeService();
		HttpSession ses = request.getSession();
		
		int reimbId = (int)ses.getAttribute("reimbId");
		
		
		


		
		
		
		
		
		
		
		String finalGrade = "";
		
		@SuppressWarnings("deprecation")
		AmazonS3 s3Client = new AmazonS3Client(new ProfileCredentialsProvider());
		ArrayList<String> files = new ArrayList<String>();

		// parse form fields and uploaded file.
		try {
	        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	        for (FileItem item : items) {
	            if (item.isFormField()) {
  	            	String input = item.getFieldName();
	        		
	            	switch(input){
	            	case "finalGrade" : 
	            		finalGrade = item.getString();
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
		
		s3Client.putObject(new PutObjectRequest(bucketName, keyName, file, new ObjectMetadata())); //upload file to s3 bucket with the given name.
		
		file.close();	
		
		if(!files.isEmpty())
			serveEmp.submitEdit(reimbId, files);
		
		
		
		
		
		
		
		serveEmp.submitGrade(reimbId, finalGrade);
		
		int empId = (int)ses.getAttribute("uId");
		
		ArrayList<Reimbursement> approvedReimbursements = serveEmp.getApprovedReimbursements(empId);
		request.setAttribute("approvedReimbursements", approvedReimbursements);
		
		
		String nextJSP = "/approvedReimbursements.jsp";
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