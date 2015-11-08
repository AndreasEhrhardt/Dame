package Events;

import java.awt.Component;
import java.awt.event.*;
import java.util.*;

import GUI.*;

public class EventHandler{
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
				
			}
		}
	}
}
