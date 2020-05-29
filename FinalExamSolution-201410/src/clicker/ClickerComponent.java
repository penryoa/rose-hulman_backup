package clicker;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 * 
 * A general Java Swing component to specify cardinal directions (e.g. North, South, East, West)
 * 
 * This code already handles figuring out which direction you clicked.  You should interact with it
 * using addCompassEventListener, similar to the way you use addActionListener in JButton.
 * 
 * IF YOU WANT MOST OF THE CREDIT ON THE EXAM2 QUESTION, DO NOT MODIFY THIS CLASS IN ANY WAY.
 * 
 * @author Michael Hewner
 *
 */
public class ClickerComponent extends JComponent implements MouseListener {
	
	public ArrayList<Clicker> clickers;
	
	public ClickerComponent()
	{
		this.addMouseListener(this);
		this.clickers = new ArrayList<Clicker>();
		
		this.clickers.add(new RectangleClicker(200,200,300,200));
		this.clickers.add(new RectangleClicker(600,400,150,150));
		this.clickers.add(new CircleClicker(0,0,200));		
		this.clickers.add(new CircleClicker(650,200,75));
	}
	
		
	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.ORANGE);
		
		for(Clicker c : this.clickers) {
			c.draw(g2);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point clickPosition = e.getPoint();
		for(Clicker c : this.clickers) {
			c.detectClick(clickPosition.x, clickPosition.y);
		}
		// TODO: add code to invoke clicking here
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
}
