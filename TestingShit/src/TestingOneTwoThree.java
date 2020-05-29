import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
//import java.util.Set;
import java.util.TreeMap;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.geom.Line2D;
//import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestingOneTwoThree {

	public static void main(String[] args) {

		int time_units = 400;
		int times_to_sim = 50000;
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();

		ArrayList<Integer> results = new ArrayList<>();

		// simulateInTimeUnits(time_units);

		/*
		 * simulateXTimes is question 2, but you don't have to use 50000 times.
		 * You can pick whatever.
		 */
		simulateXTimes(times_to_sim, time_units, results);

		/*
		 * dataPoints fills in a map. key of map: the displacement from running
		 * 400 times value of map: the number of times that displacement appears
		 * after 50000 runs
		 */
		dataPoints(times_to_sim, results, map);

		/*
		 * calculateMSD will use the map from line 25 and calculate the MSD.
		 */
		double MSD = calculateMSD(map);

		System.out.println("MSD estimate: around " + 0);
		System.out.println("MSD calculation: " + MSD);
		System.out.println();
		
		/**
		 * An attempt to draw the plot, but a failed one. I've spent a really long time 
		 * trying to make this work but never succeeded. 
		 */
		drawPlot(map);

	}

	public static int simulateInTimeUnits(int time_units) {
		int total_displacement = 0;
		for (int i = 0; i < time_units; i++) {
			int displacement = getRandomDisplacement();
			total_displacement += displacement;
		}

		return total_displacement;
	}

	public static void simulateXTimes(int times_to_sim, int time_units, ArrayList<Integer> results) {
		for (int i = 0; i < times_to_sim; i++) {
			results.add(simulateInTimeUnits(time_units));
		}
	}

	public static int getRandomDisplacement() {
		int displacement;

		Random rand = new Random();
		double r = rand.nextDouble();

		if (r < 0.5) {
			displacement = 1;
		} else {
			displacement = -1;
		}

		return displacement;
	}

	public static void dataPoints(int array_size, ArrayList<Integer> array, Map<Integer, Integer> map) {
		int sum = 0;
		for (int i = 0; i < array_size; i++) {
			int key = array.get(i);
			if (!map.containsKey(key)) {
				int value = 0;
				for (int j = i; j < array_size; j++) {
					if (array.get(j).equals(key)) {
						value++;
					}
				}
				sum += value;
				map.put(key, value);
			}
		}

		System.out.println("Data: " + map.toString());
	}

	public static double calculateMSD(Map<Integer, Integer> map) {
		Object[] values = map.values().toArray();
		int sum = 0;
		for (int i = 0; i < values.length - 1; i++) {
			int current = (int) values[i];
			int next = (int) values[i + 1];
			double MSD = Math.pow(current + next, 2);
			sum += MSD;
		}
		return Math.sqrt(sum) / (values.length);
	}
	
	
	int x1;
	int y1;
	int x2;
	int y2;
	
	public static void drawPlot(Map<Integer, Integer> map) {
		JFrame frame = new JFrame("Total Displacement (X), Number of Occurences(Y)");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		JPanel panel = new JPanel();
		frame.add(panel);
		frame.setVisible(true);
		Graphics2D g2 = (Graphics2D) panel.getGraphics();

		Object[] keys = map.keySet().toArray();
		for (int i = 1; i < map.size(); i++) {
			int x1 = i-1;
			int y1 = map.get(keys[i-1]);
			int x2 = i;
			int y2 = map.get(keys[i]);
			System.out.println("Line from (" + x1 + ", " + y1 + ") to (" + x2 + ", " + y2 + ")");
			g2.drawLine(x1, y1, x2, y2);
			//panel.update(g2);
			//g2.draw(new Line2D.Double(x1, y1, x2, y2));
			//panel.update(g2);
			panel.repaint();
		}
	}
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.drawLine(x1, y1, x2, y2);
	}

	// public static void drawData(Map<Integer, Integer> map, int width, int
	// height) {
	//
	// JFrame frame = new JFrame("Total Displacement (X), Number of
	// Occurences(Y)");
	// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// frame.setSize(width, height);
	// JPanel panel = new JPanel();
	// frame.add(panel);
	// frame.setVisible(true);
	// JComponent c = new component();
	// Graphics g = panel.getGraphics();
	// g.drawOval(25, 25, 25, 25);
	// frame.pack();
	//
	// }
	//
	// class component extends JComponent {
	//
	// public component() {
	//
	// }
	//
	// @Override
	// protected void paintComponent(Graphics g) {
	// super.paintComponent(g);
	//
	// }
	//
	// }


	
}