
import java.awt.Point;

/**
 * It wasn't until week 9 that we realized that the original game has an enemy
 * named "Pooka", not "Pookie", but we got very used to calling it "Pookie" and
 * left it. Pookie acts as a Pooka, where unlike Fygar, it cannot shoot fire.
 * 
 * @author penryoa
 *
 */

public class Pookie extends Monster {

	// ======= Pookie Constructor ======= \\

	public Pookie(Point centerPoint, World world, String name) {
		super(centerPoint, world, name);
	}

	@Override
	public void timePassed() {
		if (this.isDying == false) {
			if (this.hit == false) {
				this.timeCounter++;
				if (this.timeCounter > 30) {
					this.isGhost = true;
					this.name = "ghost";
					this.image = this.getImage();
				} else {
					this.isGhost = false;
					this.name = this.originalName;
					this.image = this.getImage();
				}

				Point leftPt = new Point(this.centerPoint.x + 10, this.centerPoint.y);
				Point downPt = new Point(this.centerPoint.x, this.centerPoint.y + 10);
				Point rightPt = new Point(this.centerPoint.x - 10, this.centerPoint.y);
				Point upPt = new Point(this.centerPoint.x, this.centerPoint.y - 10);

				if (this.isGhost == false) {
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
				} else {
					if (this.getCenterPoint().getX() - this.world.hero.getCenterPoint().getX() > 0) {
						this.moveBy(-10, 0);
					} else {
						this.moveBy(10, 0);
					}
					if (this.getCenterPoint().getY() - this.world.hero.getCenterPoint().getY() > 0) {
						this.moveBy(0, -10);
					} else {
						this.moveBy(0, 10);
					}
					if (this.blackSpaces.containsKey(this.getCenterPoint())) {
						this.timeCounter = 0;
					}
				}
			}
			this.hit = false;
		} else {
			this.dyingCount++;
			if (this.dyingCount < 2) {
				this.name = "pookieDying1";
				this.image = this.getImage();
			} else if (this.dyingCount < 4) {
				this.name = "pookieDying2";
				this.image = this.getImage();
			} else {
				this.world.monsters.remove(this);
				this.world.addToScore(300);
			}
		}
	}

	@Override
	public void setIsPaused(boolean isPaused) {
		// TODO Auto-generated method stub.

	}

	@Override
	public boolean getIsPaused() {
		// TODO Auto-generated method stub.
		return false;
	}
}
