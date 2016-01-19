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
import SavegameManager.DatenzugriffPDF;
import SavegameManager.DatenzugriffSerialisiert;
import SavegameManager.DatenzugriffXML;

/**
 * Servlet implementation class SaveServlet
 */
@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveServlet() {
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
		
		
		request.getRequestDispatcher("/WEB-INF/save.jsp").include(request, response);
		// Get active game object
		SpielBean game = (SpielBean)this.getServletConfig().getServletContext().getAttribute("GAME");
			
		String filetype = "";
		String path = "";
		String filename = "";
		
		if(request.getParameter("Filetype") != null) {
		filetype = request.getParameter("Filetype");
		}
		
		if(request.getParameter("path") != null) {
			path = request.getParameter("path");
		}
		
		if(request.getParameter("filename") != null) {
		filename = request.getParameter("filename");
		}
		
		//save as CSV
		if(filetype.matches("CSV")){
			iDatenzugriff csv = new DatenzugriffCSV();
			csv.saveGame(path, filename, game);
			response.sendRedirect(request.getContextPath() + "/BacktoGameServlet");
			
		}
		//save as PDF
		if(filetype.matches("PDF")){
			iDatenzugriff pdf = new DatenzugriffPDF();
			pdf.saveGame(path, filename, game);
			request.getRequestDispatcher("/WEB-INF/link.jsp").include(request, response); 
			
		}
		//save as Serialised
		if(filetype.matches("Serialised")){
			iDatenzugriff serialised = new DatenzugriffSerialisiert();
			serialised.saveGame(path, filename, game);
			response.sendRedirect(request.getContextPath() + "/BacktoGameServlet");
		}
		//save as XML
		if(filetype.matches("XML")){
			iDatenzugriff xml = new DatenzugriffXML();
			xml.saveGame(path, filename + ".xml", game);
			response.sendRedirect(request.getContextPath() + "/BacktoGameServlet");
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
