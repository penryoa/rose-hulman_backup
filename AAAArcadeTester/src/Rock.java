import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Rock implements GameObject {
	
	public Point position;
	
	private BufferedImage rockImage;
	
	public Rock(Point initialPosition){
		this.position = initialPosition;
		try {
			this.rockImage = ImageIO.read(new File("rock.png"));
		} catch (IOException e) {
			throw new RuntimeException("Error drawing rock: "+e);
		}
	}	
	
	public void drawRock(Graphics2D g2){
		g2 = (Graphics2D) g2.create();
		g2.translate(position.x-20, position.y-20);
//		this.cat.drawCentered(g2);
		g2.drawImage(rockImage, 0, 0, null);
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
		hero.collideWithRock(this);
	}
}