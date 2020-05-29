import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Monster extends GameObject{

	protected Point2D position;
	protected boolean isPaused = false;
	protected BufferedImage image;
	protected boolean up = false;
	protected boolean down = false;
	protected boolean left = false;
	protected boolean right = false;
	private String name;
	private World world;
	
	public Monster(Point2D centerPoint, World world) {
		super(centerPoint, world);
		this.position = centerPoint;
		this.name="pookie";
		this.world = world;
		this.setName("fygar");
	}
	
	public void setName(String name){
		this.name=name;
	}
	public void moveBy(int dx, int dy) {
		this.position = new Point2D.Double(position.getX() + dx, position.getY() + dy);
	}
	
	public void updatePosition(int dx, int dy) {
		this.position = new Point2D.Double(position.getX() + dx, position.getY() + dy);
	}
	
	public Point2D getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
	
	public void drawMonster(Graphics2D g2) {
		g2 = (Graphics2D) g2.create();
		g2.translate(this.position.getX(), this.position.getY());
		g2.translate(-image.getWidth() / 2, -image.getHeight() / 2);
		g2.drawImage(image, 0, 0, null);
	}
	
	@Override
	public void timePassed() {
//		this.collideWithHero(this.world.getHero());
		
		
//		Point leftPt = new Point(this.position.x + 10, this.position.y);
//		Point downPt = new Point(this.position.x, this.position.y + 10);
//		Point rightPt = new Point(this.position.x - 10, this.position.y);
//		Point upPt = new Point(this.position.x, this.position.y - 10);
//		
//		if (left == false && this.blackSpaces.containsKey(leftPt)) {
//			this.right = true;
//			this.moveBy(10, 0);
//			return;
//		}else {
//			this.right = false;
//		}
//		
//		if (up == false && this.blackSpaces.containsKey(downPt)) {
//			this.down = true;
//			this.moveBy(0, 10);
//			return;
//		}else {
//			this.down = false;
//		}	
//		
//		if (this.blackSpaces.containsKey(rightPt)) {
//			this.left = true;
//			this.moveBy(-10, 0);
//			return;
//		}else {
//			this.left = false;
//		}
//		
//		if (this.blackSpaces.containsKey(upPt)) {
//			this.up = true;
//			this.moveBy(0, -10);
//			return;
//		}else {
//			this.up = false;
//		}
		
	}
	
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
}
