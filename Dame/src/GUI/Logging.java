package GUI;

import java.awt.GridLayout;

import javax.swing.JTextField;


	public class Logging extends MainPanelComponent{

		private JTextField log;	
		
		public Logging(MainPanel mf){
			super(mf);
			this.setLayout(new GridLayout(1,1));
			log= new JTextField();
			this.add(log);
		}
		public JTextField getJTextField(){
			return log;
		}
	}
