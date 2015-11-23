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
public class LoadGUI extends MainPanelComponent {

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Properties

	private ImageButton loadCSVButton;
	private ImageButton loadPDFButton;
	private JLabel statusLabel;
	
	public static LoadGUI globalPointer = null;

	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//++ Constructor

	public LoadGUI(){
		// Set global pointer
		globalPointer = this;
		
		// Set transparent background
		this.setOpaque(false);
		
		// Create image buttons
		loadCSVButton = new ImageButton("Load as CSV");
		loadCSVButton.setDefaultImage("Images/Load_CSV.png");
		loadCSVButton.setHoverImage("Images/Load_CSV_Hover.png");
		loadCSVButton.setPressImage("Images/Load_CSV_Pressed.png");

		loadPDFButton = new ImageButton("Load as PDF");
		loadPDFButton.setDefaultImage("Images/Load_PDF.png");
		loadPDFButton.setHoverImage("Images/Load_PDF_Hover.png");
		loadPDFButton.setPressImage("Images/Load_PDF_Pressed.png");
		
		// Create status label
		statusLabel = new JLabel("");
		statusLabel.setFont(new Font("Gill Sans", Font.BOLD, 40));
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setForeground(new Color(255,255,255));
		
		// Set pref size
		loadCSVButton.setPreferredSize(new Dimension(200, 200));
		loadPDFButton.setPreferredSize(new Dimension(200, 200));
		
		// Add event handler to buttons
		loadCSVButton.addActionListener(new EventHandler().new eLoadCSVButton());
		loadPDFButton.addActionListener(new EventHandler().new eLoadPDFButton());

		// Create constraints
		GridBagConstraints c = new GridBagConstraints();

		// Create layout
		this.setLayout(new GridBagLayout());
		
		c.insets = new Insets(0,0,200,0);
		c.gridx = 0; c.gridy = 0;  c.gridwidth = 2; 
		this.add(this.statusLabel, c);
		
		c.insets = new Insets(0,0,0,0);
		c.gridx = 0; c.gridy = 1; c.gridwidth = 1; 
		this.add(this.loadCSVButton, c);
		
		c.insets = new Insets(0,150,0,0);
		c.gridx = 1; c.gridy = 1;
		this.add(this.loadPDFButton, c);
		
		// Add event handler to panel
		this.addComponentListener(new EventHandler().new eLoadGUI());
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