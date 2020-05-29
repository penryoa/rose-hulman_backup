
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Fygar extends Monster{
	
	Point centerPoint;
	
	// ======= Fygar Constructor ======= \\
	
	public Fygar(Point centerPoint, World world) {
		super(centerPoint,world);
		this.centerPoint = centerPoint;
		try {
			image = ImageIO.read(new File("fygar.png"));
		} catch (IOException e) {
			throw new RuntimeException("Error drawing fygar: " + e);
		}
	}
	
	// ======= Drawable Methods ======= \\

	@Override
	public void move(int dx, int dy) {
	}

	@Override
	public Point2D getCenterPoint() {
		return this.centerPoint;
	}
}
