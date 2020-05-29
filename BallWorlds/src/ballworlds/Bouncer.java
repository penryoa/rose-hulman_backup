package ballworlds;

import java.awt.Color;

import util.Random;

public class Bouncer extends AbstractBouncer {

	private Color color;
	private int size;
	double height;
	double width;

	public Bouncer(BallEnvironment world) {
		super(world);
		this.setCenterPoint(world.getCenterPoint());
		this.size = 25;
		this.color = new Color(0, 222, 144);
		this.height = world.getSize().getHeight();
		this.width = world.getSize().getWidth();
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public void updatePosition() {
		super.updatePosition();
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
