
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

/**
 * The levels are drawn and updated by World.
 * 
 * @author penryoa
 *
 */


public class World implements Temporal {

	// ======= Important Objects ======= \\

	DigDugComponent c;
	private static final long UPDATE_INTERVAL_MS = 300;
	protected Level level;
	protected int score;
	protected int rockFallCount;
	protected boolean isPaused = false;
	protected boolean stop = false;
	protected boolean isDigging = false;
	protected Hero hero;
	protected MasterSword theSword = null;
	protected HashMap<Point, BlackSpace> blackSpaces = new HashMap<>();
	protected ArrayList<Fruit> fruits = new ArrayList<>();
	protected ArrayList<Monster> monsters = new ArrayList<>();
	protected ArrayList<Rock> rocks = new ArrayList<>();
	protected ArrayList<Shooter> shooters = new ArrayList<>();
	protected ArrayList<GameObject> objects = new ArrayList<>();
	protected Point centerPoint = new Point(220, 260);
	protected JLabel scoreLabel;

	// ======= World Constructor ======= \\

	public World(DigDugComponent digDugComponent, JLabel scoreLabel) {
		// this.score = this.level.currentScore;
		this.scoreLabel = scoreLabel;
		this.rockFallCount = 0;
		this.hero = new Hero(this.centerPoint, this, "hero");
		this.c = digDugComponent;
		this.initialize();
		this.setLevelObjects();
		this.addBlackSpaces(this.hero.centerPoint);

		Runnable tickTock = new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						Thread.sleep(UPDATE_INTERVAL_MS);
						timePassed();
					}
				} catch (InterruptedException exception) {
				}
			}
		};
		new Thread(tickTock).start();
	}

	// ======= Temporal Methods ======= \\

	public synchronized void timePassed() {
		if (this.level.getCurrentLevel() != 5) {
			if (this.monsters.size() == 0) {
				this.changeLevel(1);
			}
		}
		if (!this.isPaused) {
			this.stop = false;
			this.objects = this.getObjects();
			this.shooters.clear();

			for (int i = 0; i < this.objects.size(); i++) {

				if (this.monsters.size() > 0) {
					for (int j = 0; j < this.monsters.size(); j++) {
						this.objects.get(i).collideWithMonster(this.monsters.get(j));
					}
				}
				this.objects.get(i).collideWithHero(hero);
				if (this.stop == true) {
					break;
				}
				this.objects.get(i).timePassed();
			}

			if (this.rockFallCount == 2) {
				this.fruits.add(new Fruit(this.centerPoint, this, "fruit"));
				this.rockFallCount = 0;
			}
			this.c.repaint();
		}
		this.updateScoreLabel();
	}

	// ======= Other Methods ======= \\

	public void initialize() {
		try {
			this.level = new Level((Graphics2D) c.getGraphics());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void drawDirtImage(Graphics2D g2) {
		g2.drawImage(this.level.image, 0, 0, null);
	}

	public void setToLevel(int num) throws IOException {
		this.level.setToLevel(num);
	}

	public void changeLevel(int num) {
		if (this.isDigging == false) {
			Graphics2D g2 = (Graphics2D) this.c.getGraphics();

			if (this.level.canChangeLevel(num)) {
				this.rockFallCount = 0;
				this.monsters.clear();
				this.rocks.clear();
				this.shooters.clear();
				this.fruits.clear();
				this.theSword = null;
				this.objects.clear();
				if (num != 0) {
					this.blackSpaces.clear();
				}

				try {
					this.level.changeLevel(num);
				} catch (IOException e) {
				}
				this.setLevelObjects();
				this.drawDirtImage(g2);
				this.addBlackSpaces(this.hero.centerPoint);

				this.c.repaint();
			}
		}
	}

	public void setLevelObjects() {

		for (int i = 0; i < this.level.tunnelLocations.size(); i++) {
			Point p = this.level.tunnelLocations.get(i);
			BlackSpace newSpace = new BlackSpace(p);
			this.blackSpaces.put(p, newSpace);
		}

		for (int i = 0; i < this.level.fygarLocations.size(); i++) {
			Point p = this.level.fygarLocations.get(i);
			Fygar newFygar = new Fygar(p, this, "fygar");
			this.monsters.add(newFygar);
			this.objects.add(newFygar);
		}

		for (int i = 0; i < this.level.pookieLocations.size(); i++) {
			Point p = this.level.pookieLocations.get(i);
			Pookie newPookie = new Pookie(p, this, "pookie");
			this.monsters.add(newPookie);
			this.objects.add(newPookie);
		}

		for (int i = 0; i < this.level.pigLocations.size(); i++) {
			Point p = this.level.pigLocations.get(i);
			Pookie newPookie = new Pookie(p, this, "pig");
			this.monsters.add(newPookie);
			this.objects.add(newPookie);
		}

		for (int i = 0; i < this.level.creeperLocations.size(); i++) {
			Point p = this.level.creeperLocations.get(i);
			Creeper newCreeper = new Creeper(p, this, "creep");
			this.monsters.add(newCreeper);
			this.objects.add(newCreeper);
		}

		for (int i = 0; i < this.level.R2Locations.size(); i++) {
			Point p = this.level.R2Locations.get(i);
			Rock2 newRock = new Rock2(p, this, "rock");
			this.rocks.add(newRock);
			this.objects.add(newRock);
		}

		for (int i = 0; i < this.level.R1Locations.size(); i++) {
			Point p = this.level.R1Locations.get(i);
			Rock1 newRock = new Rock1(p, this, "rock1");
			this.rocks.add(newRock);
			this.objects.add(newRock);
		}

		for (int i = 0; i < this.level.masterSwordLocations.size(); i++) {
			Point p = this.level.masterSwordLocations.get(i);
			MasterSword theSword = new MasterSword(p, this, "masterSword");
			this.fruits.add(theSword);
			this.theSword = theSword;
			this.objects.add(theSword);
		}

		for (int i = 0; i < this.objects.size(); i++) {
			this.objects.get(i).setBlackSpace(this.blackSpaces);
		}

		this.hero.centerPoint = this.level.heroLocation;
		this.objects.add(this.hero);
	}

	public void addToScore(int value) {
		try {
			this.level.addToScore(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateScoreLabel() {
		this.scoreLabel
				.setText("Current Score: " + this.level.currentScore + " | Lives left: " + this.getHero().livesLeft);
	}

	// ======= Getter Methods ======= \\
	public synchronized ArrayList<GameObject> getObjects() {
		ArrayList<GameObject> newList = new ArrayList<>();
		newList.addAll(monsters);
		newList.addAll(rocks);
		newList.addAll(fruits);
		newList.addAll(shooters);
		newList.add(hero);
		return newList;
	}

	@Override
	public void die() {
	}

	public HashMap<Point, BlackSpace> getBlackSpaces() {
		return this.blackSpaces;
	}

	@Override
	public void setIsPaused(boolean isPaused) {
		this.isPaused = isPaused;
		this.hero.isPaused = isPaused;
	}

	@Override
	public boolean getIsPaused() {
		return this.isPaused;
	}

	public Hero getHero() {
		return this.hero;
	}

	// checks if any 'non-visible' blackSpaces is missing
	public void addBlackSpaces(Point p) {
		this.isDigging = true;
		HashMap<Point, BlackSpace> blackSpacesToAdd = new HashMap<>();
		for (Point pt : this.blackSpaces.keySet()) {
			if (this.blackSpaces.containsKey(new Point(pt.x + 40, pt.y))
					&& !this.blackSpaces.containsKey(new Point(pt.x + 20, pt.y))) {
				blackSpacesToAdd.put(new Point(pt.x + 10, pt.y), new BlackSpace(new Point(pt.x + 10, pt.y)));
				blackSpacesToAdd.put(new Point(pt.x + 20, pt.y), new BlackSpace(new Point(pt.x + 20, pt.y)));
				blackSpacesToAdd.put(new Point(pt.x + 30, pt.y), new BlackSpace(new Point(pt.x + 30, pt.y)));
			} else if (this.blackSpaces.containsKey(new Point(pt.x, pt.y + 40))
					&& !this.blackSpaces.containsKey(new Point(pt.x, pt.y + 20))) {
				blackSpacesToAdd.put(new Point(pt.x, pt.y + 10), new BlackSpace(new Point(pt.x, pt.y + 10)));
				blackSpacesToAdd.put(new Point(pt.x, pt.y + 20), new BlackSpace(new Point(pt.x, pt.y + 20)));
				blackSpacesToAdd.put(new Point(pt.x, pt.y + 30), new BlackSpace(new Point(pt.x, pt.y + 30)));
			}
		}
		this.blackSpaces.putAll(blackSpacesToAdd);
		this.blackSpaces.put(p, new BlackSpace(p));
		this.isDigging = false;
	}
}
