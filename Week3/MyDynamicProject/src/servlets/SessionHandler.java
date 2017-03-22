package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionHandler
 */
public class SessionHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int visitCount = 0;
		HttpSession currSession = request.getSession();
		
		if (request.getSession().isNew()){
			currSession.setAttribute("Visit Count", 1);
		}
		
		// increment whenever the visit button is clicked
		currSession.setAttribute("Visit Count", (int)currSession.getAttribute("Visit Count")+1);
		visitCount = (int)currSession.getAttribute("Visit Count");
		
		PrintWriter out = response.getWriter();
		out.println(
				"<html><body>" + "Visit Count: " + visitCount + "</body></html>"
				);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
