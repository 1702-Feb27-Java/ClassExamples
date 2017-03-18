package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionHandler2
 */
public class SessionHandler2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionHandler2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int visitCount = 0;
		
		HttpSession curSes = request.getSession();
		
		if(curSes.isNew()){
			curSes.setAttribute("VisitCount", 0);
		}
		
		curSes.setAttribute("VisitCount", (int)curSes.getAttribute("VisitCount") + 1);
		visitCount = (int)curSes.getAttribute("VisitCount");
		
		PrintWriter out = response.getWriter();
		out.println(
				"<html><body>" +
						"VisitCount: " + visitCount +
						"</body></html>"
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
