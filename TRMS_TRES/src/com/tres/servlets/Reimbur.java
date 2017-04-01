package com.tres.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
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

import com.tres.objs.Reimbursement;
import com.tres.service.ServiceImp;

/**
 * Servlet implementation class Reimbur
 */
public class Reimbur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static ServiceImp serve = new ServiceImp();
	
	private final String UPLOAD_DIRECTORY = "C:\\Users\\tobon\\Desktop\\Trash";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reimbur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// Parse the request
		try
		{
			List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for(int i = 0; i < multiparts.size(); i++){
				FileItem item = multiparts.get(i);
				if(!item.isFormField() && item.getSize() > 0){
			        String name = new File(item.getName()).getName();
			        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
			        //request.setAttribute("photoname", name);
			        System.out.println(i+":"+UPLOAD_DIRECTORY + "\\" + name);
			    }else{
			        // here get value of other parameter which is not file type
			        System.out.println(i+":"+item.getFieldName()+" = "+item.getString());
			    }
			    
			}
			//CREATE REIMBURSEMETN OBJECT
			HttpSession sess = request.getSession(false);
			int x = (int) sess.getAttribute("uid");
			int y = (int) sess.getAttribute("roleid");
			 Reimbursement r = new Reimbursement
			    		(
							//EMID
			    			x
							//Location
			    		, multiparts.get(9).getString()
							
							//TIME
			    		, multiparts.get(5).getString()
			
							//COURSE DES
			    		, multiparts.get(10).getString()
			
			    			//COST
			    		,Double.parseDouble(multiparts.get(11).getString())
			
			    			//GRADEID
			    		, Integer.parseInt(multiparts.get(12).getString())
			
			    			//courseid
			    		, Integer.parseInt(multiparts.get(13).getString())
			
			    			//workjus
			    		, multiparts.get(14).getString()
			
			    			//tmmiss
			    		, multiparts.get(3).getString()
		
			    			//crsStart
			    		, multiparts.get(7).getString()
		
			    			//crsEnd
			    		, multiparts.get(8).getString()
			
			    			//approd
			    		,y
	
			    			//formdt
			    		, multiparts.get(6).getString()
			
			    			//gradeval
			    		,null
			    			//Currently on
			    		, (int)sess.getAttribute("repid")
			    		
			    			//sid
			    		, 1
			    		);
			int reim_id = serve.insertReim(r);
			if(!multiparts.get(1).isFormField() && multiparts.get(1).getSize() > 0)
			{
				FileItem item = multiparts.get(1);
			    String name = new File(item.getName()).getName();
			    serve.addAtch(x, reim_id, UPLOAD_DIRECTORY+"\\"+ name);
			}
		} catch (FileUploadException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		RequestDispatcher rs; 
		rs = request.getRequestDispatcher("/employee.jsp");
		rs.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
