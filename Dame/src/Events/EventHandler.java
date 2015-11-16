//###########################################################
//## Package

package Events;

//###########################################################
//## Imports

import java.awt.Component;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import GUI.*;
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
				if(!serial.haveSaveGame()) lm.getLoadingSerializeButton().setDisabled(true);;
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
	/*public class felderEingabe implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() instanceof Control){
			 		MainFrame.globalPointer.start
			}
		}
	}
	*/
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

	public ComponentListener eMoveFigureBoard() {
		// TODO Auto-generated method stub
		return null;
	}
}