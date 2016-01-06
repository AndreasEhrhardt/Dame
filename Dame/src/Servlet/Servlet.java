package Servlet;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.log.SysoCounter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;
import com.itextpdf.text.pdf.parser.clipper.Paths;

import Enumerations.FarbEnum;
import GameLogic.SpielBean.eInvalidPointException;
import GameLogic.SpielBean;
import GameLogic.Spielbrett;
import GameLogic.Spieler;
import KI.KI_Dame;

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

		// Check if game exist or new game should be created -> Show setup site
		if(game == null || (request.getParameter("CreateNewGame") == "true" && game.gameFinished() != null)){
			System.out.println("No Gameobject found");
			
			// Remove game object
			this.getServletConfig().getServletContext().removeAttribute("GAME");

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
				if(game.getPlayer(1).getKi() == null){
					this.getServletConfig().getServletContext().setAttribute("P1_SESSION", true);
					session.setAttribute("NAME", player1);
				}
				else if(game.getPlayer(2).getKi() == null){
					this.getServletConfig().getServletContext().setAttribute("P2_SESSION", true);
					session.setAttribute("NAME", player2);
				}
				
				// Output information
				System.out.println("Gameobject created!");
			}else{
				// Import start page
				request.getRequestDispatcher("/WEB-INF/Setting.jsp").include(request, response); 
			}
		}
		
		// Show gameboard
		if(game != null){
			// Check if currently KI on move
			if(game.getCurrentGamer().getKi() != null) game.getCurrentGamer().getKi().move(game);
			else{
				if(session.getAttribute("NAME") instanceof String && game.getCurrentGamer().getName().compareTo((String)session.getAttribute("NAME")) == 0){
					if(request.getParameter("FieldPressed") != null){
						game.nextFieldClicked(request.getParameter("FieldPressed"));
					}
				}
			}
			
			// Output information
			System.out.println("Gameobject found");
			
			// Add game as paramter
			request.setAttribute("GAME", game);
			
			// Include game-view
			request.getRequestDispatcher("/WEB-INF/Game.jsp").include(request, response); 
			
			// Add game as paramter
			request.setAttribute("GAME", game);
		}

		// Create footer
		request.getRequestDispatcher("/WEB-INF/Footer.jsp").include(request, response);
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
