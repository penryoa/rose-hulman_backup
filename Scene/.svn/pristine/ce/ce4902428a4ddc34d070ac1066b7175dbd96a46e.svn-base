import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class SceneComponent extends JComponent {
	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		Graphics2D g2 = (Graphics2D) graphics;

		g2.setColor(Color.BLUE);
		g2.fillRect(0, 0, 750, 375);

		g2.setColor(Color.GREEN);
		g2.fillRect(0, 375, 750, 225);

		Sun s = new Sun();
		s.drawOn(g2);

		for (int k = 0; k < 25; k++) {
			PineTrees tree = new PineTrees(225 + k * 18, 350, 10, 40);
			tree.drawOn(g2);
		}

		for (int k = 0; k < 15; k++) {
			PineTrees tree = new PineTrees(225 + k * 28, 360, 20, 80);
			tree.drawOn(g2);
		}

		for (int k = 0; k < 5; k++) {
			House house = new House(100 + k * 115, 475, Color.RED);
			house.drawOn(g2);
		}
	}
}