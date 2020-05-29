package refactoring;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

public class Dirt extends Tile {

	public int x, y;
	public Color color;

	public Dirt(int x, int y, Color color) {
		super(x, y);
		this.color = color;
	}

	@Override
	public void mutate() {
		Random random = new Random();
		this.color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
		System.out.println("Color is now " + this.color);
	}

}
