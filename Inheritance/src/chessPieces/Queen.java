package chessPieces;

public class Queen extends AbstractChessPiece {


	public Queen(boolean isWhite) {
		super("queen", isWhite);
	}
	
	@Override
	public boolean checkMove(int dx, int dy) {
		if (dx == 0 || dy == 0) {
			return false;
		}
		if (dx == 0 || dy == 0 || Math.abs(dx) == Math.abs(dy)) {
			return true;
		}
		return false;
	}

}
