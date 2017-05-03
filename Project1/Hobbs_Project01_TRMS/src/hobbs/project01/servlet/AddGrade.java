package hobbs.project01.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import hobbs.project01.pojo.Employee;
import hobbs.project01.pojo.Grade;
import hobbs.project01.pojo.Reimbursement;
import hobbs.project01.pojo.ReimbursementAttachment;
import hobbs.project01.service.ReimbursementServiceImpl;

/**
 * Servlet implementation class AddGrade
 */
public class AddGrade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGrade() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int appId = Integer.parseInt(request.getParameter("id"));

		@SuppressWarnings("deprecation")
		AmazonS3 s3Client = new AmazonS3Client(new ProfileCredentialsProvider()); //accesses default profile defined in C:/users/YOUR_USER_NAME/.aws/credentials.
		
		String bucketName = "revature-project01";
		Random random = new Random(); //to generate a random unique number for storing files in bucket (otherwise same name would collide).
		
		// GENERATE GRADE; POPULATE VALUES
		Grade grade = new Grade();
		ArrayList<ReimbursementAttachment> attachments = null;
		
		Reimbursement reimbursement = (Reimbursement)request.getSession().getAttribute("reimbursement");
		
		// parse form fields and uploaded file.
		try {
	        List<FileItem> fields = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	        for (FileItem field : fields) {
	            if (field.isFormField()) {
	                //// all non-<input type="file"> fields will be processed here
	            	switch (field.getFieldName()) {
	            	case "grade":
	            		grade.setGrade(field.getString());
	            		break;
	            	}
	            } else {
	                //// the <input type="file"> fields will be processed here.
	            	if (!field.getName().isEmpty()) { //if a file was actually attached
	            		Employee employee = (Employee)request.getSession().getAttribute("user");
		            	String fileName = employee.getId() + "-" + random.nextInt(Integer.MAX_VALUE) + "-" + FilenameUtils.getName(field.getName()); //getting filename of file uploaded
		                
		            	//CREATE ATTACHMENT; add to reimbursement
		            	if (attachments == null) {
		            		attachments = new ArrayList<>();
		            	}
		            	ReimbursementAttachment attachment = new ReimbursementAttachment();
		            	attachment.setUrl(fileName);
		            	switch (field.getFieldName()) {
		            	case "grade-attachments":
		            		attachment.setAttachmentTypeId(ReimbursementAttachment.AttachmentType.grade.getId());
		            		break;
		            	}
		            	attachments.add(attachment);
		            	System.out.println(attachments);
		                InputStream file = field.getInputStream(); //get file uploaded by user.
		                
		                s3Client.putObject(new PutObjectRequest(bucketName, fileName, file, new ObjectMetadata())); //upload file to s3 bucket with the given name.
		                
		                file.close();
	            	}
	            }
	        }
	    } catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		// COMMIT GRADE AND ATTACHMENTS
		ReimbursementServiceImpl.getReimbursementService().addGrade(reimbursement, grade, attachments);
		
		response.sendRedirect("app?id=" + appId);
		return;
	}

}
