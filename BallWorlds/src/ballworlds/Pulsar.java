package ballworlds;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.Random;

public class Pulsar extends Ball {

	private final int size = 25;
	private Color color;
	private boolean isWhite;

	public Pulsar(BallEnvironment world) {
		super(world);
		this.color = new Color(0, 0, 0);
		Random r = new Random();
		double x = r.nextDouble() * world.getSize().getWidth();
		double y = r.nextDouble() * world.getSize().getHeight();
		this.setCenterPoint(new Point2D.Double(x, y));
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public void updatePosition() {
	}

	@Override
	public void updateSize() {
	}

	@Override
	public void updateColor() {
		if (this.getIsPaused()) {
			this.color = new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
		} else {
			if (this.color.equals(Color.white)) {
				this.isWhite = true;
			}
			if (this.color.equals(Color.black)) {
				this.isWhite = false;
			}
			if (this.isWhite == false) {
				this.color = new Color(this.color.getRed() + 5, this.color.getGreen() + 5, this.color.getBlue() + 5);
			}
			if (this.isWhite == true) {
				this.color = new Color(this.color.getRed() - 5, this.color.getGreen() - 5, this.color.getBlue() - 5);
			}
		}
	}

	@Override
	public double getDiameter() {
		return this.size;
	}

}
