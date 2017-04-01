package servlet.attachment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import database.model.Attachment;
import database.model.User;
import database.service.Service;

/**
 * Servlet implementation class Attachment
 */
@WebServlet("/Attachment")
@MultipartConfig
public class AttachmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int maxMemSize = 4*1024;
	private int maxFileSize = 100*1024*1024;
	private File locationToSaveData;
	
	
    @Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		locationToSaveData = new File( "storage/");
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public AttachmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	Integer id = Integer.parseInt(request.getParameter("id"));
	
	Attachment attachmentInfo = Service.getInstance().getAttachmentById(id);
	String filename = attachmentInfo.getAttachmentLoc();
	//gets the file
	File attachmentFile = new File(locationToSaveData, filename);
	
	//gets and sets the contentType of file
	String mimeType = getServletContext().getMimeType(filename);
	response.setContentType(mimeType != null? mimeType:"application/octet-stream");
	
	//sets the responses header info
	response.setContentLength((int) attachmentFile.length());
	response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
	
	//outputs file to servlet
	
	
	byte[] bufferData = new byte[1024];
	int read = 0;
	try(ServletOutputStream os = response.getOutputStream();){
		Files.copy(attachmentFile.toPath(), os);
	} catch (IOException e){
		e.printStackTrace();
	}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer reimbursementId = Integer.parseInt(request.getParameter("reimburse-id"));
		String attachmentType = request.getParameter("attachmentType");
		
		if(!((User)request.getSession().getAttribute("loggedInUser")).getUserId().equals(Service.getInstance().getReimbursementById(reimbursementId).getUserId())){
			//does not allow user to transaction to continue this is not the user's reimbursement.
			response.sendError(500);
		}
		Part file = request.getPart("file");
		
		locationToSaveData.mkdirs();
		System.out.println("Saving data to: " + locationToSaveData.getAbsolutePath());
		
		//MSIE fix to only get file name
		String originalFilename = Paths.get(file.getSubmittedFileName()).getFileName().toString();
		InputStream fileContent = file.getInputStream();
		
		//create a unique filename
		SimpleDateFormat df = new SimpleDateFormat("ddMMyy-hhmmss.SSS");
		
		String endingTag = "";
		if(originalFilename.contains(".")){
			//if file has an ending tag, save it
			int n = originalFilename.lastIndexOf('.');
			endingTag = originalFilename.substring(n);
		}
		
		String filename = "file-" + df.format(new Date()) + endingTag;
		
		File fileToSaveTo = new File(locationToSaveData, filename);
		
		System.out.println(fileToSaveTo.getAbsolutePath());
		try(InputStream input = file.getInputStream()){
			Files.copy(input, fileToSaveTo.toPath());
		}
		
		Attachment attachment = new Attachment(
				null,
				attachmentType,
				reimbursementId,
				null,
				filename
				);
		
		Service.getInstance().InsertOrUpdateAttachment(attachment);
		
		
		response.sendRedirect(""
				+ "EditReimbursement?id="+reimbursementId);
				// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
