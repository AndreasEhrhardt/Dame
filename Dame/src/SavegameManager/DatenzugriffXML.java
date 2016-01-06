package SavegameManager;

import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import GameLogic.SpielBean;
import Interfaces.iDatenzugriff;

public class DatenzugriffXML implements iDatenzugriff {

	@Override
	public boolean saveGame(String path, String filename, SpielBean game) {
		FileWriter fw = null;
		try {
			JAXBContext context = JAXBContext.newInstance(SpielBean.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(game, System.out);
			fw = new FileWriter(filename);
			m.marshal(game, fw);
			
		} catch (Exception e) {
			System.out.println(e);
			return false;
		} 
		finally {
			try {
				fw.close();
			} catch (Exception e) {
				System.out.println(e);
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean loadGame(String path, String filename, SpielBean game) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean haveSaveGame(String path, String filename) {
		// TODO Auto-generated method stub
		return false;
	}

}
