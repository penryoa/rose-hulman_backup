package ballworlds;

import java.awt.Color;
import java.awt.geom.Point2D;

import util.Random;

public class Exploder extends AbstractBouncer {

	private Color color;
	private double size = 5.0;
	private boolean isMaxSize;
	private double maxSize;

	public Exploder(BallEnvironment world) {
		super(world);
		this.setCenterPoint(world.getCenterPoint());
		this.color = new Color(59, 243, 243);
		this.maxSize = Random.randRange(2, 10) * this.size;

	}

	public Exploder(BallEnvironment world, Point2D inPoint) {
		super(world);
		this.setCenterPoint(inPoint);
		this.color = new Color(59, 243, 243);
		this.maxSize = Random.randRange(2, 10) * this.size;
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public void updateSize() {
		if (this.getIsPaused()) {
			this.size += 0.0;
		} else {
			if (this.size == this.maxSize) {
				this.isMaxSize = true;
			}
			if (!this.isMaxSize) {
				this.size += 0.25;
			} else {
				this.die();
				Point2D deathPoint = super.getCenterPoint();
				Exploder exp1 = new Exploder(this.getWorld(), deathPoint);
				Exploder exp2 = new Exploder(this.getWorld(), deathPoint);
				this.getWorld().addBall(exp1);
				this.getWorld().addBall(exp2);
				this.isMaxSize = false;
			}
		}

	}

	@Override
	public double getDiameter() {
		return this.size;
	}

	@Override
	public void die() {
		this.getWorld().removeBall(this);
	}

	@Override
	public void updateColor() {
	}

}
