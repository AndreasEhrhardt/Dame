//###########################################################
//## Package

package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Events.EventHandler;

//###########################################################
//## Imports


//###########################################################
//## Class

/**
 * @author ehrha
 *
 */
@SuppressWarnings("serial")
public class SaveGUI extends MainPanelComponent {

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	private ImageButton saveCSVButton;
	private ImageButton savePDFButton;
	private JLabel statusLabel;
	
	public static SaveGUI globalPointer = null;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public SaveGUI(){
		// Set global pointer
		globalPointer = this;
		
		// Set transparent background
		this.setOpaque(false);
		
		// Create image buttons
		saveCSVButton = new ImageButton("Save as CSV");
		saveCSVButton.setDefaultImage("Images/Save_CSV.png");
		saveCSVButton.setHoverImage("Images/Save_CSV_Hover.png");
		saveCSVButton.setPressImage("Images/Save_CSV_Pressed.png");

		savePDFButton = new ImageButton("Save as PDF");
		savePDFButton.setDefaultImage("Images/Save_PDF.png");
		savePDFButton.setHoverImage("Images/Save_PDF_Hover.png");
		savePDFButton.setPressImage("Images/Save_PDF_Pressed.png");
		
		// Create status label
		statusLabel = new JLabel("");
		statusLabel.setFont(new Font("Gill Sans", Font.BOLD, 40));
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setForeground(new Color(255,255,255));
		
		// Set pref size
		saveCSVButton.setPreferredSize(new Dimension(200, 200));
		savePDFButton.setPreferredSize(new Dimension(200, 200));
		
		// Add event handler to buttons
		saveCSVButton.addActionListener(new EventHandler().new eSaveCSVButton());
		savePDFButton.addActionListener(new EventHandler().new eSavePDFButton());

		// Create constraints
		GridBagConstraints c = new GridBagConstraints();

		// Create layout
		this.setLayout(new GridBagLayout());
		
		c.insets = new Insets(0,0,200,0);
		c.gridx = 0; c.gridy = 0;  c.gridwidth = 2; 
		this.add(this.statusLabel, c);
		
		c.insets = new Insets(0,0,0,0);
		c.gridx = 0; c.gridy = 1; c.gridwidth = 1; 
		this.add(this.saveCSVButton, c);
		
		c.insets = new Insets(0,150,0,0);
		c.gridx = 1; c.gridy = 1;
		this.add(this.savePDFButton, c);
		
		// Add event handler to panel
		this.addComponentListener(new EventHandler().new eSaveGUI());
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods

	public void setMessage(String message){
		this.statusLabel.setText(message);
	}
	
	public void clearMessage(){
		this.statusLabel.setText("");
	}

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Getter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods ( Setter)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Methods (Override)


	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Inner class

}