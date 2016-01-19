package SavegameManager;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import GameLogic.SpielBean;
import Interfaces.iDatenzugriff;

public class DatenzugriffXML implements iDatenzugriff {

	@Override
	public boolean saveGame(String path, String filename, SpielBean game) {
		FileWriter fw = null;
		String s= "/";
		if(!path.endsWith(s))path+=s;
		try {
			JAXBContext context = JAXBContext.newInstance(SpielBean.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(game, System.out);
			fw = new FileWriter(path+filename);
			m.marshal(game, fw);
			
		} catch (Exception e) {
			
			return false;
		} 
		finally {
			try {
				fw.close();
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean loadGame(String path, String filename, SpielBean game) {
		String s= "/" ;
		if(!path.endsWith(s))path+=s;
		if(!filename.endsWith(".xml"))filename+=".xml";
		try {
			JAXBContext context = JAXBContext.newInstance(SpielBean.class);
			Unmarshaller um = context.createUnmarshaller();
			game = (SpielBean) um.unmarshal(new FileReader(path+filename));
			
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean haveSaveGame(String path, String filename) {
		// TODO Auto-generated method stub
		return false;
	}

}
