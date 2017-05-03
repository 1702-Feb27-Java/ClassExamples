package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

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
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

/**
 * Simple demo of accessing AWS S3 to upload a file and get the names of files in it.
 * 
 * Servlet implementation class S3Demo
 */
public class S3Demo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String bucketName = "revature-week05"; //bucket's name in s3. hardcoded here.
	private String keyName; //file's name in s3. should be unique name within bucket. to be written by user.
	private InputStream file; //file uploaded.
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S3Demo() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Displays objects stored in S3 bucket.
     * 
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	@SuppressWarnings("deprecation")
		AmazonS3 s3Client = new AmazonS3Client(new ProfileCredentialsProvider()); //accesses default profile defined in C:/users/YOUR_USER_NAME/.aws/credentials.
    	
    	ObjectListing objects = s3Client.listObjects(bucketName); //get objects in the s3 bucket.
    	List<S3ObjectSummary> objectSummaries = objects.getObjectSummaries(); //get metadata of objects.
    	PrintWriter out = response.getWriter();
    	out.println("<!DOCTYPE html><html><head><title>List S3 Objects</title><style>body { background-color: white; }</style></head><body>\n");
		out.println("<h1>Objects in my S3 bucket</h1>\n");
		out.println("<ul>\n");
    	for (S3ObjectSummary o : objectSummaries) {
    		out.println("<li>" + o.getKey() + "</li>\n"); //get object's name.jxx
    	}
    	out.println("</ul>\n");
		out.println("</body></html>");
    }

	/**
	 * Uploads files to S3 bucket.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("deprecation")
		AmazonS3 s3Client = new AmazonS3Client(new ProfileCredentialsProvider()); //accesses default profile defined in C:/users/YOUR_USER_NAME/.aws/credentials.
		
		String extension = null;
		// parse form fields and uploaded file.
		try {
	        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	        for (FileItem item : items) {
	            if (item.isFormField()) {
	                // all non-<input type="file"> fields will be processed here
	                if (item.getFieldName().equals("filename")) {
	                	keyName = item.getString(); //get filename input by user.
	                }
	            } else {
	                // the <input type="file"> field will be processed here.
	                //String fieldName = item.getFieldName();
	                //String fileName = FilenameUtils.getName(item.getName()); //getting filename of file uploaded; but not using..
	                //fileName += FilenameUtils.getExtension(item.getName()); //add extension onto name
	            	extension = "." + FilenameUtils.getExtension(item.getName());
	                file = item.getInputStream(); //get file uploaded by user.
	            }
	        }
	    } catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		System.out.println(keyName); //test printing the file name written by user.
		keyName += extension;
		s3Client.putObject(new PutObjectRequest(bucketName, keyName, file, new ObjectMetadata())); //upload file to s3 bucket with the given name.
		
		file.close();
		
		response.sendRedirect("index.html");
	}

}
