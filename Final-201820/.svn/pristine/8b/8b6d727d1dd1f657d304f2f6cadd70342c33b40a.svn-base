package refactoring;

public class Diamond extends Tile {

	public int tileX;
	public int tileY;
	public int yield;

	public Diamond(int x, int y) {
		super(x, y);
		this.yield = 1;
	}

	@Override
	public void mutate() {
		this.yield *= 2;
		System.out.println("Yield is now " + this.yield);
	}

}
