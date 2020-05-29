import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComponent;

public class DigDugComponent extends JComponent {
	
	// ======= Important Objects ======= \\
	private World world;
	private Hero hero;

	// ======= DigDugComponent Constructor ======= \\
	public DigDugComponent() {
		this.world = new World(this);
		this.hero = this.world.getHero();
	}

	// ======= Important Methods ======= \\

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// makes things look slightly prettier
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// drawDrawable(g2, this.world);
		
//		 List<Drawable> drawableParts = this.world.getDrawableParts();
//		 for (Drawable d : drawableParts) {
//		 drawDrawable(g2, d);
//		 }

		this.drawBackground(g2);
		this.world.drawDirtImage(g2);
//		this.world.setLevelObjects();
		this.handleCollisions();
		this.drawDrawablesCentered(g2);
		this.handleCollisions();
//		for(Drawable o : world.getDrawables()) {
//			o.drawCentered(g2);
//		}
	}

	public HashMap<Point, BlackSpace> getBlackSpaces() {
		return this.getWorld().blackSpaces;
	}

	private void drawDrawablesCentered(Graphics2D graphics) {
		this.getWorld().drawDrawablesCentered(graphics);
	}

	private void drawBackground(Graphics2D g2) {
		this.getWorld().drawBackground(g2);
	}

	public World getWorld() {
		return this.world;
	}
//	private void updateState() {
//		this.world.timePassed();
//		handleCollisions();
//	}

	// ======= Potentially Unused Method? ======= \\
	
	public void handleCollisions() {
		List<GameObject> allObjects = new ArrayList<>();
		// allObjects.add(box);
		allObjects.addAll(this.world.getGameObjects());

//		for (GameObject object : allObjects) {
//			// FIXME: make box a GameObject
//			object.collideWithHero(this.world.getHero());
//
//			for (GameObject object2 : allObjects) {
//				if (object != object2 && !object.shouldRemove() && !object2.shouldRemove()) {
//					object.collideWith(object2);
//				}
//			}
//		}
		
//		for(Monster m : this.world.getMonsters()) {
//			m.collideWithHero(this.world.getHero());
//		}

		List<GameObject> shouldRemove = new ArrayList<>();

		for (GameObject object : allObjects) {
			if (object.shouldRemove()) {
				shouldRemove.add(object);
			}
		}

		for (GameObject object : shouldRemove) {
			this.world.getGameObjects().remove(object);
			object.onRemove();
		}
	}

	
}