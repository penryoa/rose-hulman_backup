
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class PyramidComponent extends JComponent {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// Complete the TODOs below.
		phase2(g2);
		phase3(g2);

	}

	/**
	 * Phase 2: a default inverted pyramid 5 rows high made of rectangles of
	 * dimension 30x15. The top-left corner is placed at (0,0).
	 */
	public void phase2(Graphics2D g2) {
		// TODO 2: write the code in the Pyramid class to make this work
		Pyramid defaultPyramid = new Pyramid();
		defaultPyramid.drawOn(g2);
		// We give you the screen text for clarity; don't change it.
		Font font = new Font("Times New Roman", Font.PLAIN, 16);
		g2.setFont(font);
		g2.setColor(new Color(0,0,200));
		g2.drawString("Phase 2", 50, 90);
	}

	/**
	 * Phase 3: a pinwheel of pyramids, rotated around the top-left corner of
	 * the provided Rectangle.
	 */
	public void phase3(Graphics2D g2) {

		g2.setColor(new Color(0, 200, 0));
		g2.drawString("Phase 3", 235, 400);
		
		for (int i = 0; i < 4; i++) {
			double degrees = i * 90;
			// TODO 3: UNCOMMENT the next two lines and write
			// more code in the Pyramid class to make this work
			Pyramid circlePyramid = new Pyramid(260, 200);
			circlePyramid.drawRotated(g2, degrees);
		}
	}

}
