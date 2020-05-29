package chessPieces;

public class Bishop extends AbstractChessPiece {

	public Bishop(boolean isWhite) {
		super("bishop", isWhite);
	}

	@Override
	public boolean checkMove(int dx, int dy) {
		if (Math.abs(dx) == Math.abs(dy)) {
			return true;
		}
		return false;
	}
}
