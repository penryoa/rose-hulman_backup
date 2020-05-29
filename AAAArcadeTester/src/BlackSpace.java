
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BlackSpace {
	
	public Point position;
	
	private BufferedImage image;
	
	public BlackSpace(Point initialPosition){
		this.position = initialPosition;
		try {
			this.image = ImageIO.read(new File("black.png"));
		} catch (IOException e) {
			throw new RuntimeException("Error drawing blackSpcace: "+e);
		}
	}	
	
	public void drawCentered(Graphics2D g2) {
		g2.translate(-image.getWidth() / 2, -image.getHeight() / 2);
		g2.drawImage(image, 0, 0, null);
//		g2.translate(0, image.getHeight());
	}
	
	public void drawBlackSpace(Graphics2D g2){
		g2 = (Graphics2D) g2.create();
		g2.translate(position.x, position.y);
		this.drawCentered(g2);
	}
}

