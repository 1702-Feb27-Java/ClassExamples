package com.revature.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import com.revature.dao.AttachDAOImp;
import com.revature.pojo.UserClass;

/**
 * Servlet implementation class GradeFileUpload
 */
public class GradeFileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String bucketName = "trmsproject";
	private String keyName; // file name parsed with extension, needs to be unique
	private InputStream file; //file uploaded.
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradeFileUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String getAppID = session.getAttribute("appID").toString();
		List<String> fileList = new ArrayList<String>();
		
		@SuppressWarnings("deprecation")
		AmazonS3 s3Client = new AmazonS3Client(new ProfileCredentialsProvider());
		try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);			
			UserClass thisUser = new UserClass();
			thisUser = (UserClass)session.getAttribute("userInfo");
			String username = thisUser.getUsername();
						
			for (FileItem item : items) {
				if (item.isFormField()) {
					// all non-<input type="file"> fields will be processed here
					if (item.getFieldName().equals("appID")) {
						keyName = item.getString(); 
					}
				} else {
					// the <input type="file"> field will be processed here.
					InputStream file = null; // initialize a new file
					String fileName = FilenameUtils.getName(item.getName()); 
					//fileName += "." + FilenameUtils.getExtension(item.getName()); 
					keyName = username + "/" + getAppID + "/" + fileName; // create sub directory for the user
					fileList.add(keyName);  // save it to the list, persist to DB later
					file = item.getInputStream(); // get file uploaded by user.
					s3Client.putObject(new PutObjectRequest(bucketName, keyName, file, new ObjectMetadata()));
					System.out.println("Success");
					file.close();
				}
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	
		int appID = Integer.parseInt(getAppID);
		
		AttachDAOImp attach = new AttachDAOImp();
		
		for (Iterator<String> i = fileList.iterator(); i.hasNext(); ){
			attach.addGrAttachments(appID, i.next());
		}
		
		session.removeAttribute("appID");
		
		String destination = "appstatus.jsp";

		response.sendRedirect(destination);
	}

}
