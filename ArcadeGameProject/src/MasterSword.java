import java.awt.Point;

/**
 * The MasterSword is found in the middle of the puzzle of Level 5. After
 * picking up the Master Sword, the hero gets some pretty sweet upgrades to his
 * shooter and appearance.
 * 
 * @author penryoa
 *
 */

public class MasterSword extends Fruit {

	public MasterSword(Point center, World world, String name) {
		super(center, world, name);
	}

	@Override
	public void collideWithHero(Hero hero) {
		double x1 = this.getCenterPoint().getX();
		double y1 = this.getCenterPoint().getY();
		double x2 = this.world.hero.getCenterPoint().getX();
		double y2 = this.world.hero.getCenterPoint().getY();
		if (Math.abs(x2 - x1) < 40 && Math.abs(y2 - y1) < 40) {
			this.world.fruits.remove(this);
			this.world.changeLevel(1);
			this.world.hero.getMasterSword();
			this.world.stop = true;
		}
	}
}
