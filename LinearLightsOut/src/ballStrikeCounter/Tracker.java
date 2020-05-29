package ballStrikeCounter;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Tracker {
	private JLabel label = new JLabel();

	// Call this to update the text on the label.
	public void updateLabel(int numBalls, int numStrikes) {
		this.label.setText("<html>Balls: " + numBalls + "<br />Strikes: " + numStrikes + "</HTML>");
	}

	public static void main(String[] args) {
		new Tracker();
	}

	public Tracker() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		frame.add(label, BorderLayout.CENTER);
		frame.add(panel, BorderLayout.SOUTH);
		
		// TODO: Add buttons and listeners to make things work.
		
		JButton addBallButton = new JButton("Add Ball");
		panel.add(addBallButton);
		
		JButton addStrikeButton = new JButton("Add Strike");
		panel.add(addStrikeButton);

		class ButtonHandler implements ActionListener {
			int numBalls = 0;
			int numStrikes = 0;
			
			@Override
			public void actionPerformed(ActionEvent event) {
				JButton clickedButton = (JButton) event.getSource();
				if (clickedButton.equals(addBallButton)) {
					numBalls++;
					if (numBalls == 5) {
						numBalls = 0;
						numStrikes = 0;
					}
					updateLabel(numBalls, numStrikes);
				}
				if (clickedButton.equals(addStrikeButton)){
					numStrikes++;
					if (numStrikes == 4) {
						numBalls = 0;
						numStrikes = 0;
					}
					updateLabel(numBalls, numStrikes);
				}	
			}	
		}
		
		ButtonHandler handler = new ButtonHandler();
		
		addBallButton.addActionListener(handler);
		addStrikeButton.addActionListener(handler);
		
		
		
		// The following line is given to show you how to use the given method:
		updateLabel(0, 0);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
