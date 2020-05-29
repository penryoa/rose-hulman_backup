import java.awt.Color;
import java.awt.Graphics2D;

public class PineTrees {

	private int width;
	private int height;
	private Color green;
	private Color brown;
	private int x;
	private int y;

	public PineTrees(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.green = new Color(40, 135, 22);
		this.brown = new Color(145, 112, 33);
	}
	
	public void drawOn(Graphics2D g2) {
		
		g2.setColor(brown);
		g2.fillRect(this.x + width/3, this.y + 2*height/3, width/3, height/3);
		
		g2.setColor(green);
		int[] xPoints = new int[3];
		xPoints[0] = this.x;
		xPoints[1] = this.x + width/2;
		xPoints[2] = this.x + width;
		
		int[] yPoints = new int[3];
		yPoints[0] = this.y + 2*height/3;
		yPoints[1] = this.y;
		yPoints[2] = this.y + 2*height/3;
		
		g2.fillPolygon(xPoints, yPoints, 3);
		
	}

	
	
}
