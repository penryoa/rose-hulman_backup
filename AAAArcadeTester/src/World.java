
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class World implements Drawable, Temporal {

	private static final long UPDATE_INTERVAL_MS = 300;
	private boolean isPaused = false;
	// private final List<Fygar> fygars = new ArrayList<Fygar>();
	// private final List<Hero> heroToAdd = new ArrayList<Hero>();
	// private final List<Rock> rockToRemove = new ArrayList<Rock>();
	private BufferedImage image;
	private Hero hero;
	private Fygar monster;
	private Rock rock;
	public ArrayList<BlackSpace> blackSpaces = new ArrayList<BlackSpace>();
	public Shape background;
	DigDugComponent c;
	private int level = 1;

	public World(DigDugComponent digDugComponent) {
		this.c = digDugComponent;

		this.background = new Rectangle2D.Double(0, 0, 620, 620);
		try {
			this.image = ImageIO.read(new File("bkgd1.png"));
		} catch (IOException e) {
			throw new RuntimeException("Error drawing dirt: " + e);
		}
		this.hero = new Hero(new Point(200, 500));
		this.rock = new Rock(new Point(300, 300));
		this.monster = new Fygar(new Point(100, 200));

		
		BlackSpace blackSpace = new BlackSpace(hero.position);
		BlackSpace blackSpace1 = new BlackSpace(new Point(110, 200));
		BlackSpace blackSpace2 = new BlackSpace(new Point(120, 200));
		BlackSpace blackSpace3 = new BlackSpace(new Point(130, 200));
		BlackSpace blackSpace4 = new BlackSpace(new Point(140, 200));

		this.blackSpaces.add(blackSpace);
		this.blackSpaces.add(blackSpace1);
		this.blackSpaces.add(blackSpace2);
		this.blackSpaces.add(blackSpace3);
		this.blackSpaces.add(blackSpace4);
		this.monster.setBlackSpace(blackSpaces);

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

	public synchronized void timePassed() {
		if (!this.isPaused) {
			monster.timePassed();
			this.c.repaint();
			// for (Temporal t : this.fygars) {
			// (t).timePassed();
			// }
		}
		// this.fygars.removeAll(this.fygarsToRemove);
		// this.fygarsToRemove.clear();
		// this.fygars.addAll(this.fygarsToAdd);
		// this.fygarsToAdd.clear();
	}

	public Hero getHero() {
		return hero;
	}

	public Rock getRock() {
		return rock;
	}

	public Fygar getFygar() {
		return monster;
	}

	public ArrayList<BlackSpace> getBlackSpcaces() {
		return blackSpaces;
	}

	public void drawLevel(Graphics2D g2) {
		g2.drawImage(this.image, 20, 120, null);
		for (int i = 0; i < blackSpaces.size(); i++) {
			blackSpaces.get(i).drawBlackSpace(g2);
		}
	}

	public void changeLevel(int num) {

		if (this.level + num >= 1 && this.level + num <= 4) {
			this.level += num;
			try {
				this.image = ImageIO.read(new File("bkgd" + this.level + ".png"));
				this.blackSpaces.clear();
				drawLevel((Graphics2D) this.c.getGraphics());

				this.getHero().position = new Point(200, 500);
				this.getFygar().position = new Point(100, 200);
				BlackSpace blackSpace = new BlackSpace(this.getHero().position);
				this.blackSpaces.add(blackSpace);
				this.getRock().drawRock((Graphics2D) this.c.getGraphics());
				this.getFygar().drawFygar((Graphics2D) this.c.getGraphics());
				this.getHero().drawHero((Graphics2D) this.c.getGraphics());

			} catch (IOException e) {
				throw new RuntimeException("Error in drawing: " + e);
			}
		}
	}
	
	public void changeLevelOther(int num){
		if (this.level + num >= 1 && this.level + num <= 4) {
			this.level += num;
			try {
				this.image = ImageIO.read(new File("bkgd" + this.level + ".png"));
				this.blackSpaces.clear();
				drawLevel((Graphics2D) this.c.getGraphics());

				this.getHero().position = new Point(200, 500);
				this.getFygar().position = new Point(100, 200);
				BlackSpace blackSpace = new BlackSpace(this.getHero().position);
				this.blackSpaces.add(blackSpace);
				this.getRock().drawRock((Graphics2D) this.c.getGraphics());
				this.getFygar().drawFygar((Graphics2D) this.c.getGraphics());
				this.getHero().drawHero((Graphics2D) this.c.getGraphics());

			} catch (IOException e) {
				throw new RuntimeException("Error in drawing: " + e);
			}
		}
	}

	public void drawBackground(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.draw(background);
		g2.fill(background);
	}

	@Override
	public void die() {
	}

	@Override
	public void setIsPaused(boolean isPaused) {
	}

	@Override
	public boolean getIsPaused() {
		return false;
	}

}
