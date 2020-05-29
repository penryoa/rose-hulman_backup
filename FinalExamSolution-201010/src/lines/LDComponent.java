package lines;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 * This component uses a mouse listener to collect points and render lines
 * between them.
 * 
 * @author Curt Clifton and <TODO: your name here>.
 */
public class LDComponent extends JComponent {
	private static final Dimension SIZE = new Dimension(600, 400);
	private static final Color BACKGROUND_COLOR = Color.BLACK;
	private static final Color LINE_COLOR = Color.GREEN;

	private Rectangle2D background = new Rectangle2D.Double();

	// TODO: Add any additional fields that you need
	private ArrayList<Point2D> points;

	/**
	 * Constructs the component and initializes any fields.
	 */
	public LDComponent() {
		setPreferredSize(SIZE);
		// TODO: Initialize any fields that you added, if necessary
		final ArrayList<Point2D> pointsList = new ArrayList<Point2D>();
		this.points = pointsList;

		// TODO: Add an appropriate listener to this so that the user can click
		// to draw lines, as described on the exam.  Don't forget to repaint().
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pointsList.add(e.getPoint());
				LDComponent.this.repaint();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// paints background
		this.background.setRect(0, 0, this.getWidth(), this.getHeight());
		g2.setColor(BACKGROUND_COLOR);
		g2.fill(this.background);

		g2.setColor(LINE_COLOR);
		// TODO: add code to render the lines
		if (this.points.size() >= 2) {
			Point2D prevPoint = null;
			for (Point2D p : this.points) {
				if (prevPoint != null) {
					Line2D l = new Line2D.Double(prevPoint, p);
					g2.draw(l);
				}
				prevPoint = p;
			}
			// Closes the shape
			if (prevPoint != null) {
				Line2D l = new Line2D.Double(prevPoint, this.points.get(0));
				g2.draw(l);
			}
		}
		// Not required, but here is code to label the points in order:
		for (int i = 0; i < this.points.size(); i++) {
			String label = Integer.toString(i+1);
			Point2D p = this.points.get(i);
			g2.drawString(label, (int) p.getX(), (int) p.getY());
		}
	}

}
