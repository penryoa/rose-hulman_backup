import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class DigDugViewer {

	public static final int WIDTH = 620;
	public static final int HEIGHT = 620;

	public void makeWindow(JFrame frame) {
		
		frame.setTitle("Dig Dug Project");
		
		DigDugComponent c = new DigDugComponent();
		c.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.add(c);

		JPanel panel = new JPanel();

		DirectionListener direction = new DirectionListener(c);

		frame.addKeyListener(direction);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	//return dimension;
	public Dimension getSize() {
		return new Dimension(WIDTH, HEIGHT);
	}
}