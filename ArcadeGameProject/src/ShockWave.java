import java.awt.Point;

/**
 * After the hero collects the Master Sword, the hero's shooter upgrades to
 * ShockWave, a weapon with thrice the range of the original shooter!
 * 
 * @author penryoa
 *
 */

public class ShockWave extends Shooter {

	public ShockWave(Point centerPoint, World world, String name) {
		super(centerPoint, world, name);
	}

	@Override
	public void collideWithMonster(Monster monster) {
		double x1 = this.getCenterPoint().getX();
		double y1 = this.getCenterPoint().getY();
		double x2 = monster.getCenterPoint().getX();
		double y2 = monster.getCenterPoint().getY();
		if (Math.abs(x2 - x1) < 80 && Math.abs(y2 - y1) < 60) {
			monster.die();
		}
	}
}
