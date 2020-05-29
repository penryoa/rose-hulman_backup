package spaceships;

import java.awt.Graphics2D;

@SuppressWarnings("javadoc")
public interface Spaceship {
	public void drawOn(Graphics2D g);
	public void moveTo(int newX, int newY);
}
