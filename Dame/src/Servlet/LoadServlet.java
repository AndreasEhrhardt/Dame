package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import GameLogic.SpielBean;
import Interfaces.iDatenzugriff;
import SavegameManager.DatenzugriffCSV;
import SavegameManager.DatenzugriffSerialisiert;
import SavegameManager.DatenzugriffXML;

/**
 * Servlet implementation class LoadServlet
 */
@WebServlet("/LoadServlet")
public class LoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadServlet() {
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
		request.getRequestDispatcher("/WEB-INF/load.jsp").include(request, response);

	
		if (request.getParameter("LoadGame") != null && request.getParameter("LoadGame").compareTo("true") == 0) {
				if(request.getParameter("Path") != null && request.getParameter("Dateiname") != null) {
					System.out.println("blaaa");
					request.getRequestDispatcher("/LoadGameServlet").include(request, response);
		}
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
