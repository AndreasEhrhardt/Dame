package Servlet;

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

import com.itextpdf.text.pdf.codec.Base64.OutputStream;
import com.itextpdf.text.pdf.parser.clipper.Paths;

import GameLogic.Spiel;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Dame")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Test() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		// Get Sessions
		HttpSession session = request.getSession(true);
		
		// Include CSS
		request.getRequestDispatcher("/WEB-INF/STYLES/Style.jsp").include(request, response);;
		
		// Create header
		request.getRequestDispatcher("/WEB-INF/Header.jsp").include(request, response);
		
		// Get active game object
		Spiel game = (Spiel)this.getServletConfig().getServletContext().getAttribute("GAME");
		
		// Check if game exist or new game should be created -> Show setup site
		if(game == null || (request.getParameter("CreateNewGame") == "true" && game.gameFinished() != null)){
			request.getRequestDispatcher("/WEB-INF/Setting.jsp").include(request, response); 
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
	
	@Override
	public void init(){
	}
}
