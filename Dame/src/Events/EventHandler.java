//###########################################################
//## Package

package Events;

import java.awt.Color;

//###########################################################
//## Imports

import java.awt.Component;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

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
				GameGUI board = (GameGUI) component;
				board.getGameboard().createField();
				board.fitComponents();
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
		public void componentShown(ComponentEvent e) {
			Component component = e.getComponent();
			if(component instanceof Board){
				Board board = (Board) component;
			}
		}
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
}