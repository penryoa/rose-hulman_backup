package chessPieces;

public class Knight extends AbstractChessPiece {
	public Knight(boolean isWhite) {
		super("knight", isWhite);
	}
	
	@Override
	public boolean checkMove(int dx, int dy) {
		if (Math.abs(dx) == 2 * Math.abs(dy) || Math.abs(dy) == 2 * Math.abs(dx)) {
			return true;
		}
		return false;
	}
}
