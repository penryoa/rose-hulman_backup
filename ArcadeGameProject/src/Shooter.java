
import java.awt.Point;
 
/**
 * Shooter is the hero's weapon. It has a small range.
 * 
 * @author penryoa
 *
 */


public class Shooter extends GameObject {

	// ======= Important Objects ======= \\
	public int dx;
	public int dy;

	// ======= Shooter Constructor ======= \\

	public Shooter(Point centerPoint, World world, String name) {
		super(centerPoint, world, name);
	}

	public void move() {
		for(int i=0;i<this.world.monsters.size();i++) {
			this.collideWithMonster(this.world.monsters.get(i));
		}
	}

	@Override
	public void timePassed() {
	}
	
	@Override
	public void collideWithMonster(Monster monster) {
		double x1 = this.getCenterPoint().getX();
		double y1 = this.getCenterPoint().getY();
		double x2 = monster.getCenterPoint().getX();
		double y2 = monster.getCenterPoint().getY();
		if (Math.abs(x2 - x1) < 40 && Math.abs(y2 - y1) < 40) {
			monster.die();
		}
	}

	@Override
	public void die() {
	}

	@Override
	public void setIsPaused(boolean isPaused) {
	}

	@Override
	public boolean getIsPaused() {
		return false;
	}
}
