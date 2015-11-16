package GUI;

import java.awt.BorderLayout;

public class GamePanel extends MainPanel{
	
	private Control control;
	private Logging logging;
	private Board board;
	
	public GamePanel(){
		this.setLayout(new BorderLayout());
		control = new Control();
		logging = new Logging();
		board = new Board();
		this.add(control,BorderLayout.EAST);
		this.add(logging,BorderLayout.SOUTH);
		this.add(board,BorderLayout.CENTER);
		
		}
}
