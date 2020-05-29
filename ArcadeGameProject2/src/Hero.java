import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Hero extends GameObject {

	// ======= Important Objects ======= \\

	private BufferedImage image;
	public Point centerPoint;
	private boolean collision;
	private World world;
	public ArrayList<Rock> rocks = new ArrayList<>();;
	public ArrayList<Monster> monsters = new ArrayList<>();;

	private int directionx;
	private int directiony;
	private Monster monster;
	boolean hitRock;

	// ======= Hero Constructor ======= \\

	public Hero(Point initialPosition, World world) {
		super(initialPosition, world);
		this.world = world;
		this.centerPoint = initialPosition;
		try {
			image = ImageIO.read(new File("hero.png"));
		} catch (IOException e) {
			throw new RuntimeException("Error drawing hero: " + e);
		}

		this.collision = true;
		this.hitRock = true;
		this.directionx = 0;
		this.directiony = 0;

		// this.monster=world.getMonster();
	}

	// ======= Drawable Methods ======= \\

	public void drawCentered(Graphics2D g2) {
		g2 = (Graphics2D) g2.create();
		g2.translate(this.centerPoint.x - this.image.getWidth() / 2, this.centerPoint.y - this.image.getHeight() / 2);
		g2.drawImage(image, 0, 0, null);
	}

	// public void moveBy(int dx, int dy) {
	// boolean canMove = this.hitRock(dx, dy);
	// if (canMove && this.centerPoint.x + dx > 30 && this.centerPoint.x + dx <
	// 490 && this.centerPoint.y + dy > 130
	// && this.centerPoint.y + dy < 590) {
	// this.centerPoint = new Point(centerPoint.x + dx, centerPoint.y + dy);
	// }
	//
	// }

	@Override
	public void move(int dx, int dy) {
		this.directionx = dx;
		this.directiony = dy;

		// if (Math.abs(this.getCenterPoint().getX() + dx - 300) < 40
		// && Math.abs(this.getCenterPoint().getY() + dy - 300) < 40) {
		// hitRock = false;
		// } else {
		// hitRock = true;
		// }
		// for (Monster m : this.world.getMonsters()) {
		// if (Math.abs(this.centerPoint.x + dx - m.getPosition().x) < 40
		// && Math.abs(this.centerPoint.y + dy - m.getPosition().y) < 40) {
		// hitRock = false;
		// }
		// }

		if (this.hitRock(dx, dy) 
//				&& this.hitMonster(dx,  dy) 
				&& this.getCenterPoint().getX() + dx > 30
				&& this.getCenterPoint().getX() + dx < 490 && this.getCenterPoint().getY() + dy > 130
				&& this.getCenterPoint().getY() + dy < 590) {
			this.centerPoint = new Point(centerPoint.x + dx, centerPoint.y + dy);
		}

	}

	public Point getCenterPoint() {
		return this.centerPoint;
	}

	// ======= Other Methods ======= \\

	public boolean hitRock(int dx, int dy) {
		for (Rock rock : rocks) {
			if (Math.abs(this.centerPoint.x + dx - rock.centerPoint.x) < 40
					&& Math.abs(this.centerPoint.y + dy - rock.centerPoint.y) < 40) {
				this.collideWithRock(rock);
				return false;
			}
		}
		return true;
	}

//	public boolean hitMonster(int dx, int dy) {
//		for (Monster m : this.monsters) {
//			if (Math.abs(this.centerPoint.x + dx - m.centerPoint.x) < 40
//					&& Math.abs(this.centerPoint.y + dy - m.centerPoint.y) < 40) {
//				this.collideWithMonster(m);
//				return false;
//			}
//		}
//		return true;
//		
//	}

	public void drawShooter(Graphics2D g2) {
		Shooter newShooter = new Shooter(g2, this.getCenterPoint(), this.directionx, this.directiony, this.world);
		newShooter.move();
	}

	// ======= Temporal Methods ======= \\

	public void die() {
		this.world.getGameObjects().remove(this);
	}

	// ======= GameObject Methods ======= \\

	public void update(int i, int j) {
		// TODO Auto-generated method stub.

	}

	@Override
	public void drawOn() {
		// TODO Auto-generated method stub.

	}

	@Override
	public boolean shouldRemove() {
		// TODO Auto-generated method stub.
		return false;
	}

	@Override
	public void onRemove() {
		// TODO Auto-generated method stub.

	}

	@Override
	public void drawOn(Graphics2D g2) {
		// TODO Auto-generated method stub.

	}

	@Override
	public void collideWith(GameObject other) {
		// TODO Auto-generated method stub.

	}

	@Override
	public void collideWithMonster(Monster monster) {
		System.out.println("You died!");
	}

	@Override
	public void collideWithRock(Rock rock) {
		// TODO Auto-generated method stub.
		if (Math.abs(this.getCenterPoint().getX() + this.directionx - rock.returnPosition().getX()) < 40
				&& Math.abs(this.getCenterPoint().getY() + this.directiony - rock.returnPosition().getX()) < 40) {
			this.collision = false;
		} else {
			this.collision = true;
		}
	}

	@Override
	public void collideWithHero(Hero hero) {
		// TODO Auto-generated method stub.

	}

}
