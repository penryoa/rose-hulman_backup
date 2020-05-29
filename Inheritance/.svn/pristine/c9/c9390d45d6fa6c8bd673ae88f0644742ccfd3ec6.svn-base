package chessPieces;

public class Rook extends AbstractChessPiece{
	
	public Rook(boolean isWhite) {
		super("rook", isWhite);
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
