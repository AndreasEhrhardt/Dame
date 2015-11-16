package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JButton;

import Enumerations.FarbEnum;
import GameLogic.Spiel;

public class FieldButton extends JButton {

	int x;
	int y;
	private Spiel game = MainFrame.globalPointer.getGame();

	public FieldButton(int x, int y) {

		this.x = x;
		this.y = y;

	}

	@Override
	protected void paintComponent(Graphics g) {
	
		if ((y % 2 == 0 || !(x % 2 == 0)) && ((y % 2 == 0 || !(x % 2 == 0)))) {
			this.setBackground(Color.white);
			this.setEnabled(false);
		} else {
			this.setBackground(Color.gray);
		}
		//Kreis parameter??
		if (game.getGameboard().getField(x, y).getFigure() != null
				&& game.getGameboard().getField(x, y).getFigure().getColor().equals(FarbEnum.schwarz)
				&& game.getGameboard().getField(x, y).getFigure().isDame() != true) {
			g.drawOval(0, 0, this.getHeight(), this.getHeight());
			g.setColor(Color.BLACK);
		} else if (game.getGameboard().getField(x, y).getFigure() != null
				&& game.getGameboard().getField(x, y).getFigure().getColor().equals(FarbEnum.schwarz)
				&& game.getGameboard().getField(x, y).getFigure().isDame() == true) {
			g.drawOval(0, 0, this.getHeight(), this.getHeight());
			g.setColor(Color.BLACK);
			this.setBackground(Color.YELLOW);
		} else if (game.getGameboard().getField(x, y).getFigure() != null
				&& game.getGameboard().getField(x, y).getFigure().getColor().equals(FarbEnum.weiß)
				&& game.getGameboard().getField(x, y).getFigure().isDame() != true) {
			g.drawOval(0, 0, this.getHeight(), this.getHeight());
			g.setColor(Color.WHITE);
		} else if (game.getGameboard().getField(x, y).getFigure() != null
				&& game.getGameboard().getField(x, y).getFigure().getColor().equals(FarbEnum.weiß)
				&& game.getGameboard().getField(x, y).getFigure().isDame() == true) {
			g.drawOval(0, 0, this.getHeight(), this.getHeight());
			g.setColor(Color.WHITE);
			this.setBackground(Color.YELLOW);
		}
	}
	
	public Point getButtonId() {
		return new Point(this.x, this.y);
	}
}
