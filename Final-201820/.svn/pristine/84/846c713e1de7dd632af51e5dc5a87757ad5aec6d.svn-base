package refactoring;

public class Rock extends Tile {

	int x, y, dy;

	public Rock(int x, int y) {
		super(x, y);
		this.dy = 1;
	}

	public void fall() {
		this.y += dy;
		System.out.println("Rock is falling " + dy);
	}

	@Override
	public void mutate() {
		this.dy *= -1;
		System.out.println("dy is now " + this.dy);
	}

}
