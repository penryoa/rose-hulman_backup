package spaceships;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * 
 * When a MovingSpaceship is told to move, it should not
 * change it's x and y to the target position immediately.  
 * Instead it should slowly adjust its x and y towards the 
 * target position.  That way the ship appears to animate.
 * 
 * Feel free to modify anything you wish about this class.
 * Add fields, modify any of the functions, add your own
 * functions, etc.
 * 
 * @author TODO <YOUR_NAME_GOES_HERE>
 *
 */
public class MovingSpaceship implements Spaceship {

	private static final int WIDTH=60;
	private static final int HEIGHT=60;
	private static final int MAX_SPEED=6;
	
	private int x, y;
	
	private int destinationX, destinationY;
	
	/**
	 * Constructs a MovingSpaceship at the specified location.
	 *
	 * @param startingX
	 * @param startingY
	 */
	public MovingSpaceship(int startingX, int startingY) {
		this.x = startingX;
		this.y = startingY;
		this.destinationX = startingX;
		this.destinationY = startingY;
	}
	
	/**
	 * Called to draw the spaceship.
	 */
	@Override
	public void drawOn(Graphics2D g) {
		int deltaX = this.destinationX - this.x;
		int deltaY = this.destinationY - this.y;
		
		deltaX = Math.min(deltaX, MAX_SPEED);
		deltaY = Math.min(deltaY, MAX_SPEED);
		deltaX = Math.max(deltaX, -MAX_SPEED);
		deltaY = Math.max(deltaY, -MAX_SPEED);
		
		this.x = this.x + deltaX;
		this.y = this.y + deltaY;
		
		g.setColor(Color.ORANGE);
		g.fillOval(this.x - WIDTH/2, this.y - WIDTH/2, WIDTH, HEIGHT);
	}
	
	/**
	 * Should be called when a new destination is set for
	 * the moving spaceship.
	 */
	@Override
	public void moveTo(int newX, int newY) {
		this.destinationX = newX;
		this.destinationY = newY;
	}

}
