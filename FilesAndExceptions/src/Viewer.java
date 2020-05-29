import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Viewer {
	public Viewer() throws FileNotFoundException {
		JFrame frame = new JFrame();
		frame.setTitle("File button counting program");
		
		JButton button =  new JButton("B");
		frame.add(button);
		
		ActionListener counter = new Counter(button);
		button.addActionListener(counter);
		
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		try {
			new Viewer();
		} catch (FileNotFoundException e) {
			File count = new File("count.txt");
			PrintWriter writer = new PrintWriter(count);
			writer.println(0);
			writer.close();
			
		}
		
		
	}
}
