package clicker;
import javax.swing.JFrame;


public class ClickerViewer {

	/**
	 * The width of the frame.
	 */
	public static final int WIDTH = 800;
	/**
	 * The height of the frame.
	 */
	public static final int HEIGHT = 600;


	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.setSize(WIDTH, HEIGHT);
		frame.setTitle("Clickers");
		ClickerComponent clickerComponent = new ClickerComponent();
		
		frame.add(clickerComponent);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
