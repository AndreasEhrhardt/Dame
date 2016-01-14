package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Enumerations.FarbEnum;
import GameLogic.SpielBean;
import GameLogic.Spielbrett;
import GameLogic.Spieler;
import Interfaces.iDatenzugriff;
import KI.KI_Dame;
import SavegameManager.*;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Dame")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public Servlet() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		@SuppressWarnings("unused")
		PrintWriter out = response.getWriter();

		// Get Sessions
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(-1);

		// Include CSS
		request.getRequestDispatcher("/WEB-INF/STYLES/Style.jsp").include(request, response);;

		// Create header
		request.getRequestDispatcher("/WEB-INF/Header.jsp").include(request, response);

		// Get active game object
		SpielBean game = (SpielBean)this.getServletConfig().getServletContext().getAttribute("GAME");
		
		// Reset sessions if not valid
		if(!this.activeGamer(session, game)) this.reset(session);
		
		// Check if game exist or new game should be created -> Show setup site
		if(game == null
				|| (request.getParameter("NewGame") != null && request.getParameter("NewGame").compareTo("true") == 0 && this.activeGamer(session, game))
				|| (request.getParameter("NewGame") != null && request.getParameter("NewGame").compareTo("true") == 0 && game.getPlayer(1).getKi() != null && game.getPlayer(2).getKi() != null)){
			System.out.println("Create new game");

			// Remove game object
			this.getServletConfig().getServletContext().removeAttribute("GAME");
			game = null;
			
			//laden csv
			if(request.getParameter("Path") != null && request.getParameter("Dateiname") != null) {
				if(request.getParameter("Laden").matches("CSV")){
				iDatenzugriff csv = new DatenzugriffCSV();
				game = new SpielBean();
				csv.loadGame(request.getParameter("Path"), request.getParameter("Dateiname"), game);
				}
				if(request.getParameter("Laden").matches("Serialisiert")){
					DatenzugriffSerialisiert ser = new DatenzugriffSerialisiert();
					game = new SpielBean();
					ser.loadGame(request.getParameter("Path"), request.getParameter("Dateiname"), game);
					}
				// Output information
				
				String player1 = request.getParameter("PLAYER1");
				String player2 = request.getParameter("PLAYER2");
				
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
			}
				
			else {
			String player1 = request.getParameter("PLAYER1");
			String player2 = request.getParameter("PLAYER2");

			if(((player1 != null && !player1.isEmpty()) || (request.getParameter("P1_KI") != null && request.getParameter("P1_KI").compareTo("true") == 0)) && 
					((player2 != null && !player2.isEmpty()) || (request.getParameter("P2_KI") != null && request.getParameter("P2_KI").compareTo("true") == 0))){
				// Remove old games
				this.getServletConfig().getServletContext().removeAttribute("GAME");

				// Remove user-session connection
				this.getServletConfig().getServletContext().removeAttribute("P1_SESSION");
				this.getServletConfig().getServletContext().removeAttribute("P2_SESSION");

				// Create game object
				game = new SpielBean();

				// Set gameboard size
				game.setGameboard(new Spielbrett(12));

				// Create players
				if(request.getParameter("P1_KI") != null && request.getParameter("P1_KI").compareTo("true") == 0)
					game.setPlayer(1, new Spieler(new KI_Dame(),FarbEnum.schwarz));
				else
					game.setPlayer(1, new Spieler(player1,FarbEnum.schwarz));

				if(request.getParameter("P2_KI") != null && request.getParameter("P2_KI").compareTo("true") == 0)
					game.setPlayer(2, new Spieler(new KI_Dame(),FarbEnum.weiß));
				else
					game.setPlayer(2, new Spieler(player2,FarbEnum.weiß));


				// Set black as current gamer
				game.setCurrentGamer(FarbEnum.schwarz);
			
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
			} else{
				// Import start page
				request.getRequestDispatcher("/WEB-INF/Setting.jsp").include(request, response); 
			} }
		} 

		// Show gameboard
		if(game != null){
			if(session.getAttribute("NAME") == null || 
					!(session.getAttribute("GAME_ID") != null && (int)session.getAttribute("GAME_ID") == game.getID())
					){
				session.removeAttribute("GAME_ID");
				session.removeAttribute("NAME");

				if((boolean)this.getServletConfig().getServletContext().getAttribute("P2_SESSION") != true){
					if(game.getPlayer(2).getKi() == null){
						this.getServletConfig().getServletContext().setAttribute("P2_SESSION", true);
						session.setAttribute("NAME", game.getPlayer(2).getName());
						session.setAttribute("GAME_ID", game.getID());
					}
				}
			}

			if(game.gameFinished() == null){
				// Check if currently KI on move
				if(game.getCurrentGamer().getKi() != null) 
					game.getCurrentGamer().getKi().move(game);
				else{
					if(session.getAttribute("NAME") instanceof String && game.getCurrentGamer().getName().compareTo((String)session.getAttribute("NAME")) == 0){
						if(request.getParameter("FieldPressed") != null){
							game.nextFieldClicked(request.getParameter("FieldPressed"));
						}
					}
				}
			}

			// Output information
			System.out.println("Gameobject found");

			// Add game as paramter
			request.setAttribute("GAME", game);
			
			//save game
			if (request.getParameter("SaveGame") != null && request.getParameter("SaveGame").compareTo("true") == 0) {
				request.getRequestDispatcher("/WEB-INF/save.jsp").include(request, response); 
			}
			String filetype = "";
			String filename = "";
			
			if(request.getParameter("Filetype") != null) {
			filetype = request.getParameter("Filetype");
			}
			
			if(request.getParameter("filename") != null) {
			filename = request.getParameter("filename");
			}
			
			//save as CSV
			if(filetype.matches("CSV")){
				iDatenzugriff csv = new DatenzugriffCSV();
				csv.saveGame("./", filename, game);
			}
			//save as PDF
			if(filetype.matches("PDF")){
				iDatenzugriff pdf = new DatenzugriffPDF();
				pdf.saveGame("./", filename, game);
			}
			//save as Serialised
			if(filetype.matches("Serialised")){
				iDatenzugriff serialised = new DatenzugriffSerialisiert();
				serialised.saveGame("./", filename, game);
			}
			//save as XML
			if(filetype.matches("XML")){
				iDatenzugriff xml = new DatenzugriffXML();
				xml.saveGame("./", filename + ".xml", game);
			}

			// Include game-view or winning-view
			if(game.gameFinished() == null) request.getRequestDispatcher("/WEB-INF/Game.jsp").include(request, response);
			else request.getRequestDispatcher("/WEB-INF/winView.jsp").include(request, response); 

			// Add game as paramter
			request.setAttribute("GAME", game);
		}

		// Create footer
		request.getRequestDispatcher("/WEB-INF/Footer.jsp").include(request, response);
	}
	
	void reset(HttpSession session){		
		session.removeAttribute("NAME");
		session.removeAttribute("GAME_ID");
	}
	
	boolean activeCurrentGamer(HttpSession session, SpielBean game){
		if(!activeGamer(session,game)) return false;
		
		String name = (String)session.getAttribute("NAME");
		if(name.equals(game.getCurrentGamer().getName())) return true;
		else return false;
	}
	
	boolean activeGamer(HttpSession session, SpielBean game){		
		if(game == null || session == null) return false;
		if(session.getAttribute("NAME") == null || session.getAttribute("GAME_ID") == null) return false;
		if((int)session.getAttribute("GAME_ID") != game.getID()) return false;
		
		Spieler gamer1 =  game.getPlayer(1);
		Spieler gamer2 =  game.getPlayer(2);
		
		if(session.getAttribute("NAME") == gamer1.getName() || session.getAttribute("NAME") == gamer2.getName()) return true;
		
		return false;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void init(){
	}
}
