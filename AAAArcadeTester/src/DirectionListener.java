
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DirectionListener implements KeyListener {
	DigDugComponent c;

	public DirectionListener(DigDugComponent c) {
		this.c = c;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == e.VK_LEFT) {
			c.getWorld().getHero().update(-10, 0);
			BlackSpace blackSpace = new BlackSpace(c.getWorld().getHero().position);
			c.getWorld().blackSpaces.add(blackSpace);
			c.repaint();
		}

		if (e.getKeyCode() == e.VK_RIGHT) {
			c.getWorld().getHero().update(10, 0);
			BlackSpace blackSpace = new BlackSpace(c.getWorld().getHero().position);
			c.getWorld().blackSpaces.add(blackSpace);
			c.repaint();
		}

		if (e.getKeyCode() == e.VK_UP) {
			c.getWorld().getHero().update(0, -10);
			BlackSpace blackSpace = new BlackSpace(c.getWorld().getHero().position);
			c.getWorld().blackSpaces.add(blackSpace);
			c.repaint();
		}

		if (e.getKeyCode() == e.VK_DOWN) {
			c.getWorld().getHero().update(0, 10);
			BlackSpace blackSpace = new BlackSpace(c.getWorld().getHero().position);
			c.getWorld().blackSpaces.add(blackSpace);
			c.repaint();
		}

		if (e.getKeyCode() == e.VK_U) {
			this.c.getWorld().changeLevel(1);
		}

		if (e.getKeyCode() == e.VK_D) {
			this.c.getWorld().changeLevel(-1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
