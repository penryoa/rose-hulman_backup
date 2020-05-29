import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;


public class Fygar implements Temporal{
	private BufferedImage image;
	public Point position;
	private boolean isPaused = false;
	ArrayList<BlackSpace> blackSpaces;
	
	
	public Fygar(Point centerPoint) {
		this.position = centerPoint;
//		this.level = level;
		try {
			image = ImageIO.read(new File("fygar.png"));
		} catch (IOException e) {
			throw new RuntimeException("Error drawing fygar: " + e);
		}
	}
	
	public void moveBy(int dx, int dy) {
		this.position = new Point(position.x + dx, position.y + dy);
	}
	
	public void drawCentered(Graphics2D g2) {
		g2.translate(-image.getWidth() / 2, -image.getHeight() / 2);
		g2.drawImage(image, 0, 0, null);
//		g2.translate(0, image.getHeight());
	}
	
	public void drawFygar(Graphics2D g2) {
		g2 = (Graphics2D) g2.create();
		g2.translate(this.position.x, this.position.y);
		this.drawCentered(g2);
	}
	
	public void setBlackSpace(ArrayList<BlackSpace> blackSpaces) {
		this.blackSpaces = blackSpaces;
	}
	

	@Override
	public void timePassed() {
		Random random = new Random();
		int num = random.nextInt(100);
		
		for(int i=0;i<this.blackSpaces.size();i++) {
			if(this.position.x+10==this.blackSpaces.get(i).position.x && num >50) {
				this.moveBy(10, 0);
			} else if(this.position.x-10==this.blackSpaces.get(i).position.x && num<50) {
				this.moveBy(-10, 0);
			} else if(this.position.y+10==this.blackSpaces.get(i).position.y && num >50) {
				this.moveBy(0, 10);
			} else if(this.position.y-10==this.blackSpaces.get(i).position.y &&num<50) {
				this.moveBy(0, -10);
			}
		}
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIsPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	@Override
	public boolean getIsPaused() {
		return this.isPaused;
	}

}
