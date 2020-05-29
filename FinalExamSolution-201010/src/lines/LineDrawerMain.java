package lines;

import javax.swing.JFrame;

/**
 * This is the starting point for the line drawing program.  It sets up the GUI.
 * You shouldn't need to change anything here, but you may if you wish.
 *
 * @author Curt Clifton.
 *         Created Nov 14, 2009.
 */
public class LineDrawerMain {

	/**
	 * Starts the GUI.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Linear Thinking");
		frame.add(new LDComponent());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
