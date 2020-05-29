import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JComponent;

public class DigDugComponent extends JComponent {

	private World world;
	private static final int FRAMES_PER_SECOND = 30;
	private static final long REPAINT_INTERVAL_MS = 1000 / FRAMES_PER_SECOND;

	public DigDugComponent() {
		this.world = new World(this);
		Runnable repainter = new Runnable() {
			@Override
			public void run() {
				// Periodically asks Java to repaint this component
				try {
					while (true) {
						Thread.sleep(REPAINT_INTERVAL_MS);
						repaint();
					}
				} catch (InterruptedException exception) {
					// Stop when interrupted
				}
			}
		};
		new Thread(repainter).start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// makes things look slightly prettier
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// drawDrawable(g2, this.world);
		//
		// List<Drawable> drawableParts = this.world.getDrawableParts();
		// for (Drawable d : drawableParts) {
		// drawDrawable(g2, d);
		// }
		this.drawBackground(g2);
		this.drawParts(g2, this.world);
//		this.drawParts(g2, this.world.getHero());
//		this.drawParts(g2, this.world.getRock());
		List<Drawable> drawableParts = this.world.getDrawableParts();
		for (Drawable object : drawableParts) {
			this.drawParts(g2, object);
		}

		// getWorld().getRock().drawCentered(g2);
		// getWorld().getMonster().drawMonster(g2);
		// getWorld().getHero().drawHero(g2);
	}

	private void drawParts(Graphics2D g2, Drawable object) {
		BufferedImage picture = object.getImage();
		g2.translate(object.getCenterPoint().getX(), object.getCenterPoint().getY());
		g2.translate(-picture.getWidth() / 2, -picture.getHeight() / 2);
		g2.drawImage(picture, 0, 0, null);
		g2.translate(-object.getCenterPoint().getX(), -object.getCenterPoint().getY());
		g2.translate(picture.getWidth() / 2, picture.getHeight() / 2);
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