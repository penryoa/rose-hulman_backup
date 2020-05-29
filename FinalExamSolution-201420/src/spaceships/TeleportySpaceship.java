package spaceships;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

/**
 * When told to move, the TeleportySpaceship
 * should immediately change its position
 * to the given location.
 * 
 * You may modify this class, but not too many
 * modifications should be necessary.
 * 
 * @author TODO <YOUR_NAME_GOES_HERE>
 *
 */
public class TeleportySpaceship implements Spaceship {

	private static final int WIDTH=50;
	private static final int HEIGHT=75;
	
	private int x, y;
	
	/**
	 * Constructs a TeleportySpaceship at the specified location.
	 *
	 * @param startingX
	 * @param startingY
	 */
	public TeleportySpaceship(int startingX, int startingY) {
		this.x = startingX;
		this.y = startingY;
	}
	
	/**
	 * Draw the spaceship at the current x and y
	 */
	@Override
	public void drawOn(Graphics2D g) {
		Polygon triangle = new Polygon();
		triangle.addPoint(this.x-WIDTH/2, this.y-HEIGHT/2);
		triangle.addPoint(this.x+WIDTH/2, this.y-HEIGHT/2);
		triangle.addPoint(this.x, this.y+HEIGHT/2);
		g.setColor(Color.GREEN);
		g.fill(triangle);
	}
	
	/**
	 * This function should be called when the spaceship
	 * is supposed to move.  Note, that calling this
	 * function does not directly trigger a repaint.
	 */
	@Override
	public void moveTo(int newX, int newY) {
		this.x = newX;
		this.y = newY;
	}
	
}
