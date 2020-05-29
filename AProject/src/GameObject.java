import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

// TODO: Refactor Raindrop, Platform, (and hopefully that lone box?) into GameObjects.
public abstract class GameObject implements Temporal, Drawable {
	private Point2D centerPoint;
	private World world;
	private String name;
	private Dimension dimension;
	

	// updatePosition, fall, ... can all be consolidated into an update method.
	public GameObject(Point2D center, World world) {
		this.centerPoint = center;
		this.world = world;
		this.dimension = new Dimension(40, 40);
		
	}

	@Override
	public Point2D getCenterPoint() {
		return this.centerPoint;
	}
	
	public void setCenterPoint(Point2D point) {
		this.centerPoint = point;
	}

	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub.
		String pictureName = this.name + ".png";
		try {
			BufferedImage picture = ImageIO.read(new File(pictureName));
			return picture;
		} catch (IOException e) {
			throw new RuntimeException("Error drawing Object: " + e);
		}
	}

	@Override
	public Dimension getDimension() {
		// TODO Auto-generated method stub.
		return this.dimension;
	}

	public void setDimension(int dimenx, int dimeny) {
		this.dimension = new Dimension(50, 60);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void collideWith(GameObject other) {

	}

	// Subclass-specific collision methods.
	// A RainDrop might not care that it has collided with a platform,
	// so it might call:
	// other.collideWithDrop(this)
	// to let the platform absorb the raindrop.
	public void collideWithHero(Hero hero) {
		double x1 = this.getCenterPoint().getX();
		double y1 = this.getCenterPoint().getY();
		double x2 = hero.getCenterPoint().getX();
		double y2 = hero.getCenterPoint().getY();
		if (Math.abs(x2 - x1) < 40 && Math.abs(y2 - y1) < 40) {
			hero.die();;
		}
	}

	// Beware infinite loops: someone must eventually "handle" the collision.
	public void collideWithMonster(Monster monster) {

	}

	// FIXME: are we STILL using Rectangle2D to represent the user's box?
	public void collideWithRock(Rock rock) {
		
	}

}