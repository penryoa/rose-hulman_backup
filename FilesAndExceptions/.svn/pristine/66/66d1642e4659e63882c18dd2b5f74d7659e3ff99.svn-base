import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;


public class Counter implements ActionListener {

	JButton button;
	int count;
	File countFile;
	
	public Counter(JButton button) {
		this.button = button;
		this.countFile = new File("count.txt");
		
		try {
			Scanner in = new Scanner(this.countFile);
			count = in.nextInt();
			in.close();
		} catch (FileNotFoundException e) {
			this.count = 0;
		} finally {
			button.setText(this.count + "");
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		count++;
		button.setText(count + "");
		
		
		try {
			PrintWriter writer = new PrintWriter(countFile);
			writer.println(count);
			writer.close();
		} catch (FileNotFoundException e) {
			this.count = 0;
		}
	}

}
