import java.awt.Point;

/**
 * Creeper is a unique enemy that explodes and creates black spaces surrounding
 * it if the hero gets too close.
 * 
 * @author penryoa
 *
 */

public class Creeper extends Monster {

	// ======= Creeper Constructor ======= \\
	public Creeper(Point centerPoint, World world, String name) {
		super(centerPoint, world, name);
	}

	@Override
	public void timePassed() {
		double x1 = this.getCenterPoint().getX();
		double y1 = this.getCenterPoint().getY();
		double x2 = this.world.hero.getCenterPoint().getX();
		double y2 = this.world.hero.getCenterPoint().getY();
		if (Math.abs(x2 - x1) < 50 && Math.abs(y2 - y1) < 50) {
			this.die();
		}

		if (this.isDying == false) {
			Point leftPt = new Point(this.centerPoint.x + 10, this.centerPoint.y);
			Point downPt = new Point(this.centerPoint.x, this.centerPoint.y + 10);
			Point rightPt = new Point(this.centerPoint.x - 10, this.centerPoint.y);
			Point upPt = new Point(this.centerPoint.x, this.centerPoint.y - 10);

			if (this.hit == false) {
				if (this.hit == false) {
					if (left == false && this.blackSpaces.containsKey(leftPt)) {
						this.right = true;
						this.moveBy(10, 0);
						return;
					} else {
						this.right = false;
					}

					if (up == false && this.blackSpaces.containsKey(downPt)) {
						this.down = true;
						this.moveBy(0, 10);
						return;
					} else {
						this.down = false;
					}

					if (this.blackSpaces.containsKey(rightPt)) {
						this.left = true;
						this.moveBy(-10, 0);
						return;
					} else {
						this.left = false;
					}

					if (this.blackSpaces.containsKey(upPt)) {
						this.up = true;
						this.moveBy(0, -10);
						return;
					} else {
						this.up = false;
					}
				}
			}
			this.hit = false;
		} else {
			this.dyingCount++;
			if (this.dyingCount < 2) {
				this.name = "creepDying";
				this.image = this.getImage();
			} else if (this.dyingCount < 4) {
				this.name = "creep";
				this.image = this.getImage();
			} else {
				this.setExplosion();
				if (Math.abs(x2 - x1) < 110 && Math.abs(y2 - y1) < 110) {
					this.world.hero.die();
				}
			}
		}
	}

	@Override
	public void die() {
		this.isDying = true;
	}

	public void setExplosion() {
		this.world.monsters.remove(this);
		this.world.addToScore(300);
		this.world.addBlackSpaces(this.centerPoint);
		this.world.addBlackSpaces(new Point(this.centerPoint.x + 40, this.centerPoint.y));
		this.world.addBlackSpaces(new Point(this.centerPoint.x - 40, this.centerPoint.y));
		this.world.addBlackSpaces(new Point(this.centerPoint.x, this.centerPoint.y + 40));
		this.world.addBlackSpaces(new Point(this.centerPoint.x, this.centerPoint.y - 40));
		this.world.addBlackSpaces(new Point(this.centerPoint.x - 40, this.centerPoint.y - 40));
		this.world.addBlackSpaces(new Point(this.centerPoint.x + 40, this.centerPoint.y + 40));
		this.world.addBlackSpaces(new Point(this.centerPoint.x + 40, this.centerPoint.y - 40));
		this.world.addBlackSpaces(new Point(this.centerPoint.x - 40, this.centerPoint.y + 40));
		this.world.addBlackSpaces(new Point(this.centerPoint.x, this.centerPoint.y - 80));
		this.world.addBlackSpaces(new Point(this.centerPoint.x, this.centerPoint.y + 80));
		this.world.addBlackSpaces(new Point(this.centerPoint.x - 80, this.centerPoint.y));
		this.world.addBlackSpaces(new Point(this.centerPoint.x + 80, this.centerPoint.y));

	}

	@Override
	public void setIsPaused(boolean isPaused) {
	}

	@Override
	public boolean getIsPaused() {
		return false;
	}
}
