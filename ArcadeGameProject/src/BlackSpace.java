import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 * BlackSpaces are used as the tunnels that the hero creates as he digs. The
 * monsters can only move within these BlackSpaces, unless in ghost form.
 * 
 * All the methods in this class are self-explanatory.
 * 
 * @author penryoa
 *
 */
public class BlackSpace {

	// ======= Important Objects ======= \\
	public Point centerPoint;
	private BufferedImage image;

	// ======= BlackSpace Constructor ======= \\

	public BlackSpace(Point initialPosition) {
		this.centerPoint = initialPosition;
		try {
			this.image = ImageIO.read(new File("black.png"));
		} catch (IOException e) {
			throw new RuntimeException("Error drawing blackSpcace: " + e);
		}
	}

	// ======= Drawable Methods ======= \\
	public Point2D getCenterPoint() {
		return this.centerPoint;
	}

	public void drawCentered(Graphics2D g2) {
		g2 = (Graphics2D) g2.create();
		g2.translate(centerPoint.x - image.getWidth() / 2, centerPoint.y - image.getHeight() / 2);
		g2.drawImage(image, 0, 0, null);
	}
}
