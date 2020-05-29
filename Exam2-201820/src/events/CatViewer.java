package events;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CatViewer {

	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;
	
	public void makeWindow() {
		
		JFrame frame = new JFrame();

		frame.setTitle("Curie Cat");

		CatComponent c = new CatComponent();

		c.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.add(c);

		JPanel panel = new JPanel();

		JButton leftButton = new JButton("Left");
		JButton rightButton = new JButton("Right");
		JButton upButton = new JButton("Up");
		JButton downButton = new JButton("Down");

		class ButtonHandler implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent event) {
				JButton clickedButton = (JButton) event.getSource();
				
				if (clickedButton.equals(leftButton)) {
					int startX = c.roomba.position.x; int startY = c.roomba.position.y;
					c.addLine(startX, startY, startX-10, startY);
					c.updatePosition(-10, 0);
					c.repaint();
				}
				if (clickedButton.equals(rightButton)) {
					int startX = c.roomba.position.x; int startY = c.roomba.position.y;
					c.addLine(startX, startY, startX+10, startY);
					c.updatePosition(10, 0);
					c.repaint();
				}
				if (clickedButton.equals(upButton)) {
					int startX = c.roomba.position.x; int startY = c.roomba.position.y;
					c.addLine(startX, startY, startX, startY-10);
					c.updatePosition(0, -10);
					c.repaint();
				}
				if (clickedButton.equals(downButton)) {
					int startX = c.roomba.position.x; int startY = c.roomba.position.y;
					c.addLine(startX, startY, startX, startY+10);
					c.updatePosition(0, 10);
					c.repaint();
				}

			}
		}

		ButtonHandler handler = new ButtonHandler();

		leftButton.addActionListener(handler);
		rightButton.addActionListener(handler);
		upButton.addActionListener(handler);
		downButton.addActionListener(handler);

		frame.add(panel, BorderLayout.SOUTH);
		panel.add(leftButton);
		panel.add(rightButton);
		panel.add(upButton);
		panel.add(downButton);

		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		CatViewer viewer = new CatViewer();
		viewer.makeWindow();
	}

}
