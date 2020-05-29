import java.awt.Color;

import javax.swing.JFrame;

/**
 * The main class for your arcade game.
 * 
 * You can design your game any way you like, but make the game start
 * by running main here.
 * 
 * Also don't forget to write javadocs for your classes and functions!
 * 
 * @author Buffalo
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		JFrame digDugFrame = new JFrame("Dig Dug");
		DigDugViewer viewer = new DigDugViewer();
		viewer.makeWindow(digDugFrame);
		
		digDugFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		digDugFrame.setVisible(true);
		
		
	}

}
