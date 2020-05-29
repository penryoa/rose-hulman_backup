import java.awt.Graphics2D;
import java.awt.Point;

public abstract class GameObject implements Drawable {

	// ======= Important Objects ======= \\

	private Point centerPoint;
	protected World world;

	// ======= GameObject Constructor ======= \\

	public GameObject(Point center, World world) {
		this.centerPoint = center;
		this.world = world;
	}

	// ======= Other Methods ======= \\
	
	public void drawOn() {
	}

	public void update() {
	}

	public boolean shouldRemove() {
		return false;
	}

	public void drawCentered(Graphics2D g2) {
	}

	public void onRemove() {

	}

	public void drawOn(Graphics2D g2) {

	}

	public void collideWith(GameObject other) {

	}

	public void collideWithHero(Hero hero) {

	}

	public void collideWithMonster(Monster monster) {
		// double x1 = this.getPosition().getX();
		// double y1 = this.getPosition().getY();
		// double x2 = hero.getCenterPoint().getX();
		// double y2 = hero.getCenterPoint().getY();
		// if(Math.abs(x2-x1)<40 && Math.abs(y2-y1)<40){
		// hero.die();
		// }
	}

	public void collideWithRock(Rock rock) {

	}

}