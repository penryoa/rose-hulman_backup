
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class World implements Drawable, Temporal {

	private static final long UPDATE_INTERVAL_MS = 10;
	private boolean isPaused = false;
	// private final List<Fygar> fygars = new ArrayList<Fygar>();
	// private final List<Hero> heroToAdd = new ArrayList<Hero>();
	// private final List<Rock> rockToRemove = new ArrayList<Rock>();
	private BufferedImage image;
	private Hero hero;
	public Shape background;
	DigDugComponent c;
	private int level = 1;

	private final List<GameObject> gameObjects = new ArrayList<>();
	private final List<GameObject> gameObjectsToAdd = new ArrayList<>();
	private final List<GameObject> gameObjectsToDelete = new ArrayList<>();

	public World(DigDugComponent digDugComponent) {
		this.c = digDugComponent;

		this.background = new Rectangle2D.Double(0, 0, 620, 620);
		try {
			this.image = ImageIO.read(new File("bkgd1.png"));
		} catch (IOException e) {
			throw new RuntimeException("Error drawing dirt: " + e);
		}
		this.hero = new Hero(new Point2D.Double(230, 230), this);
		this.addGameObject(this.hero);
		this.addGameObject(new Rock(new Point2D.Double(200, 400), this));
		// this.gameObjectsToAdd.add(this.monster);

		// BlackSpace blackSpace = new BlackSpace(hero.position);

		Runnable tickTock = new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						Thread.sleep(UPDATE_INTERVAL_MS);
						timePassed();
					}
				} catch (InterruptedException exception) {
					// Stop when interrupted
				}
			}
		};
		new Thread(tickTock).start();
	}

	// public synchronized List<Drawable> getDrawableParts() {
	// return new ArrayList<Drawable>(this.balls);
	// }

	//

	public void drawLevel(Graphics2D g2) {
		g2.drawImage(this.image, 100, 120, null);
	}

	public void changeLevel(int num) {

//		if (this.level + num >= 1 && this.level + num <= 4) {
//			this.level += num;
//			try {
//				this.image = ImageIO.read(new File("bkgd" + this.level + ".png"));
//
//			} catch (IOException e) {
//				throw new RuntimeException("Error in drawing: " + e);
//			}
//		}
	}

	public void drawBackground(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.draw(this.background);
		g2.fill(this.background);
	}

	public synchronized List<Drawable> getDrawableParts() {

		return new ArrayList<Drawable>(this.gameObjects);
	}

	public Hero getHero() {
		return this.hero;
	}

	// public Rock getRock() {
	// return this.rock;
	// }
	//
	// 
	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub.
		return this.image;
	}

	@Override
	public Dimension getDimension() {
		// TODO Auto-generated method stub.
		Dimension dim = new Dimension(460, 460);
		return dim;
	}

	@Override
	public Point2D getCenterPoint() {
		// TODO Auto-generated method stub.
		Point2D center = new Point2D.Double(260, 360);
		return center;
	}

	public boolean isInsideWorldX(Point2D point) {
		return point.getX() >= 40 && point.getX() <= 480;
	}

	public boolean isInsideWorldY(Point2D point) {
		return point.getY() >= 140 && point.getY() <= 580;
	}

	public boolean isInsideWorld(Point2D point) {
		return isInsideWorldX(point) && isInsideWorldY(point);
	}

	public synchronized void addGameObject(GameObject object) {
		this.gameObjectsToAdd.add(object);
	}

	public synchronized void deleteGameObject(GameObject object) {
		this.gameObjectsToDelete.add(object);
	}

	
	public synchronized void timePassed() {
		if (!this.isPaused) {
			for (Temporal g : this.gameObjects) {
				g.timePassed();
			}
			// for (Temporal t : this.fygars) {
			// (t).timePassed();
			// }

		}
		this.gameObjects.removeAll(this.gameObjectsToDelete);
		this.gameObjectsToDelete.clear();

		this.gameObjects.addAll(this.gameObjectsToAdd);
		this.gameObjectsToAdd.clear();

		// this.fygars.removeAll(this.fygarsToRemove);
		// this.fygarsToRemove.clear();
		// this.fygars.addAll(this.fygarsToAdd);
		// this.fygarsToAdd.clear();
	}

	// Temporal
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
