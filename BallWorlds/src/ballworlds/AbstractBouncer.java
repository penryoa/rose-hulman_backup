package ballworlds;

import java.awt.geom.Point2D;
import util.Random;

/**
 * A ball that bounces off the walls.
 * 
 * @author Curt Clifton. Created Jan 22, 2011.
 */
public abstract class AbstractBouncer extends Ball {

	private int vX;
	private int vY;
	private double height;
	private double width;
	private double size;

	/**
	 * Constructs a abstract bouncer in the given world.
	 * 
	 * @param world
	 */
	public AbstractBouncer(BallEnvironment world) {
		super(world);
		this.height = world.getSize().getHeight();
		this.width = world.getSize().getWidth();
		this.setCenterPoint(world.getCenterPoint());
		this.vX = Random.randRange(-3, 3);
		if (this.vX == 0) {
			this.vX = Random.randRange(-3, 3);
		}
		this.vY = Random.randRange(-3, 3);
		if (this.vY == 0) {
			this.vY = Random.randRange(-3, 3);
		}
		this.size = 25;

	}

	@Override
	public double getDiameter() {
		return this.size;
	}

	public void updatePosition() {

		if (this.getIsPaused()) {
			double x = this.getCenterPoint().getX();
			double y = this.getCenterPoint().getY();
			this.setCenterPoint(new Point2D.Double(x, y));
		} else {
			double x = this.getCenterPoint().getX() + this.vX;
			double y = this.getCenterPoint().getY() + this.vY;
			this.setCenterPoint(new Point2D.Double(x, y));

			if (x >= this.width || x <= 0.0) {
				this.vX = -this.vX;
			}
			if (y >= this.height || y <= 0.0) {
				this.vY = -this.vY;
			}
		}

	}
}
