//###########################################################
//## Package

package Events;

//###########################################################
//## Imports

import java.awt.Component;
import java.awt.event.*;
import java.util.*;

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

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Methods (Override)

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

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Methods (Override)

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

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Methods (Override)

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() instanceof ButtonBack){
				ButtonBack bb =  (ButtonBack) e.getSource();
				bb.back();
			}
		}
	}

	public class eShowLoadingMenuButton implements ActionListener{

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//++ Methods (Override)

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() instanceof ImageButton){
				ImageButton ib = (ImageButton) e.getSource();
				MainPanelComponent mpc = ib.getParent();
				MainPanel mp = mpc.getMainPanel();
				mp.showLoadingMenu();
			}
		}
	}
}