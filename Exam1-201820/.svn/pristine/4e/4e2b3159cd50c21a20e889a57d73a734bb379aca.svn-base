import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class BlockComponent extends JComponent {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// TODO 0: complete the TODO below.
		// The others are in PyramidComponent
		phase1(g2);
	}

	/**
	 * Phase 1: a default inverted pyramid 5 rows high made of rectangles of
	 * dimension 30x15. The top-left corner is placed at (0,0).
	 */
	public void phase1(Graphics2D g2) {
		// TODO 1: write the code in the Pyramid class to make this work
		Pyramid defaultPyramid = new Pyramid();
		defaultPyramid.drawOneBlock(g2);

		g2.translate(50, 100);
		defaultPyramid.drawOneBlock(g2);
		g2.translate(-50, -100);
		
		// We give you the screen text for clarity; don't change it.
		Font font = new Font("Times New Roman", Font.PLAIN, 16);
		g2.setFont(font);
		g2.setColor(new Color(0,0,0));
		g2.drawString("Phase 1", 10, 30);
	}
}
