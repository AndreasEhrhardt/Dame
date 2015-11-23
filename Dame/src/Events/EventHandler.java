//###########################################################
//## Package

package Events;

import java.awt.Color;

//###########################################################
//## Imports

import java.awt.Component;
import java.awt.Point;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.log.SysoCounter;
import com.sun.mail.util.MailConnectException;

import GUI.*;
import GUI.PlayerSettings.PlayerSelectionButton;
import GameLogic.Spieler;
import SavegameManager.*;

//###########################################################
//## Class

public class EventHandler{

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Inner class

	public class eMainPanel implements ComponentListener{	

		@Override
		public void componentResized(ComponentEvent e) {
			Component component = e.getComponent();
			if(component instanceof MainPanel){
				MainPanel mp = (MainPanel) component;
				mp.fitComponent();
			}
		}

		@Override
		public void componentHidden(ComponentEvent arg0) {}

		@Override
		public void componentMoved(ComponentEvent arg0) {}

		@Override
		public void componentShown(ComponentEvent arg0) {}
	}

	public class eLoadingMenu implements ComponentListener{		

		@Override
		public void componentResized(ComponentEvent e) {}

		@Override
		public void componentHidden(ComponentEvent arg0) {}

		@Override
		public void componentMoved(ComponentEvent arg0) {}

		@Override
		public void componentShown(ComponentEvent e) {
			Component component = e.getComponent();
			if(component instanceof LoadingMenu){
				LoadingMenu lm = (LoadingMenu) component;
				DatenzugriffSerialisiert serial = new DatenzugriffSerialisiert();
				if(!serial.haveSaveGame()) lm.getLoadingSerializeButton().setDisabled(true);
				else lm.getLoadingSerializeButton().setDisabled(false);
				lm.updateUI();;
			}
		}
	}

