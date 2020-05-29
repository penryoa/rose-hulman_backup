
import java.awt.Point;
/*
 * rock is a abstract station gameobject, it can also kill others when drop.
 */
public abstract class Rock extends GameObject {

	private int timeCounter;
	private int moveCount;
	private boolean killM=false;
	private boolean killH=false;

	// ======= Rock Constructor ======= \\

	public Rock(Point initialPosition, World world, String name) {
		super(initialPosition, world, name);
		this.timeCounter = 0;
		this.moveCount = 0;
		this.hit = false;
		this.name=name;
		this.image = this.getImage();
	}
	
	@Override
	public void collideWithHero(Hero hero) {
		double x1 = this.getCenterPoint().getX();
		double y1 = this.getCenterPoint().getY();
		double x2 = hero.getCenterPoint().getX();
		double y2 = hero.getCenterPoint().getY();
		if (Math.abs(x2 - x1) < 40 && Math.abs(y2 - y1) < 40) {
			this.killH=true;
		}
	}
	
	@Override
	public void timePassed() {
		for(int i=0;i<this.world.rocks.size();i++) {
			if (this.centerPoint.x==this.world.rocks.get(i).centerPoint.x && this.centerPoint.y==this.world.rocks.get(i).centerPoint.y-40) {
				this.hit=true;
				break;
			}else {
				this.hit=false;
			}
		}
		if(this.hit == false) {
			if (this.blackSpaces.containsKey(new Point(this.getCenterPoint().x, this.getCenterPoint().y + 40))
					|| this.blackSpaces.containsKey(new Point(this.getCenterPoint().x, this.getCenterPoint().y + 30))
					|| this.blackSpaces.containsKey(new Point(this.getCenterPoint().x, this.getCenterPoint().y + 20))
					|| this.blackSpaces.containsKey(new Point(this.getCenterPoint().x, this.getCenterPoint().y + 10))) {
				this.timeCounter++;
				if (this.moveCount == 0) {
					this.moveCount++;
				}
				if (this.timeCounter > 3) {
					this.moveBy(0, 10);
					this.collideWithHero(this.world.hero);
					if(this.killH==true) {
						this.world.hero.die();
					}
					for (int i = 0; i < this.world.monsters.size(); i++) {
						Point pt = this.world.monsters.get(i).getCenterPoint();
						if (Math.abs(this.centerPoint.x - pt.x) < 40 && Math.abs(this.centerPoint.y - pt.y) < 40) {
							this.world.monsters.get(i).die();
						}
					}
				}
			}else {
				this.timeCounter=0;
			}
		}
		
		if (this.moveCount == 1) {
			this.world.rockFallCount++;
			this.moveCount = 2;
		}
		this.killH=false;
		this.killM=false;
	}
}