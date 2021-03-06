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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get Sessions
		HttpSession session = request.getSession(true);
		request.getRequestDispatcher("/WEB-INF/STYLES/Style.jsp").include(request, response);
		request.getRequestDispatcher("/WEB-INF/Header.jsp").include(request, response);
		request.getRequestDispatcher("/WEB-INF/load.jsp").include(request, response);

		SpielBean game = (SpielBean) this.getServletConfig().getServletContext().getAttribute("GAME");
		this.getServletConfig().getServletContext().removeAttribute("GAME");
		game = null;

		String filetype = "";
		String path = "";
		String filename = "";

		if (request.getParameter("Laden") != null) {
			filetype = request.getParameter("Laden");
		}

		if (request.getParameter("Path") != null) {
			path = request.getParameter("Path");
		}

		if (request.getParameter("Dateiname") != null) {
			filename = request.getParameter("Dateiname");
		}

		if (filetype.matches("CSV")) {
			iDatenzugriff csv = new DatenzugriffCSV();
			game = new SpielBean();
			csv.loadGame(path, filename, game);
		}

		if (filetype.matches("Serialisiert")) {
			iDatenzugriff ser = new DatenzugriffSerialisiert();
			game = new SpielBean();
			ser.loadGame(path, filename, game);
		}
		
		if(filetype.matches("XML")){
			iDatenzugriff xml = new DatenzugriffXML();
			game= new SpielBean();
			xml.loadGame(path, filename, game);
		}
		if (filetype.matches("CSV")||filetype.matches("XML")||filetype.matches("Serialisiert")){
		// Output information
		String player1 = game.getPlayer(1).getName();
		String player2 = game.getPlayer(2).getName();

		// Save game in application scope
		this.getServletConfig().getServletContext().setAttribute("GAME", game);

		// Set player as active gamer
		this.getServletConfig().getServletContext().setAttribute("P1_SESSION", false);
		this.getServletConfig().getServletContext().setAttribute("P2_SESSION", false);
		if (game.getPlayer(1).getKi() == null) {
			this.getServletConfig().getServletContext().setAttribute("P1_SESSION", true);
			session.setAttribute("NAME", player1);
		} else if (game.getPlayer(2).getKi() == null) {
			this.getServletConfig().getServletContext().setAttribute("P2_SESSION", true);
			session.setAttribute("NAME", player2);
		}

		// Set gameid
		if (session.getAttribute("NAME") != null)
			session.setAttribute("GAME_ID", game.getID());


		session.setAttribute("GAME", game);
		response.sendRedirect(request.getContextPath() + "/BacktoGameServlet");

		request.getRequestDispatcher("/WEB-INF/Footer.jsp").include(request, response);

	}}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
