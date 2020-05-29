
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * Level is responsible for turning the letters and integers in the text files
 * to lists of points that give the initial positions of the game objects.
 * 
 * @author penryoa
 *
 */

public class Level {

	// ======= Important Objects ======= \\

	public int level;
	public FileReader currentLevelFile;
	public ArrayList<Point> fygarLocations = new ArrayList<>();
	public ArrayList<Point> pookieLocations = new ArrayList<>();
	public ArrayList<Point> tunnelLocations = new ArrayList<>();
	public ArrayList<Point> creeperLocations = new ArrayList<>();
	public ArrayList<Point> R1Locations = new ArrayList<>();
	public ArrayList<Point> R2Locations = new ArrayList<>();
	public ArrayList<Point> masterSwordLocations = new ArrayList<>();
	public ArrayList<Point> pigLocations = new ArrayList<>();
	public HashMap<Integer, String> bestScores = new HashMap<>();
	public int currentScore = 0;
	public Point heroLocation = new Point(200, 300);
	public BufferedImage image;
	public FileReader scoreFile = new FileReader("Score");

	// ======= Level Constructor ======= \\

	public Level(Graphics2D g2) throws IOException {
		this.level = 1;
		this.resetLevel();
	}

	// ======= Changing and Setting the Level ======= \\

	public int getCurrentLevel() {
		return this.level;
	}

	public void resetImage() throws IOException {
		BufferedImage image = ImageIO.read(new File("bkgd" + this.level + ".png"));
		this.image = image;
	}

	public void setToLevel(int num) throws IOException {
		this.level = num;
		this.fygarLocations.clear();
		this.pookieLocations.clear();
		this.tunnelLocations.clear();
		this.creeperLocations.clear();
		this.R1Locations.clear();
		this.R2Locations.clear();
		this.masterSwordLocations.clear();
		this.pigLocations.clear();
		this.resetLevel();
	}

	public void changeLevel(int num) throws IOException {
		if (canChangeLevel(num)) {
			this.level += num;
			this.fygarLocations.clear();
			this.pookieLocations.clear();
			this.tunnelLocations.clear();
			this.creeperLocations.clear();
			this.R1Locations.clear();
			this.R2Locations.clear();
			this.masterSwordLocations.clear();
			this.pigLocations.clear();
			this.resetLevel();
		}
	}

	public boolean canChangeLevel(int num) {
		if (this.level + num >= 1 && this.level + num <= 6) {
			return true;
		} else {
			return false;
		}
	}

	public void resetLevel() throws IOException {
		this.resetImage();

		this.currentLevelFile = new FileReader("Level " + this.level);
		Scanner s = new Scanner(currentLevelFile);

		while (s.hasNext()) {
			String line = s.nextLine();

			if (line.startsWith("F")) {
				this.fygarLocations.add(resetLevelHelper(line));

			} else if (line.startsWith("T")) {
				this.tunnelLocations.add(resetLevelHelper(line));

			} else if (line.startsWith("R")) {
				this.R2Locations.add(resetLevelHelper(line));

			} else if (line.startsWith("P")) {
				this.pookieLocations.add(resetLevelHelper(line));

			} else if (line.startsWith("H")) {
				this.heroLocation = resetLevelHelper(line);

			} else if (line.startsWith("C")) {
				this.creeperLocations.add(resetLevelHelper(line));

			} else if (line.startsWith("S")) {
				this.R1Locations.add(resetLevelHelper(line));

			} else if (line.startsWith("M")) {
				this.masterSwordLocations.add(resetLevelHelper(line));
			} else if (line.startsWith("O")) {
				this.pigLocations.add(resetLevelHelper(line));
			}
		}

		this.currentLevelFile.close();
		s.close();
	}

	public Point resetLevelHelper(String line) {
		String[] splitLine = line.split(" ");
		int x = Integer.valueOf(splitLine[1]);
		int y = Integer.valueOf(splitLine[2]);
		Point newPoint = new Point(x, y);
		return newPoint;
	}

	// ======= Score File and All That Jazz ======= \\

	public void scoreReader() throws IOException {
		Scanner s = new Scanner(this.scoreFile);

		while (s.hasNext()) {
			String line = s.nextLine();
			if (line.startsWith("C")) {
				this.currentScore += scoreReaderHelper(line);
			}
		}
		this.scoreFile.close();
		s.close();

	}

	public int scoreReaderHelper(String line) {
		String[] splitLine = line.split(" ");
		return Integer.valueOf(splitLine[1]);
	}

	public void addToScore(Integer value) throws IOException {
		Scanner s = new Scanner(this.scoreFile);
		this.currentScore = this.currentScore + value;

		while (s.hasNext()) {
			String line = s.nextLine();

			if (line.startsWith("C")) {
				line = "C " + (this.currentScore);
			}
		}

		this.scoreFile.close();
		s.close();
	}

}
