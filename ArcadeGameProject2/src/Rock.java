import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Rock extends GameObject {
	
	// ======= Important Objects ======= \\
	public Point centerPoint;
	private BufferedImage rockImage;
	
	
	// ======= Rock Constructor ======= \\

	public Rock(Point initialPosition, World world){
		super(initialPosition, world);
		this.centerPoint = initialPosition;
		try {
			this.rockImage = ImageIO.read(new File("rock.png"));
		} catch (IOException e) {
			throw new RuntimeException("Error drawing rock: "+e);
		}
	}	
	
	// ======= Drawable Methods ======= \\

	@Override
	public void drawCentered(Graphics2D g2){
		g2 = (Graphics2D) g2.create();
		g2.translate(centerPoint.x-20, centerPoint.y-20);
		g2.drawImage(rockImage, 0, 0, null);
	}
	
	@Override
	public void move(int dx, int dy) {
		
	}
	
	// ======= GameObject Methods ======= \\
	
	@Override
	public void update() {
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
		hero.collideWithRock(this);
	}

	// ======= Other Methods ======= \\

	public Point returnPosition(){
		return this.centerPoint;
	}

	@Override
	public Point2D getCenterPoint() {
		return null;
	}
	
}