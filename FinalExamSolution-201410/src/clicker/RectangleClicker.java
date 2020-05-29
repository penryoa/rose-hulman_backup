package clicker;

import java.awt.Graphics2D;

public class RectangleClicker extends Clicker {

	int x;
	int y;
	int width;
	int height;
	
	public RectangleClicker(int upperLeftX, int upperLeftY, int width, int height)
	{
		this.x = upperLeftX;
		this.y = upperLeftY;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void draw(Graphics2D g2) {
		g2.fillRect(this.x, this.y, this.width, this.height);
	}

	@Override
	public void detectClick(int x, int y) {
		if(x < this.x || x > this.x + width)
			return;
		if(y < this.y || y > this.y + height)
			return;
		
		System.out.println("You clicked in the Rectangle clicker");
		
	}

}
