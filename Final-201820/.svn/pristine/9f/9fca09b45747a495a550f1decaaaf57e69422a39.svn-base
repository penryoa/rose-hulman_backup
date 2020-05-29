package refactoring;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

// FIXME: this App uses POOR DESIGN.
// Can you fix it to use GOOD DESIGN?
public class App {

	private static HashMap<Tile, Boolean> tiles;

	public static void main(String[] args) {
		tiles = new HashMap<Tile, Boolean>();

		tiles.put(new Diamond(0, 0), true);
		tiles.put(new Dirt(1, 0, Color.BLACK), true);
		tiles.put(new Dirt(0, 1, Color.BLACK), false);
		tiles.put(new Rock(1, 1), true);

		App app = new App(tiles);
		app.handleMutate(App.tiles);
		app.handlePrintPositions(App.tiles);

	}

	public App(HashMap<Tile, Boolean> tiles) {
		App.tiles = tiles;
	}

	public void handleMutate(HashMap<Tile, Boolean> tiles) {
		for (Tile tile : tiles.keySet()) {
			if (tiles.get(tile)) {
				tile.mutate();
			}
		}
	}

	public void handlePrintPositions(HashMap<Tile, Boolean> tiles) {
		for (Tile tile : tiles.keySet()) {
			tile.printPosition();
		}
	}

}
