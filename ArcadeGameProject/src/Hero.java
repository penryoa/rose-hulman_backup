import java.awt.Graphics2D;
import java.awt.Point;
import java.io.IOException;

/**
 * Hero is, well, the hero of the game. Hero can move with the up, down, left,
 * and right keys and shoot with his shooter (space bar).
 * 
 * @author penryoa
 *
 */

public class Hero extends GameObject {

	// ======= Important Objects ======= \\
	public Shooter newShooter;
	public int livesLeft = 5;
	public boolean isPaused;
	protected boolean haveMasterSword = false;

	// ======= Hero Constructor ======= \\

	public Hero(Point initialPosition, World world, String name) {
		super(initialPosition, world, name);
		this.hit = false;
	}

	@Override
	public void moveBy(int dx, int dy) {
		if (this.isPaused == false) {
			if (this.haveMasterSword) {
				if (dx > 0) {
					this.name = "knight";
				} else if (dx < 0) {
					this.name = "knightLeft";
				} else if (dy > 0) {
					this.name = "knightDown";
				} else if (dy < 0) {
					this.name = "knightUp";
				}
			} else {
				if (dx > 0) {
					this.name = "hero";
				} else if (dx < 0) {
					this.name = "heroLeft";
				} else if (dy > 0) {
					this.name = "heroDown";
				} else if (dy < 0) {
					this.name = "heroUp";
				}
			}
			this.image = this.getImage();
			for (Rock r : this.world.rocks) {
				if (Math.abs(this.centerPoint.x + dx - r.getCenterPoint().x) < 40
						&& Math.abs(this.centerPoint.y + dy - r.getCenterPoint().y) < 40) {
					hit = true;
				}
			}
			for (int i = 0; i < this.world.monsters.size(); i++) {
				if (Math.abs(this.centerPoint.x - this.world.monsters.get(i).getCenterPoint().x) < 40
						&& Math.abs(this.centerPoint.y - this.world.monsters.get(i).getCenterPoint().y) < 40) {
					hit = true;
					this.die();
				}
			}
			if (this.hit == false && this.getCenterPoint().getX() + dx > 10 && this.getCenterPoint().getX() + dx < 470
					&& this.getCenterPoint().getY() + dy > 10 && this.getCenterPoint().getY() + dy < 470) {
				this.centerPoint = new Point(centerPoint.x + dx, centerPoint.y + dy);
				this.newShooter = new Shooter(new Point(this.centerPoint.x + 40, this.centerPoint.y), this.world,
						"shooter");
			}
			this.hit = false;
		}
	}

	// ======= Other Methods ======= \\

	public void shoot() {
		this.newShooter = null;
		if (this.haveMasterSword) {
			if (this.name == "knight") {
				this.newShooter = new ShockWave(new Point(this.centerPoint.x + 80, this.centerPoint.y), this.world,
						"shockWave");
			} else if (this.name == "knightLeft") {
				this.newShooter = new ShockWave(new Point(this.centerPoint.x - 80, this.centerPoint.y), this.world,
						"shockWave");
			} else if (this.name == "knightUp") {
				this.newShooter = new ShockWave(new Point(this.centerPoint.x, this.centerPoint.y - 80), this.world,
						"shockWave1");
			} else if (this.name == "knightDown") {
				this.newShooter = new ShockWave(new Point(this.centerPoint.x, this.centerPoint.y + 80), this.world,
						"shockWave1");
			}
		} else {
			if ((this.world.blackSpaces.containsKey(new Point(this.centerPoint.x + 40, this.centerPoint.y))
					|| this.world.blackSpaces.containsKey(new Point(this.centerPoint.x + 40, this.centerPoint.y + 10))
					|| this.world.blackSpaces.containsKey(new Point(this.centerPoint.x + 40, this.centerPoint.y - 10)))
					&& this.name == "hero") {
				this.newShooter = new Shooter(new Point(this.centerPoint.x + 40, this.centerPoint.y), this.world,
						"shooter");
			} else if ((this.world.blackSpaces.containsKey(new Point(this.centerPoint.x - 40, this.centerPoint.y))
					|| this.world.blackSpaces.containsKey(new Point(this.centerPoint.x - 40, this.centerPoint.y + 10))
					|| this.world.blackSpaces.containsKey(new Point(this.centerPoint.x - 40, this.centerPoint.y - 10)))
					&& this.name == "heroLeft") {
				this.newShooter = new Shooter(new Point(this.centerPoint.x - 40, this.centerPoint.y), this.world,
						"shooter");
			} else if ((this.world.blackSpaces.containsKey(new Point(this.centerPoint.x, this.centerPoint.y - 40))
					|| this.world.blackSpaces.containsKey(new Point(this.centerPoint.x + 10, this.centerPoint.y - 40))
					|| this.world.blackSpaces.containsKey(new Point(this.centerPoint.x - 10, this.centerPoint.y - 40)))
					&& this.name == "heroUp") {
				this.newShooter = new Shooter(new Point(this.centerPoint.x, this.centerPoint.y - 40), this.world,
						"shooter1");
			} else if ((this.world.blackSpaces.containsKey(new Point(this.centerPoint.x, this.centerPoint.y + 40))
					|| this.world.blackSpaces.containsKey(new Point(this.centerPoint.x + 10, this.centerPoint.y + 40))
					|| this.world.blackSpaces.containsKey(new Point(this.centerPoint.x - 10, this.centerPoint.y + 40)))
					&& this.name == "heroDown") {
				this.newShooter = new Shooter(new Point(this.centerPoint.x, this.centerPoint.y + 40), this.world,
						"shooter1");
			}
		}
		if (this.newShooter != null) {
			this.newShooter.drawCentered((Graphics2D) this.world.c.getGraphics());
			this.newShooter.move();
		}
	}

	@Override
	public void timePassed() {
	}

	@Override
	public void die() {
		if (this.livesLeft > 0) {
			this.livesLeft--;
			this.world.setIsPaused(true);
			// System.out.println("You died! Press 'Enter' to continue.");
			this.world.setIsPaused(false);
			this.world.changeLevel(0);
		} else if (this.livesLeft == 0) {
			System.out.println("Game Over!");
			this.world.setIsPaused(true);
			try {
				this.world.setToLevel(1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void setIsPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	@Override
	public boolean getIsPaused() {
		return this.isPaused;
	}

	public void getMasterSword() {
		this.haveMasterSword = true;
	}
}
