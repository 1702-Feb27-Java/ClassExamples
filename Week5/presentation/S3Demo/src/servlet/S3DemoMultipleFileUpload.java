package servlet;

import java.io.IOException;
import java.io.InputStream;
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

/**
 * Servlet implementation class S3DemoMultipleFileUpload
 */
public class S3DemoMultipleFileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S3DemoMultipleFileUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
AmazonS3 s3Client = new AmazonS3Client(new ProfileCredentialsProvider()); //accesses default profile defined in C:/users/YOUR_USER_NAME/.aws/credentials.
		
		String bucketName = "revature-week05";
		Random random = new Random();
		// parse form fields and uploaded file.
		try {
	        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	        for (FileItem item : items) {
	            if (item.isFormField()) {
	                //// all non-<input type="file"> fields will be processed here
	            	// no-op
	            } else {
	                //// the <input type="file"> field will be processed here.
	                
	            	String fileName = random.nextInt() + "-" + FilenameUtils.getName(item.getName()); //getting filename of file uploaded
	                //fileName += "." + FilenameUtils.getExtension(item.getName()); //appending extension onto name
	                
	                System.out.println(fileName); //test printing the file name written by user.
	                
	                InputStream file = item.getInputStream(); //get file uploaded by user.
	                
	                s3Client.putObject(new PutObjectRequest(bucketName, fileName, file, new ObjectMetadata())); //upload file to s3 bucket with the given name.
	                
	                file.close();
	            }
	        }
	    } catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("index.html");
		return;
	}

}
