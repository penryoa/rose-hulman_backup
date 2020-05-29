import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class Pyramid {

	public static int width = 30;
	public static int height = 15;
	int x = 0;
	int y = 0;
	int x1;
	int y1;
	int xOriginal;
	int yOriginal;
	
	public Pyramid () {
		this.x = 0;
		this.y = 0;
		this.xOriginal = 0;
		this.yOriginal = 0;
	}
	
	
	public Pyramid (int x, int y) {
		this.x = x;
		this.y = y;
		this.xOriginal = x;
		this.yOriginal = y;

	}
	

	public void drawOneBlock(Graphics2D g2) {
		
		g2.setColor(Color.GREEN);
		g2.fillRect(this.x, this.y, width, height);
		g2.setColor(Color.BLACK);
		g2.drawRect(this.x, this.y, width, height);
	}
	
	public void drawOn(Graphics2D g2) {
		for (int k=0; k<5;k++) {
			for(int j=0; j<5-k; j++) {
				this.drawOneBlock(g2);
				this.x += width;
			}
			this.x = this.xOriginal + (k+1)*width/2;
			this.y += height;
		}
		this.x = this.xOriginal;
		this.y = this.yOriginal;
	}

	public void drawRotated(Graphics2D g2, double degrees) {
		for (int m=0; m<4; m++) {
			g2.rotate(degrees*Math.PI/180);
			this.drawOn(g2);
		}
	}
}
