import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JComponent;


public class DigDugComponent extends JComponent {

	
	private World world;
	private BufferedImage image;
	private Hero hero;
	
	private List<Monster> monsters = new ArrayList<>();

	public DigDugComponent() {
		this.world = new World(this);
		this.hero= world.getHero();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// makes things look slightly prettier
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
//		drawDrawable(g2, this.world);
//
//		List<Drawable> drawableParts = this.world.getDrawableParts();
//		for (Drawable d : drawableParts) {
//			drawDrawable(g2, d);
//		}
		
		this.drawBackground(g2);
		this.drawLevel(g2);
		getWorld().getRock().drawRock(g2);
		getWorld().getFygar().drawFygar(g2);
		getWorld().getHero().drawHero(g2);
	}

	private void handleCollisions() {
		List<GameObject> allObjects = new ArrayList<>();
		//allObjects.add(box);
		allObjects.addAll((Collection<? extends GameObject>) this.monsters);

		
		for(GameObject object: allObjects){
			// FIXME: make box a GameObject
			object.collideWithHero(hero);
			
			for(GameObject object2: allObjects){
				if(object != object2 && !object.shouldRemove() && !object2.shouldRemove()){
					object.collideWith(object2);
				}
			}
		}
		
		List<GameObject> shouldRemove = new ArrayList<>();
		
		for(GameObject object: allObjects){
			if(object.shouldRemove()){
				shouldRemove.add(object);
			}
		}
		
		for(GameObject object: shouldRemove){
			// Not sure which one to remove from.
			// FIXME: maybe you could do more double dispatch??
			this.monsters.remove(object);
	
			object.onRemove();
		}
	}

	public ArrayList<BlackSpace> getBlackSpaces() {
		return this.getWorld().blackSpaces;
	}

	private void drawLevel(Graphics2D graphics) {
		this.getWorld().drawLevel(graphics);
	}
	
	private void drawBackground(Graphics2D g2) {
		this.getWorld().drawBackground(g2);
	}

	public World getWorld() {
		return this.world;
	}
}