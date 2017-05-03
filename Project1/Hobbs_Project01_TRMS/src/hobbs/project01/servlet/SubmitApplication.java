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
import hobbs.project01.pojo.Reimbursement;
import hobbs.project01.pojo.ReimbursementAttachment;
import hobbs.project01.service.ReimbursementServiceImpl;

/**
 * Servlet implementation class SubmitApplication
 */
public class SubmitApplication extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitApplication() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("deprecation")
		AmazonS3 s3Client = new AmazonS3Client(new ProfileCredentialsProvider()); //accesses default profile defined in C:/users/YOUR_USER_NAME/.aws/credentials.
		
		String bucketName = "revature-project01";
		Random random = new Random(); //to generate a random unique number for storing files in bucket (otherwise same name would collide).
		
		// GENERATE REIMBURSEMENT; POPULATE VALUES
		Reimbursement reimbursement = new Reimbursement();
		String startdate = "", starttime = "", enddate = "", endtime = "";
		ArrayList<ReimbursementAttachment> attachments = null;
		
		// parse form fields and uploaded file.
		try {
	        List<FileItem> fields = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	        for (FileItem field : fields) {
	            if (field.isFormField()) {
	                //// all non-<input type="file"> fields will be processed here
	            	switch (field.getFieldName()) {
	            	case "eventType":
	            		reimbursement.setEventTypeId(Integer.parseInt(field.getString()));
	            		break;
	            	case "gradeFormat":
	            		reimbursement.setGradeFormatId(Integer.parseInt(field.getString())); //TODO: handle custom grade format possibility.
	            		break;
	            	case "location":
	            		reimbursement.setLocation(field.getString());
	            		break;
	            	case "cost":
	            		reimbursement.setCost(Double.parseDouble(field.getString()));
	            		break;
	            	case "startDate":
	            		startdate = field.getString();
	            		break;
	            	case "startTime":
	            		starttime = field.getString();
	            		break;
	            	case "endDate":
	            		enddate = field.getString();
	            		break;
	            	case "endTime":
	            		endtime = field.getString();
	            		break;
	            	case "description":
	            		reimbursement.setDescription(field.getString());
	            		break;
	            	case "justification":
	            		reimbursement.setJustification(field.getString());
	            		break;
	            	case "worktimeToBeMissed":
	            		reimbursement.setWorktimeToBeMissed(field.getString());
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
		            	case "event-attachments":
		            		attachment.setAttachmentTypeId(ReimbursementAttachment.AttachmentType.event.getId());
		            		break;
		            	case "supervisor-attachment":
		            		attachment.setAttachmentTypeId(ReimbursementAttachment.AttachmentType.approvalSupervisor.getId());
		            		break;
		            	case "head-attachment":
		            		attachment.setAttachmentTypeId(ReimbursementAttachment.AttachmentType.approvalHead.getId());
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
		
		//assemble attachments
		if (attachments != null) {
			reimbursement.setAttachments(attachments);
		}
		
		// combine start/end dates and times
		String startDatetime = startdate + " " + starttime;
		String endDatetime = enddate + " " + endtime;
		
		reimbursement.setStartDatetime(Timestamp.valueOf(startDatetime));
		reimbursement.setEndDatetime(Timestamp.valueOf(endDatetime));
		
		reimbursement.setEmployeeId(((Employee)request.getSession().getAttribute("user")).getId());
		
		// COMMIT REIMBURSEMENT
		ReimbursementServiceImpl.getReimbursementService().createReimbursement(reimbursement, false);
		
		response.sendRedirect("account");
		return;
	}

}
