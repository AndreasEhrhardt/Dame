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
		
		// Get active game object
		SpielBean game = (SpielBean)session.getServletContext().getAttribute("GAME");
	
		
	if(request.getParameter("Path") != null && request.getParameter("Dateiname") != null) {
		if(request.getParameter("Laden").matches("CSV")){
			iDatenzugriff csv = new DatenzugriffCSV();
			game = new SpielBean();
			csv.loadGame(request.getParameter("Path"), request.getParameter("Dateiname"), game);
		}
		if(request.getParameter("Laden").matches("Serialisiert")){
				iDatenzugriff ser = new DatenzugriffSerialisiert();
				game = new SpielBean();
				ser.loadGame(request.getParameter("Path"), request.getParameter("Dateiname"), game);
		}
		if(request.getParameter("Laden").matches("XML")){
				iDatenzugriff xml = new DatenzugriffXML();
				game= new SpielBean();
				xml.loadGame(request.getParameter("Path"), request.getParameter("Dateiname"), game);
				System.out.println(game.getGameboardSize());
				System.out.println(game.getCurrentGamer());
		}
			// Output information
			String player1 = game.getPlayer(1).getName();
			String player2 = game.getPlayer(2).getName();
			
			// Save game in application scope
			this.getServletConfig().getServletContext().setAttribute("GAME", game);

			// Set player as active gamer
			this.getServletConfig().getServletContext().setAttribute("P1_SESSION", false);
			this.getServletConfig().getServletContext().setAttribute("P2_SESSION", false);
			if(game.getPlayer(1).getKi() == null){
				this.getServletConfig().getServletContext().setAttribute("P1_SESSION", true);
				session.setAttribute("NAME", player1);
			}
			else if(game.getPlayer(2).getKi() == null){
				this.getServletConfig().getServletContext().setAttribute("P2_SESSION", true);
				session.setAttribute("NAME", player2);
			}
		
			// Set gameid
			if(session.getAttribute("NAME") != null) session.setAttribute("GAME_ID", game.getID());

			// Output information
			System.out.println("Gameobject created!");
		session.setAttribute("GAME", game);
		response.sendRedirect(request.getContextPath() + "/Dame");
	}
	else System.out.println("nixx");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
