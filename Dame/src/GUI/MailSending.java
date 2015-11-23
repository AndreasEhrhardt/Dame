//###########################################################
//## Package

package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JTextField;

import Events.EventHandler;
import SavegameManager.DatenzugriffCSV;
import SavegameManager.DatenzugriffPDF;

//###########################################################
//## Imports


//###########################################################
//## Class

@SuppressWarnings("serial")
public class MailSending extends MainPanelComponent{

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	private ImageButton CSVButton;
	private ImageButton PDFButton;
	private ImageButton sendButton;
	private JTextField emailAdress;
	private JLabel statusMessage;
	private JLabel lEmailAdress;

	public static MailSending globalPointer = null;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public MailSending(){
		MailSending.globalPointer = this;

		this.CSVButton = new ImageButton("As CSV");
		this.CSVButton.setDefaultImage("Images/CSV.png");
		this.CSVButton.setDisabledImage("Images/CSV_Disabled.png");
		this.CSVButton.setHoverImage("Images/CSV_Hover.png");
		this.CSVButton.setPressImage("Images/CSV_Pressed.png");
		this.CSVButton.setPreferredSize(new Dimension(150,150));
		this.CSVButton.addActionListener(new EventHandler().new eSendingCSVButton());

		this.PDFButton = new ImageButton("As PDF");
		this.PDFButton.setDefaultImage("Images/PDF.png");
		this.PDFButton.setDisabledImage("Images/PDF_Disabled.png");
		this.PDFButton.setHoverImage("Images/PDF_Hover.png");
		this.PDFButton.setPressImage("Images/PDF_Pressed.png");
		this.PDFButton.setDisabled(true);
		this.PDFButton.setPreferredSize(new Dimension(150,150));
		this.PDFButton.addActionListener(new EventHandler().new eSendingPDFButton());

		this.sendButton = new ImageButton("Send");
		this.sendButton.setDefaultImage("Images/EMail.png");
		this.sendButton.setHoverImage("Images/EMail_Hover.png");
		this.sendButton.setPressImage("Images/EMail_Pressed.png");
		this.sendButton.setPreferredSize(new Dimension(100,100));
		this.sendButton.addActionListener(new EventHandler().new eSendingMailButtton());

		emailAdress = new JTextField();
		emailAdress.setPreferredSize(new Dimension(400,40));
		emailAdress.setFont(new Font("Gill Sans", Font.PLAIN, 20));

		statusMessage = new JLabel();
		statusMessage.setFont(new Font("Gill Sans", Font.PLAIN, 30));
		statusMessage.setForeground(new Color(255,255,255));
		statusMessage.setPreferredSize(new Dimension(500,40));

		lEmailAdress = new JLabel("E-Mail:");
		lEmailAdress.setFont(new Font("Gill Sans", Font.PLAIN, 30));
		lEmailAdress.setForeground(new Color(255,255,255));
		lEmailAdress.setPreferredSize(new Dimension(110,40));

		// Set constraints
		GridBagConstraints c = new GridBagConstraints();

		// Create layout manager
		this.setLayout(new GridBagLayout());

		// Add buttons
		c.insets = new Insets(0,0,0,0);
		c.gridx = 0; c.gridy = 0; 
		this.add(CSVButton,c);

		c.insets = new Insets(0,500,0,0);
		c.gridx = 1; c.gridy = 0; 
		this.add(PDFButton,c);

		// Create group box
		GroupBox gp = new GroupBox();
		gp.setLayout(new GridBagLayout());
		gp.setPreferredSize(new Dimension(800,200));

		c.insets = new Insets(5,5,5,5);
		c.gridx = 0; c.gridy = 0; 
		gp.add(lEmailAdress,c);

		c.gridx = 2; c.gridy = 0; 
		gp.add(emailAdress,c);

		c.gridx = 3; c.gridy = 0; 
		gp.add(sendButton,c);

		c.gridx = 0; c.gridy = 1; c.gridwidth = 3;
		gp.add(statusMessage,c);

		// Add group box
		c.insets = new Insets(100,0,0,0);
		c.gridx = 0; c.gridy = 1; c.gridwidth = 2;
		this.add(gp,c);
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods

	/**
	 * 
	 */
	public void CSVPressed(){
		if(this.CSVButton.getDisabled()) {
			CSVButton.setDisabled(false);
			PDFButton.setDisabled(true);
		}
	}

	/**
	 * 
	 */
	public void PDFPressed(){
		if(this.PDFButton.getDisabled()) {
			CSVButton.setDisabled(true);
			PDFButton.setDisabled(false);
		}
	}

	public String createEmailSavegame(){
		if(!this.CSVButton.getDisabled()){
			DatenzugriffCSV savegameManager = new DatenzugriffCSV();
			if(savegameManager.saveGame("./MailTransfer/", "savegame.csv", MainFrame.globalPointer.getGame())) return "./MailTransfer/savegame.csv";
			else return null;
		}
		else if(!this.PDFButton.getDisabled()){
			DatenzugriffPDF savegameManager = new DatenzugriffPDF();
			if(savegameManager.saveGame("./MailTransfer/", "savegame.pdf", MainFrame.globalPointer.getGame())) return "./MailTransfer/savegame.pdf";
			else return null;
		}
		else return null;
	}

	public void deleteEmailSavegame(){
		File directory = new File("./MailTransfer/");
		if(directory.exists()){
			File[] files = directory.listFiles();
			if(files != null){
				for(int i = 0; i < files.length; i++) {
					if(!files[i].isDirectory()) {
						files[i].delete();
					}
				}
			}
		}
	}
	
	public void setMessage(String message){
		this.statusMessage.setText(message);
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)

	public String getAdress(){
		return this.emailAdress.getText();
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Inner class

}