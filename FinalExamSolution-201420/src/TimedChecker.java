import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * This class implements a system checker that performs a system at regular
 * intervals (e.g., every second).
 * 
 * On occasions when a system check takes longer than expected, other checks
 * should still be performed at the specified interval. Your job is to use
 * multithreading to make that happen.
 * 
 * @author TODO <YOUR_NAME_GOES_HERE>
 */
public class TimedChecker implements ActionListener {

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");

	/**
	 * 
	 * This main function just opens a window. You have to click the button to
	 * start the checking.
	 * 
	 * Once the button is pressed, the system check should run once a second.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Timed Checker");
		final JButton button = new JButton(
				"Run occasionally long checks every second");

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.setText("Running checker...(close window to stop)");
				button.setEnabled(false);
				TimedChecker checker = new TimedChecker();
				Timer timer = new Timer(1000, checker);
				timer.start();
			}

		});

		frame.add(button);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	/**
	 * 
	 * Most of your changes will probably be in this function.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Thread newThread = new Thread( new Runnable() {

			@Override
			public void run() {
				doOccasionallyLongRunningCheck();
			} 
			
		});
		newThread.start();
		// runs the system check function one time
		// doOccasionallyLongRunningCheck();

	}

	/**
	 * 
	 * Imagine this is a function that performs a system check that (under
	 * certain circumstances) could take long time to run.
	 * 
	 * Imagine that this particular check is safe to run in parallel (e.g. you
	 * can safely run two checks at the same time).
	 * 
	 * You shouldn't need to modify this function.
	 */
	public void doOccasionallyLongRunningCheck() {
		Calendar now = Calendar.getInstance();
		String currentTime = DATE_FORMAT.format(now.getTime());
		if (Math.random() < .8) {
			System.out.println(currentTime + " Doing system check...done");
		} else {
			System.out.println(currentTime
					+ " Doing system check (this could take a while)...");
			try {
				Thread.sleep(5000);
				System.out.println("System check started on " + currentTime
						+ " finally complete");
			} catch (InterruptedException e) {
				System.err.println("aborting check");
			}
		}
	}

}
