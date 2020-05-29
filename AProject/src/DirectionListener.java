
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
			this.c.getWorld().getHero().setIsPaused(false);
			this.c.getWorld().getHero().setDirection(-1, 0);
		}

		if (e.getKeyCode() == e.VK_RIGHT) {
			this.c.getWorld().getHero().setIsPaused(false);
			this.c.getWorld().getHero().setDirection(1, 0);
		}

		if (e.getKeyCode() == e.VK_UP) {
			this.c.getWorld().getHero().setIsPaused(false);
			this.c.getWorld().getHero().setDirection(0, -1);
		}

		if (e.getKeyCode() == e.VK_DOWN) {
			this.c.getWorld().getHero().setIsPaused(false);
			this.c.getWorld().getHero().setDirection(0, 1);

		}

		 if (e.getKeyCode() == e.VK_U) {
		 this.c.getWorld().changeLevel(1);
		 }
		
		 if (e.getKeyCode() == e.VK_D) {
		 this.c.getWorld().changeLevel(-1);
		 }
		
		// if (e.getKeyCode() == e.VK_SPACE) {
		// Shooter newShooter = new
		// Shooter(this.c.getWorld().getHero().position, 10, 0);
		// newShooter.drawCentered((Graphics2D) this.c.getGraphics());
		// this.c.repaint();
		//
		// }
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