	public class eButtonBack implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() instanceof ImageButton){
				MainPanel.globalPointer.back();
			}
		}
	}

	public class eButtonForward implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() instanceof ImageButton){
				MainPanel.globalPointer.forward();
			}
		}
	}

	public class eShowLoadingMenuButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() instanceof ImageButton){
				MainPanel.globalPointer.showLoadingMenu();
			}
		}
	}

	public class eShowGameboardSettingsButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() instanceof ImageButton){
				MainPanel.globalPointer.showGameboardSettings();
			}
		}
	}

	public class eGameboardStateChange implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			if(e.getSource() instanceof JSlider){
				JSlider slider = (JSlider) e.getSource();
				int size = slider.getValue();
				GameboardSettings.globalPointer.setGameboardSize(size);
			}  
		}
	}

	public class eGameboardSettings implements ComponentListener{

		@Override
		public void componentHidden(ComponentEvent arg0) {}

		@Override
		public void componentMoved(ComponentEvent arg0) {}

		@Override
		public void componentResized(ComponentEvent arg0) {}

		@Override
		public void componentShown(ComponentEvent arg0) {
			if(arg0.getSource() instanceof GameboardSettings){
				GameboardSettings widget = (GameboardSettings) arg0.getSource();
				int currentSize = MainFrame.globalPointer.getGame().getGameboardSize();
				widget.getSlider().setValue(currentSize);
			}
		}

	}

	public class eFieldButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() instanceof FieldButton) {
				FieldButton button = (FieldButton) e.getSource();
				Board.globalPointer.fieldPressed(button);
			}
		}
	}

	public class ePlayerSettings implements ComponentListener{

		@Override
		public void componentHidden(ComponentEvent e) {}

		@Override
		public void componentMoved(ComponentEvent e) {}

		@Override
		public void componentResized(ComponentEvent e) {}

		@Override
		public void componentShown(ComponentEvent e) {
			if(e.getSource() instanceof PlayerSettings){

				PlayerSettings widget = (PlayerSettings) e.getSource();

				Spieler player = MainFrame.globalPointer.getGame().getPlayer(1);
				if(player == null || player.getKi() == null){
					widget.getPlayer1Button().setEnabled();
					widget.getKI1Button().setDisabled();

					if(player != null) widget.getPlayer1Name().setText(player.getName());
				}
				else{
					widget.getPlayer1Button().setDisabled();
					widget.getKI1Button().setEnabled();
				}

				player = MainFrame.globalPointer.getGame().getPlayer(2);
				if(player == null || player.getKi() == null){
					widget.getPlayer2Button().setEnabled();
					widget.getKI2Button().setDisabled();

					if(player != null) widget.getPlayer2Name().setText(player.getName());
				}
				else{
					widget.getPlayer2Button().setDisabled();
					widget.getKI2Button().setEnabled();
				}
			}
		}
	}

	public class ePlayerButtonClicked implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource() instanceof PlayerSelectionButton) {
				PlayerSelectionButton button = (PlayerSelectionButton) e.getSource();
				if(!button.isEnabled()){
					button.setEnabled();
					if(button.getPlayerID() == 1){
						if(button.isKI()) PlayerSettings.globalPointer.getPlayer1Button().setDisabled();
						else PlayerSettings.globalPointer.getKI1Button().setDisabled();
					}
					else if(button.getPlayerID() == 2){
						if(button.isKI()) PlayerSettings.globalPointer.getPlayer2Button().setDisabled();
						else PlayerSettings.globalPointer.getKI2Button().setDisabled();
					}
				}
			}
		}
	}

	public class eGameGUI implements ComponentListener{		

		@Override
		public void componentResized(ComponentEvent e) {
			Component component = e.getComponent();
			if(component instanceof GameGUI){
				GameGUI board = (GameGUI) component;
				board.fitComponents();
			}
		}

		@Override
		public void componentHidden(ComponentEvent arg0) {}

		@Override
		public void componentMoved(ComponentEvent arg0) {}

		@Override
		public void componentShown(ComponentEvent e) {
			Component component = e.getComponent();
			if(component instanceof GameGUI){
				// Parse object to gameGUI
				GameGUI board = (GameGUI) component;

				// Create fields
				board.getGameboard().createField();

				// Fit the size of the componentes
				board.fitComponents();

				// Update GUI
				board.updateUI();
			}
		}
	}

	public class eBoard implements ComponentListener{		

		@Override
		public void componentResized(ComponentEvent e) {
			Component component = e.getComponent();
			if(component instanceof Board){
				Board board = (Board) component;
				board.setSize();
			}
		}

		@Override
		public void componentHidden(ComponentEvent arg0) {}

		@Override
		public void componentMoved(ComponentEvent arg0) {}

		@Override
		public void componentShown(ComponentEvent e) {}
	}

	public class eWinningScreen implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			DatenzugriffSerialisiert.deleteSaveGame();
			MainPanel.globalPointer.showStartpage();
		}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}
	}

	public class eLoadSerial implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {			
			DatenzugriffSerialisiert serial = new DatenzugriffSerialisiert();
			if(serial.haveSaveGame()){
				serial.loadGame(MainFrame.globalPointer.getGame());
				MainPanel.globalPointer.showGameGUI();
			}
		}
	}

	public class eSaveButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {			
			if(e.getSource() instanceof ImageButton){
				MainPanel.globalPointer.showSaveGUI();
			}
		}
	}

	public class eSaveCSVButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {			
			if(e.getSource() instanceof ImageButton){
				JFileChooser fc = new JFileChooser("Spielstand sichern (CSV)");
				fc.addChoosableFileFilter(new FileNameExtensionFilter("CSV - File", "csv"));
				fc.setAcceptAllFileFilterUsed(false);
				int result = fc.showSaveDialog(null);

				boolean error = false;

				switch (result) {
				case JFileChooser.APPROVE_OPTION:
					String dirPath = fc.getCurrentDirectory().toString();
					if(fc.getSelectedFile() != null){
						String fileName = fc.getSelectedFile().getName();

						DatenzugriffCSV savegame = new DatenzugriffCSV();
						if(savegame.saveGame(dirPath, fileName, MainFrame.globalPointer.getGame())){
							SaveGUI.globalPointer.setMessage("Save was successfully");
							return;
						}else error = true;
					}
					break;
				case JFileChooser.CANCEL_OPTION:
					break;
				case JFileChooser.ERROR_OPTION:
					error = true;
					break;
				}

				if(error) SaveGUI.globalPointer.setMessage("Couldn't save the game :(");
			}
		}
	}

	public class eSavePDFButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {			
			if(e.getSource() instanceof ImageButton){
				JFileChooser fc = new JFileChooser("Spielstand sichern (PDF)");
				fc.addChoosableFileFilter(new FileNameExtensionFilter("PDF - File", "pdf"));
				fc.setAcceptAllFileFilterUsed(false);
				int result = fc.showSaveDialog(null);

				boolean error = false;

				switch (result) {
				case JFileChooser.APPROVE_OPTION:

					String dirPath = fc.getCurrentDirectory().toString();
					if(fc.getSelectedFile() != null){
						String fileName = fc.getSelectedFile().getName();

						DatenzugriffPDF savegame = new DatenzugriffPDF();
						if(savegame.saveGame(dirPath, fileName, MainFrame.globalPointer.getGame())){
							SaveGUI.globalPointer.setMessage("Save was successfully");
						}else error = true;
					}
					break;
				case JFileChooser.CANCEL_OPTION:
					break;
				case JFileChooser.ERROR_OPTION:
					error = true;
					break;
				}

				if(error) SaveGUI.globalPointer.setMessage("Couldn't save the game :(");
			}
		}
	}

	public class eSaveGUI implements ComponentListener{		

		@Override
		public void componentResized(ComponentEvent e) {}

		@Override
		public void componentHidden(ComponentEvent arg0) {}

		@Override
		public void componentMoved(ComponentEvent arg0) {}

		@Override
		public void componentShown(ComponentEvent e) {
			Component component = e.getComponent();
			if(component instanceof SaveGUI){
				SaveGUI saveGUI = (SaveGUI) component;
				saveGUI.clearMessage();
			}
		}
	}

	public class eLoadCSVButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {			
			if(e.getSource() instanceof ImageButton){
				JFileChooser fc = new JFileChooser("Spielstand laden (CSV)");
				fc.addChoosableFileFilter(new FileNameExtensionFilter("CSV - File", "csv"));
				fc.setAcceptAllFileFilterUsed(false);
				int result = fc.showSaveDialog(null);

				boolean error = false;

				switch (result) {
				case JFileChooser.APPROVE_OPTION:

					String dirPath = fc.getCurrentDirectory().toString();
					if(fc.getSelectedFile() != null){
						String fileName = fc.getSelectedFile().getName();

						DatenzugriffCSV savegame = new DatenzugriffCSV();
						if(savegame.loadGame(dirPath, fileName, MainFrame.globalPointer.getGame())){
							MainPanel.globalPointer.showGameGUI();
						}else error = true;
					}
					break;
				case JFileChooser.CANCEL_OPTION:
					break;
				case JFileChooser.ERROR_OPTION:
					error = true;
					break;
				}

				if(error) SaveGUI.globalPointer.setMessage("Couldn't save the game :(");
			}
		}
	}

	public class eLoadPDFButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {			
			if(e.getSource() instanceof ImageButton){
				JFileChooser fc = new JFileChooser("Spielstand laden (PDF)");
				fc.addChoosableFileFilter(new FileNameExtensionFilter("PDF - File", "pdf"));
				fc.setAcceptAllFileFilterUsed(false);
				int result = fc.showOpenDialog(null);

				boolean error = false;

				switch (result) {
				case JFileChooser.APPROVE_OPTION:

					String dirPath = fc.getCurrentDirectory().toString();
					if(fc.getSelectedFile() != null){
						String fileName = fc.getSelectedFile().getName();

						DatenzugriffPDF savegame = new DatenzugriffPDF();
						if(savegame.loadGame(dirPath, fileName, MainFrame.globalPointer.getGame())){
							MainPanel.globalPointer.showGameGUI();
						}
						else error = true;
					}
					break;
				case JFileChooser.CANCEL_OPTION:
					break;
				case JFileChooser.ERROR_OPTION:
					error = true;
					break;
				}

				if(error) SaveGUI.globalPointer.setMessage("Couldn't save the game :(");
			}
		}
	}

	public class eLoadGUI implements ComponentListener{		

		@Override
		public void componentResized(ComponentEvent e) {}

		@Override
		public void componentHidden(ComponentEvent arg0) {}

		@Override
		public void componentMoved(ComponentEvent arg0) {}

		@Override
		public void componentShown(ComponentEvent e) {
			Component component = e.getComponent();
			if(component instanceof LoadGUI){
				LoadGUI loadGUI = (LoadGUI) component;
				loadGUI.clearMessage();
			}
		}
	}

	public class eFileLoadingButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {			
			if(e.getSource() instanceof ImageButton){
				MainPanel.globalPointer.showLoadGUI();
			}
		}
	}

	public class eSendButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {			
			if(e.getSource() instanceof ImageButton){
				MainPanel.globalPointer.showMailSending();
			}
		}
	}

	public class eSendingCSVButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {			
			if(e.getSource() instanceof ImageButton){
				MailSending.globalPointer.CSVPressed();
			}
		}
	}

	public class eSendingPDFButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {			
			if(e.getSource() instanceof ImageButton){
				MailSending.globalPointer.PDFPressed();
			}
		}
	}

	public class eSendingMailButtton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {			
			if(e.getSource() instanceof ImageButton){
				try{
					// Setup connection settings
					String host = "smtp.t4ggno.com";
					String password = "svNLG8Qq4T3y9crc6dBn";
					String from = "dame@t4ggno.com";
					String toAddress = MailSending.globalPointer.getAdress();
					String filename = MailSending.globalPointer.createEmailSavegame();
					if(filename == null){
						MailSending.globalPointer.setMessage("File couldn't be generated");
						return;
					}

					// Get system properties
					Properties props = new Properties();
					props.put("mail.smtp.host", host);
					props.put("mail.smtp.auth", "true");
					props.put("mail.smtp.port", 25);
					Session session = Session.getInstance(props, null);

					// Create message
					MimeMessage message = new MimeMessage(session);

					// Set data to message
					message.setFrom(new InternetAddress(from));	// From
					message.setRecipients(Message.RecipientType.TO, toAddress);	// To
					message.setSubject(" > Dame < - Your savegame file");	// Subject

					BodyPart messageBodyPart = new MimeBodyPart();	// Body
					messageBodyPart.setText("Here is your savegame.\nYou can now load it with the game!");	// Body text
					Multipart multipart = new MimeMultipart();	// Multi body
					multipart.addBodyPart(messageBodyPart);	// Add body

					// Add file
					messageBodyPart = new MimeBodyPart();
					DataSource source = new FileDataSource(new File(filename));
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(filename);
					multipart.addBodyPart(messageBodyPart);
					
					// Set body to message
					message.setContent(multipart);
					
					// Try to send message
					try {
						Transport tr = session.getTransport();
						tr.connect(host, from, password);
						tr.sendMessage(message, message.getAllRecipients());
						MailSending.globalPointer.setMessage("E-Mail succcessfully send");
						tr.close();
					} catch (SendFailedException sfe) {
						System.out.println(sfe);
						MailSending.globalPointer.setMessage("Could not send the message!");
					} catch(MailConnectException mce){
						System.out.println(mce);
						MailSending.globalPointer.setMessage("Could not connect to server!");
					}
				}
				catch (Exception exception){
					System.out.println(exception);
					MailSending.globalPointer.setMessage("Something went wrong. sry!");
				}
			}
		}
	}
}