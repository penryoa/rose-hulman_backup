package linearLightsOut;

import java.util.Random;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
Stage 0: Examine the main method in the LinearMain class in the linearLightsOut package in the LinearLightsOut project 
         that you checked out in class. This is all the code that we supply for the project — the rest is your responsibility.
Stage 1: Display a frame with the right title.
Stage 2: Display the right number of buttons in the frame (see the nButtons variable in LinearMain.main) 
         without worrying about event handling or the symbols on the buttons. 
         For full credit, your final solution must work with any nButtons greater than 2.
Stage 3: Make sure the buttons are initialized to random symbols (Xs and Os, 50% probability each).
Stage 4: Implement a working Quit button (This involves implementing an event handler for the Quit button).
Stage 5: Implement a working New Game button. When the button it pressed, the game should reset the symbol buttons 
         to a new set of random symbols.
Stage 6: Set up event handlers for the symbol buttons that correctly toggle the symbols as described above.
Stage 7: Check for a win and notify the player in some way through the GUI (not simply by System.out.println). 
         Changing the window title would suffice. (If you do that, be sure to change it back when the player clicks New Game.)
 */

/**
 * Runs the Linear Lights Out application.
 * 
 * @author <Olivia Penry, worked with Anna Thompson (from Yoder's class)>.
 *         Created Jan 18, 2010.
 */
public class LinearMain {

	/**
	 * This returns X or O, randomly of course.
	 * 
	 * @return String
	 */
	public static String getRandom() {
		String symbol;
		Random rand = new Random();
		int n = rand.nextInt(100);
		if (n < 50) {
			symbol = "X";
		} else {
			symbol = "O";
		}
		return symbol;
	}

	/**
	 * This changes a button's symbol from and X to and O (and vice versa).
	 * 
	 * @return void
	 */

	public static void changeSymbol(JButton button) {
		if (button.getText().equals("X")) {
			button.setText("O");
		} else {
			button.setText("X");
		}
	}

	/**
	 * This checks to see if the buttons are all X's or O's.
	 * 
	 * @return boolean
	 */
	public static boolean isWin(ArrayList<JButton> buttons) {
		for (int k = 0; k < buttons.size() - 1; k++) {
			if (buttons.get(k).getText() != buttons.get(k + 1).getText()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * If it turns out that you did win, this will change the frame title to honor your valiant deed.
	 * 
	 * @return void
	 */
	public static void checkForWin(ArrayList<JButton> buttons, JFrame frame) {
		if (isWin(buttons)) {
			frame.setTitle("You won! Congrats. Buy yourself something nice.");
		}
	}

	/**
	 * Main starts the whole thing!
	 */
	
	
	public static void main(String[] args) {
		String nButtonsString = JOptionPane.showInputDialog("How many buttons would you like?");
		int nButtons = Integer.parseInt(nButtonsString);

		JPanel panel_1 = new JPanel();
		JFrame frame = new JFrame();
		frame.setTitle("Linear Lights Out!");

		ArrayList<JButton> buttons = new ArrayList<JButton>();

		for (int k = 0; k < nButtons; k++) {
			String symbol = getRandom();
			JButton b = new JButton(symbol);
			panel_1.add(b);
			buttons.add(b);
		}

		JPanel panel_2 = new JPanel();

		JButton newGameButton = new JButton("New Game");
		panel_2.add(newGameButton, BorderLayout.SOUTH);

		JButton quitButton = new JButton("Quit");
		panel_2.add(quitButton, BorderLayout.SOUTH);

		/**
		 * This class implements ActionListener! 
		 */
		
		
		class ButtonHandler implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent event) {
				JButton clickedButton = (JButton) event.getSource();
				if (clickedButton.equals(newGameButton)) {
					frame.setTitle("Linear Lights Out!");
					for (JButton button : buttons) {
						String symbol = getRandom();
						button.setText(symbol);
					}
					checkForWin(buttons, frame);
				}
				if (clickedButton.equals(quitButton)) {
					frame.dispose();
				}

				if (buttons.contains(clickedButton)) {
					frame.setTitle("Linear Lights Out!");
					for (int k = 0; k < buttons.size(); k++) {
						JButton currentButton = buttons.get(k);
						if (currentButton == clickedButton) {
							changeSymbol(currentButton);
							if (k == 0) {
								changeSymbol(buttons.get(k + 1));
								break;
							}
							if (k == buttons.size() - 1) {
								changeSymbol(buttons.get(k - 1));
								break;
							}
							changeSymbol(buttons.get(k + 1));
							changeSymbol(buttons.get(k - 1));
						}
					}
					checkForWin(buttons, frame);
				}
			}
		}

		ButtonHandler handler = new ButtonHandler();

		newGameButton.addActionListener(handler);
		quitButton.addActionListener(handler);

		for (JButton button : buttons) {
			button.addActionListener(handler);
		}

		frame.add(panel_1, BorderLayout.NORTH);
		frame.add(panel_2, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
