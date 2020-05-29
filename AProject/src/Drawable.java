import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public interface Drawable {
	BufferedImage getImage();

	Dimension getDimension();

	Point2D getCenterPoint();
}
