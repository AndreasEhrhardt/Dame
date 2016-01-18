package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import GameLogic.SpielBean;

/**
 * Servlet implementation class SettingServlet
 */
@WebServlet("/SettingServlet")
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SettingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get Sessions
		HttpSession session = request.getSession(true);

		// Include CSS
		request.getRequestDispatcher("/WEB-INF/STYLES/Style.jsp").include(request, response);;

		// Create header
		request.getRequestDispatcher("/WEB-INF/Header.jsp").include(request, response);
		
		request.getRequestDispatcher("/WEB-INF/loadOrStart.jsp").include(request, response);
	
		if (request.getParameter("LoadGame") != null && request.getParameter("LoadGame").compareTo("true") == 0) {
			request.getRequestDispatcher("/LoadServlet").include(request, response);
		} else if (request.getParameter("NewGame") != null && request.getParameter("NewGame").compareTo("true") == 0) {
			response.sendRedirect(request.getContextPath() + "/Dame");
			
			
		}
		// Create footer
		request.getRequestDispatcher("/WEB-INF/Footer.jsp").include(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
