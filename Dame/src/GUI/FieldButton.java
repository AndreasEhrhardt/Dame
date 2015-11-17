package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JButton;

import Enumerations.FarbEnum;
import GameLogic.Spiel;
import GameLogic.Spielfigur;

public class FieldButton extends JButton {

	int x, y;
	boolean selected = false;

	public FieldButton(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point getPosition(){
		return new Point(x,y);
	}
	
	public void setSelected(boolean state){		
		this.selected = state;
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// Use better paint class
		Graphics2D g2D = (Graphics2D) g;
		
		// Render hints
		g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// Draw background
		Color background;
		if ((y + x) % 2 == 0) background = Color.gray;
		else  background = Color.white;
		g2D.setColor(background);
		g2D.fillRect(0, 0, this.getWidth(), this.getWidth());
		
		// Draw figure
		Spiel game = MainFrame.globalPointer.getGame();
		Spielfigur figure = game.getGameboard().getField(x, y).getFigure();
		if (figure != null){		
			if(selected){
				g2D.setColor(new Color(100,0,0));
				g2D.fillRect(0, 0, this.getWidth(), this.getHeight());
			}
			
			if(figure.isDame()){
				g2D.setColor(new Color(255,215,0));
				g2D.drawRect(0, 0, this.getWidth(), this.getHeight());
			}
			
			Color figureColor;
			Color figureColorBorder;
			if(figure.getColor() == FarbEnum.schwarz){
				figureColor = new Color(0,0,0);
				figureColorBorder = new Color(255,255,255);
			}
			else{
				figureColor = new Color(255,255,255);
				figureColorBorder = new Color(0,0,0);
			}
			
			g2D.setColor(figureColorBorder);
			g2D.fillOval(5, 5, this.getWidth() - 10, this.getHeight() - 10);
			g2D.setColor(figureColor);
			g2D.fillOval(10, 10, this.getWidth() - 20, this.getHeight() - 20);
		}
	}
	
	public Point getButtonID() {
		return new Point(this.x, this.y);
	}
}
