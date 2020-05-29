import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Chaos {

	public static Point given;
	public static Point l;
	public static Point t;
	public static Point r;
	public static int width = 500;
	public static int height = 500;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Chaos Game");
		frame.setSize(width, height);
		
		JPanel panel = new JPanel();
		Comp comp = new Comp(l, t, r);
		panel.add(comp);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
