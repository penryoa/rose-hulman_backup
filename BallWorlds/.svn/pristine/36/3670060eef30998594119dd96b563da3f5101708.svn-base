package ballworlds;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.Random;

public class Rush extends AbstractBouncer {

	private double size;
	private Color color;
	private double frameHeight;
	private double frameWidth;
	private double vX;
	private double vY;
	private double centerX;
	private double centerY;
	private double maxVelocity;

	/**
	 * This creates a bouncer that rushes around the screen, changing the color
	 * and getting faster. It starts at a random location, and once it grows too
	 * much, it bursts into four new Rush balls.
	 * 
	 * Like a ball on a sugar rush, hence the name Rush.
	 * 
	 * Another potential class name was Skittles, because with all the random
	 * colors, you can really taste the rainbow. Rush seemed cooler, though.
	 * 
	 * Worked with Anna Thompson.
	 */

	public Rush(BallEnvironment world) {
		super(world);
		this.color = new Color(255, 255, 255);
		this.size = 15;
		this.frameHeight = world.getSize().getHeight();
		this.frameWidth = world.getSize().getWidth();
		Random r = new Random();
		this.vX = r.nextDouble();
		this.vY = r.nextDouble();
		this.centerX = this.frameWidth * r.nextDouble();
		this.centerY = this.frameHeight * r.nextDouble();
		this.setCenterPoint(new Point2D.Double(centerX, centerY));
		this.maxVelocity = 20;
	}

	public Rush(BallEnvironment world, Point2D deathPoint) {
		super(world);
		this.color = new Color(255, 255, 255);
		this.size = 15;
		this.frameHeight = world.getSize().getHeight();
		this.frameWidth = world.getSize().getWidth();
		Random r = new Random();
		this.vX = r.nextDouble();
		this.vY = r.nextDouble();
		this.setCenterPoint(deathPoint);
		this.maxVelocity = 10;
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public void updateSize() {
	}

	@Override
	public void updatePosition() {
		if (this.getIsPaused()) {
			double x = this.getCenterPoint().getX();
			double y = this.getCenterPoint().getY();
			this.setCenterPoint(new Point2D.Double(x, y));
		} else {
			double x = this.getCenterPoint().getX() + this.vX;
			double y = this.getCenterPoint().getY() + this.vY;
			this.setCenterPoint(new Point2D.Double(x, y));

			if (Math.abs(this.vX) > this.maxVelocity || Math.abs(this.vY) > this.maxVelocity) {
				this.die();
				Point2D deathPoint = super.getCenterPoint();
				Rush rush1 = new Rush(this.getWorld(), deathPoint);
				this.getWorld().addBall(rush1);
				Rush rush2 = new Rush(this.getWorld(), deathPoint);
				this.getWorld().addBall(rush2);
				Rush rush3 = new Rush(this.getWorld(), deathPoint);
				this.getWorld().addBall(rush3);
				Rush rush4 = new Rush(this.getWorld(), deathPoint);
				this.getWorld().addBall(rush4);
			}

			if (x >= this.frameWidth || x <= 0.0) {
				this.vX = -this.vX;
			}
			if (y >= this.frameHeight || y <= 0.0) {
				this.vY = -this.vY;
			}

			this.vX *= 1.01;
			this.vY *= 1.01;
		}

	}

	@Override
	public void updateColor() {
		if (!this.getIsPaused()) {
			Random r = new Random();
			this.color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		}
	}
}
