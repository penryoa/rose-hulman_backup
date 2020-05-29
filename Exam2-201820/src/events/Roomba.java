package events;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Roomba {
	
	public Point position;
	
	private CurieCat cat;
	private BufferedImage roombaImage;
	private CatComponent c;
	
	public Roomba(Point initialPosition, CatComponent c){
		this.position = initialPosition;
		this.cat = new CurieCat();
		this.c = c;
		try {
			this.roombaImage = ImageIO.read(new File("roomba.png"));
		} catch (IOException e) {
			throw new RuntimeException("Error drawing Roomba: "+e);
		}
	}	
	
	public void drawRoombaAndCat(Graphics2D g2){
		g2 = (Graphics2D) g2.create();
		g2.translate(position.x, position.y);
		this.cat.drawCentered(g2);
		g2.drawImage(roombaImage, 0, 0, null);
	}
	
	public void moveBy(int dx, int dy){
//		c.addLine(this.position.x, this.position.y, this.position.x + dx, this.position.y + dy);
		this.position = new Point(position.x + dx, position.y + dy);
	}
}
