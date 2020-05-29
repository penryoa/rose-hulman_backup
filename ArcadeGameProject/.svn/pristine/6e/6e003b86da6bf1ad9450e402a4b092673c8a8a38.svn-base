import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * DirectionListener is responsible for responding to the keyboard.
 * 
 * @author penryoa
 *
 */

public class DirectionListener implements KeyListener {
	DigDugComponent c;
	private World w;
	private Hero h;

	public DirectionListener(DigDugComponent c) {
		this.c = c;
		this.w = this.c.getWorld();
		this.h = this.w.hero;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == e.VK_LEFT) {
			this.h.moveBy(-10, 0);
			this.dig();
		} else if (e.getKeyCode() == e.VK_RIGHT) {
			this.h.moveBy(10, 0);
			this.dig();
		} else if (e.getKeyCode() == e.VK_UP) {
			this.h.moveBy(0, -10);
			this.dig();
		} else if (e.getKeyCode() == e.VK_DOWN) {
			this.h.moveBy(0, 10);
			this.dig();
		} else if (e.getKeyCode() == e.VK_U) {
			this.w.changeLevel(1);
		} else if (e.getKeyCode() == e.VK_D) {
			this.w.changeLevel(-1);
		} else if (e.getKeyCode() == e.VK_SPACE) {
			this.h.shoot();
		} else if (e.getKeyCode() == e.VK_P) {
			if (this.w.isPaused) {
				this.w.setIsPaused(false);
			} else {
				this.w.setIsPaused(true);
			}
		}
	}

	public void dig() {
		if (this.w.isDigging == false && !this.w.blackSpaces.containsKey(this.h.getCenterPoint())) {
			this.w.addBlackSpaces(this.h.getCenterPoint());
			this.w.addToScore(1);
			this.w.updateScoreLabel();
		}
		this.c.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
