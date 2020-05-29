package ballworlds;

import java.awt.Color;
import java.awt.geom.Point2D;
import util.Random;

public class Mover extends Ball {

	private Color color;
	private int size;
	private int vX;
	private int vY;

	public Mover(BallEnvironment world) {
		super(world);
		this.setCenterPoint(world.getCenterPoint());
		this.size = 25;
		this.color = new Color(233, 133, 144);
		this.vX = Random.randRange(-5, 5);
		this.vY = Random.randRange(-5, 5);

	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public void updatePosition() {
		if (this.getIsPaused()) {
			double x = getCenterPoint().getX();
			double y = getCenterPoint().getY();
			this.setCenterPoint(new Point2D.Double(x, y));
		} else {
			double x = getCenterPoint().getX() + this.vX;
			double y = getCenterPoint().getY() + this.vY;
			this.setCenterPoint(new Point2D.Double(x, y));

		}
	}

	@Override
	public void updateSize() {
	}

	@Override
	public void updateColor() {
	}

	@Override
	public double getDiameter() {
		return this.size;
	}

}
