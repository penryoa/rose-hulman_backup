package spaceships;

import javax.swing.JFrame;

/**
 * This class creates the component and displays the frame
 * 
 * @author hewner
 *
 */
public class MovingSpaceshipMain {
	
	/**
	 * Depending on how you design your code, you might or might not
	 * need to modify this function.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Cool Spaceships");
		frame.setSize(800, 600);
		SpaceshipComponent component = new SpaceshipComponent();
		frame.add(component);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
