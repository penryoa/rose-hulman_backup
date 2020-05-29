
import java.awt.Point;

/*
 * monster is an abstract class. Both Pookie and Fygar have similiar code thus
 * they extends monster. monster also override GameObject to calculate collision
 * with hero.
 */
public abstract class Monster extends GameObject {

	// ======= Important Objects ======= \\

	protected boolean isPaused = false;
	protected boolean up = false;
	protected boolean down = false;
	protected boolean left = false;
	protected boolean right = false;
	protected boolean isGhost = false;
	protected int hitPoint = 0;
	protected boolean isDying = false;
	protected int dyingCount = 0;
	protected int moveCount = 0;
	protected int timeCounter;
	protected String originalName;

	// ======= Monster Constructor ======= \\

	public Monster(Point centerPoint, World world, String name) {
		super(centerPoint, world, name);
		this.originalName = name;
		this.centerPoint = centerPoint;
		this.timeCounter = 0;
		this.hit = false;
	}

	// ======= Other Methods ======= \\

	public void timePassed() {
	}

	@Override
	public void die() {
		this.hit=true;
		this.hitPoint++;
		if(this.hitPoint>=3) {
			this.isDying=true;
		}
	}
	
	@Override
	public void collideWithHero(Hero hero) {
		double x1 = this.getCenterPoint().getX();
		double y1 = this.getCenterPoint().getY();
		double x2 = hero.getCenterPoint().getX();
		double y2 = hero.getCenterPoint().getY();
		if (Math.abs(x2 - x1) < 40 && Math.abs(y2 - y1) < 40) {
			this.hit = true;
			hero.die();
		}
	}

}
