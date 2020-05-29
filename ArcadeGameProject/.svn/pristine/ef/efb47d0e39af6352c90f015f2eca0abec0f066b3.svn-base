
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DirectionListener implements KeyListener {
	DigDugComponent c;
	Point pt;
	World w;

	public DirectionListener(DigDugComponent c) {
		this.c = c;
		this.w = c.getWorld();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == e.VK_LEFT) {
			w.getHero().move(-10, 0);
			pt = new Point(w.getHero().centerPoint);
			w.blackSpaces.putIfAbsent(new Point(pt.x, pt.y), new BlackSpace(new Point(pt.x, pt.y)));
			w.blackSpaces.putIfAbsent(new Point(pt.x + 10, pt.y), new BlackSpace(new Point(pt.x + 10, pt.y)));
			c.repaint();
		}

		if (e.getKeyCode() == e.VK_RIGHT) {
			w.getHero().move(10, 0);
			pt = new Point(w.getHero().centerPoint);
			w.blackSpaces.putIfAbsent(new Point(pt.x, pt.y), new BlackSpace(new Point(pt.x, pt.y)));
			w.blackSpaces.putIfAbsent(new Point(pt.x - 10, pt.y), new BlackSpace(new Point(pt.x - 10, pt.y)));
			c.repaint();
		}

		if (e.getKeyCode() == e.VK_UP) {
			w.getHero().move(0, -10);
			pt = new Point(w.getHero().centerPoint);
			w.blackSpaces.putIfAbsent(new Point(pt.x, pt.y), new BlackSpace(new Point(pt.x, pt.y)));
			w.blackSpaces.putIfAbsent(new Point(pt.x, pt.y + 10), new BlackSpace(new Point(pt.x, pt.y + 10)));
			c.repaint();
		}

		if (e.getKeyCode() == e.VK_DOWN) {
			w.getHero().move(0, 10);
			pt = new Point(w.getHero().centerPoint);
			w.blackSpaces.putIfAbsent(new Point(pt.x, pt.y), new BlackSpace(new Point(pt.x, pt.y)));
			w.blackSpaces.putIfAbsent(new Point(pt.x, pt.y - 10), new BlackSpace(new Point(pt.x, pt.y - 10)));
			c.repaint();
		}

		if (e.getKeyCode() == e.VK_U) {
			w.changeLevel(1);
		}

		if (e.getKeyCode() == e.VK_D) {
			w.changeLevel(-1);
		}

		if (e.getKeyCode() == e.VK_SPACE) {
			w.getHero().drawShooter((Graphics2D) c.getGraphics());
			this.c.repaint();

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
