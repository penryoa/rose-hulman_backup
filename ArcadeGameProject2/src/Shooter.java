import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Shooter {

	// ======= Important Objects ======= \\
	public Point centerPoint;
	public BufferedImage image;
	public World world;
	public int dx;
	public int dy;
	public Graphics2D g2;

	// ======= Shooter Constructor ======= \\

	public Shooter(Graphics2D g2, Point initialPosition, int dx, int dy, World world) {
		this.centerPoint = initialPosition;
		this.world = world;
		this.dx = dx;
		this.dy = dy;
		this.g2 = g2;
		try {
			this.image = ImageIO.read(new File("shooter.png"));
		} catch (IOException e) {
			throw new RuntimeException("Error drawing shooter: " + e);
		}
		this.drawCentered(this.g2);
	}

	// ======= Drawable Methods ======= \\

	public void drawCentered(Graphics2D g2) {
		g2 = (Graphics2D) g2.create();
		g2.translate(this.centerPoint.getX() - 20, this.centerPoint.getY() - 20);
		g2.drawImage(this.image, 0, 0, null);
	}

	public void move() {
		while (this.centerPoint.getX() < 640 && this.getCenterPoint().getY() < 640) {
			for (Monster monster : world.getMonsters()){
				if (monster.getCenterPoint() == this.centerPoint) {
					this.collideWithMonster(monster);
					break;
			}
			
			}
//			if (this.dx > 0 && this.dy == 0) {
			this.centerPoint = new Point((int) this.centerPoint.getX() + 10, (int) this.centerPoint.getY());
			this.drawCentered(this.g2);
//			}
//
//			else if (this.dx < 0 && this.dy == 0) {
//				this.centerPoint = new Point((int) this.centerPoint.getX() + dx, (int) this.centerPoint.getY());
//			}
//
//			else if (this.dy > 0 && this.dx == 0) {
//				this.centerPoint = new Point((int) this.centerPoint.getX(), (int) this.centerPoint.getY() + dy);
//			}
//
//			else if (this.dy < 0 && this.dx == 0) {
//				this.centerPoint = new Point((int) this.centerPoint.getX(), (int) this.centerPoint.getY() + dy);
//			}
		}
	}

	public Point getCenterPoint() {
		return this.centerPoint;
	}

	// ======= GameObject Methods ======= \\

	public void collideWithMonster(Monster monster) {
		monster.collideWithShooter(this);
	}

}
