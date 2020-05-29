import java.awt.Point;

/**
 * Fire works just like shooter does. Fygar is the only enemy that can use Fire.
 * 
 * @author penryoa
 *
 */

public class Fire extends Shooter {

	// ======= Shooter Constructor ======= \\

	public Fire(Point centerPoint, World world, String name) {
		super(centerPoint, world, name);
	}

	@Override
	public void collideWithMonster(Monster monster) {
	}

	@Override
	public void collideWithHero(Hero hero) {
		double x1 = this.getCenterPoint().getX();
		double y1 = this.getCenterPoint().getY();
		double x2 = hero.getCenterPoint().getX();
		double y2 = hero.getCenterPoint().getY();
		if (Math.abs(x2 - x1) < 40 && Math.abs(y2 - y1) < 40) {
			hero.die();
		}
	}
}
