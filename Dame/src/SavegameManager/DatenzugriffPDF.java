package SavegameManager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Enumerations.FarbEnum;
import GameLogic.Spiel;
import Interfaces.iDatenzugriff;

public class DatenzugriffPDF implements iDatenzugriff{


	@Override
	public boolean saveGame(String path, String filename, Spiel game) {
		Document document = new Document();
		String gameString = "";
		gameString += "Spieler1: "+ game.getPlayer(1).getName() + " Farbe: " + game.getPlayer(1).getColor();
		if (game.getPlayer(1).getKi() == null) {
			gameString += "KI: Nein" + "\n";
		}
		else {
			gameString += "KI: Ja" + "\n";
		}
		gameString += "Spieler2: " + game.getPlayer(2).getName() + " Farbe: " + game.getPlayer(2).getColor();
		if (game.getPlayer(2).getKi() == null) {
			gameString += "KI: Nein" + "\n";
		}
		else {
			gameString += "KI: Ja" + "\n";
		}
		gameString += "Aktueller Spieler: " + game.getCurrentGamer().getColor() + "\n" + "Spielfeldgröße: " + game.getGameboard().getFields().length + "\n";
		try {
			PdfWriter.getInstance(document, new FileOutputStream(path + filename));
			document.open();
			document.add(new Paragraph(gameString));
			PdfPTable table = new PdfPTable(game.getGameboardSize());
			PdfPCell cell;
			for(int i = 0; i < game.getGameboardSize(); i++){
				for(int j = 0; j < game.getGameboardSize(); j++) {
					for(int k = 0; k < Math.pow(game.getGameboardSize(), 2); k++) {
						if (game.getGameboard().getField(i, j).getFigure().getColor() == FarbEnum.weiß) {
							if (game.getGameboard().getField(i, j).getFigure().isDame()) {
								cell = new PdfPCell(new Paragraph("W+"));
							} else {
								cell = new PdfPCell(new Paragraph("W "));
							}
						} else if (game.getGameboard().getField(i, j).getFigure().isDame()) {
							cell = new PdfPCell(new Paragraph("S+"));
						} else {
							cell = new PdfPCell(new Paragraph("S "));
						}
						
						table.addCell(cell);
					}
				}
			}
			document.close();
		} catch (FileNotFoundException e) {
			return false;
		} catch (DocumentException e) {
			return false;
		}

		return true;
	}

	@Override
	public boolean loadGame(String path, String filename, Spiel game) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean haveSaveGame(String path, String filename) {
		// TODO Auto-generated method stub
		return false;
	} 
}
