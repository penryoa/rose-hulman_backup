
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Monster extends GameObject implements Temporal{
	
	// ======= Important Objects ======= \\

	protected Point centerPoint;
	protected boolean isPaused = false;
	public HashMap<Point,BlackSpace> blackSpaces = new HashMap<>();
	protected BufferedImage image;
	protected boolean up = false;
	protected boolean down = false;
	protected boolean left = false;
	protected boolean right = false;
	
	// ======= Hero Constructor ======= \\
	
	public Monster(Point centerPoint, World world) {
		super(centerPoint, world);
		this.centerPoint = centerPoint;
	}
	
	// ======= Drawable Methods ======= \\

	public void moveBy(int dx, int dy) {
		this.centerPoint = new Point(centerPoint.x + dx, centerPoint.y + dy);
	}
	
	public void updatePosition(int dx, int dy) {
		this.centerPoint = new Point(centerPoint.x + dx, centerPoint.y + dy);
	}
	
	public Point getPosition() {
		return centerPoint;
	}

	public void setPosition(Point position) {
		this.centerPoint = position;
	}
	
	public void drawCentered(Graphics2D g2) {
		g2 = (Graphics2D) g2.create();
		g2.translate(this.centerPoint.x-image.getWidth() / 2, centerPoint.y-image.getHeight() / 2);
		g2.drawImage(image, 0, 0, null);
	}
	
	// ======= Other Methods ======= \\
	
	@Override
	public void timePassed() {
		// Random random = new Random();
		// int num = random.nextInt(100);
		Point leftPt = new Point(this.centerPoint.x + 10, this.centerPoint.y);
		Point downPt = new Point(this.centerPoint.x, this.centerPoint.y + 10);
		Point rightPt = new Point(this.centerPoint.x - 10, this.centerPoint.y);
		Point upPt = new Point(this.centerPoint.x, this.centerPoint.y - 10);
		
		
		if (left == false && this.blackSpaces.containsKey(leftPt)) {
			this.right = true;
			this.moveBy(10, 0);
			return;
		}else {
			this.right = false;
		}
		
		if (up == false && this.blackSpaces.containsKey(downPt)) {
			this.down = true;
			this.moveBy(0, 10);
			return;
		}else {
			this.down = false;
		}	
		
		if (this.blackSpaces.containsKey(rightPt)) {
			this.left = true;
			this.moveBy(-10, 0);
			return;
		}else {
			this.left = false;
		}
		
		if (this.blackSpaces.containsKey(upPt)) {
			this.up = true;
			this.moveBy(0, -10);
			return;
		}else {
			this.up = false;
		}
	}
	
	public void setBlackSpace(HashMap<Point, BlackSpace> blackSpaces) {
		this.blackSpaces = blackSpaces;
	}
	
	// ======= Temporal Methods ======= \\

	@Override
	public void die() {
	}

	@Override
	public void setIsPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	@Override
	public boolean getIsPaused() {
		return this.isPaused;
	}

	// ======= GameObject Methods ======= \\

	@Override
	public void update() {
	}

	@Override
	public void drawOn() {
	}

	@Override
	public boolean shouldRemove() {
		return false;
	}

	@Override
	public void onRemove() {
	}

	@Override
	public void drawOn(Graphics2D g2) {
	}

	@Override
	public void collideWith(GameObject other) {
	}

	@Override
	public void collideWithMonster(Monster monster) {
	}

	@Override
	public void collideWithRock(Rock rock) {
	}

	@Override
	public void collideWithHero(Hero hero) {
		hero.collideWithMonster(this);
	}
	
	public void collideWithShooter(Shooter shooter){
		this.die();
	}
}
