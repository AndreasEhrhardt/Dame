package SavegameManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Enumerations.FarbEnum;
import GameLogic.SpielBean;
import GameLogic.Spielfigur;
import Interfaces.iDatenzugriff;

public class DatenzugriffPDF implements iDatenzugriff{


	@Override
	public boolean saveGame(String path, String fileName, SpielBean game) {
		if(!path.endsWith("/")) path += "/";
		if(!fileName.endsWith(".pdf")) fileName += ".pdf";

		// Create folder if not exist
		File dir = new File(path);
		if(!dir.exists()) dir.mkdir();

		Document document = new Document();
		String gameString = "";
		gameString += "Spieler1: "+ game.getPlayer(1).getName() + "; Farbe: " + game.getPlayer(1).getColor();
		if (game.getPlayer(1).getKi() == null) {
			gameString += "; KI: Nein" + "\n";
		}
		else {
			gameString += "; KI: Ja" + "\n";
		}
		gameString += "Spieler2: " + game.getPlayer(2).getName() + "; Farbe: " + game.getPlayer(2).getColor();
		if (game.getPlayer(2).getKi() == null) {
			gameString += "; KI: Nein" + "\n";
		}
		else {
			gameString += "; KI: Ja" + "\n";
		}
		gameString += "Aktueller Spieler: " + game.getCurrentGamer().getColor() + "\n" + "Spielfeldgröße: " + game.getGameboardSize() + " * " + game.getGameboardSize() + "\n";
		try {
			PdfWriter.getInstance(document, new FileOutputStream(path + fileName));
			document.open();
			document.add(new Paragraph(gameString));
			PdfPTable table = new PdfPTable(game.getGameboardSize());
			PdfPCell cell;
			for(int i = game.getGameboardSize() - 1; i >= 0; i--) {
				for(int j = 0; j < game.getGameboardSize(); j++) {
						Spielfigur figure = game.getGameboard().getField(j, i).getFigure();
						
						if(figure == null){
							cell = new PdfPCell(new Paragraph("e "));
						}else if (game.getGameboard().getField(j, i).getFigure().getColor() == FarbEnum.weiß) {
							if (game.getGameboard().getField(j, i).getFigure().isDame()) {
								cell = new PdfPCell(new Paragraph("W+"));
							} else {
								cell = new PdfPCell(new Paragraph("W "));
							}
						} else if (game.getGameboard().getField(j, i).getFigure().isDame()) {
							cell = new PdfPCell(new Paragraph("S+"));
						} else {
							cell = new PdfPCell(new Paragraph("S "));
						}

						table.addCell(cell);
					}
				}
			table.setSpacingBefore(10);
			document.add(table);
			document.close();
		} catch (FileNotFoundException e) {
			return false;
		} catch (DocumentException e) {
			return false;
		}

		return true;
	}

	@Override
	public boolean loadGame(String path, String filename, SpielBean game) {
		if(!path.endsWith("/")) path += "/";
		if(!filename.endsWith(".pdf")) filename += ".pdf";

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean haveSaveGame(String path, String filename) {
		if(!path.endsWith("/")) path += "/";
		if(!filename.endsWith(".pdf")) filename += ".pdf";

		// TODO Auto-generated method stub
		return false;
	} 
}
