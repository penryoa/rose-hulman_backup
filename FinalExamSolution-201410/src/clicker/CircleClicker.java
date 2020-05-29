package clicker;

import java.awt.Graphics2D;
import java.awt.Point;

public class CircleClicker extends Clicker {

	int centerX;
	int centerY;
	int radius;
	
	public CircleClicker(int centerX, int centerY, int radius)
	{
		// TODO add code here if you like
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
	}
	
	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		g2.fillOval(centerX-radius, centerY - radius, 2*radius, 2*radius);
	}

	@Override
	public void detectClick(int x, int y) {
		Point click = new Point(x,y);
		Point center = new Point(this.centerX, this.centerY);
		double distance = click.distance(center);
		if(distance < this.radius)
			System.out.println("You click on a circle");
		
	}

}
