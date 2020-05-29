import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Hero implements GameObject{
	private BufferedImage image;
	public Point position;


	public Hero(Point initialPosition) {
		this.position = initialPosition;
		try {
			image = ImageIO.read(new File("hero.png"));
		} catch (IOException e) {
			throw new RuntimeException("Error drawing hero: " + e);
		}
	}

	public void drawCentered(Graphics2D g2) {
		g2.translate(-image.getWidth() / 2, -image.getHeight() / 2);
		g2.drawImage(image, 0, 0, null);
//		g2.translate(0, image.getHeight());
	}

	public void drawHero(Graphics2D g2) {
		g2 = (Graphics2D) g2.create();
		g2.translate(position.x, position.y);
		this.drawCentered(g2);
	}

	public void update(int dx, int dy) {
		boolean hit;
		if (Math.abs(this.getCenterPoint().getX() + dx - 300) < 40
				&& Math.abs(this.getCenterPoint().getY() + dy - 300) < 40) {
			hit =false;
		}else{
			hit=true;
		}
		if (hit && this.getCenterPoint().getX() + dx > 30 && this.getCenterPoint().getX() + dx < 490
				&& this.getCenterPoint().getY() + dy > 130 && this.getCenterPoint().getY() + dy < 590) {
			this.position = new Point(position.x + dx, position.y + dy);
		}

	}

	public Point2D getCenterPoint() {
		return this.position;
	}

	@Override
	public void update() {
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
		// TODO Auto-generated method stub.
		
	}

	@Override
	public void collideWithRock(Rock rock) {
		// TODO Auto-generated method stub.
		
	}

	@Override
	public void collideWithHero(Hero hero) {
		// TODO Auto-generated method stub.
		
	}

}
