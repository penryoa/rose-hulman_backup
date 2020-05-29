package chessPieces;

public class King extends AbstractChessPiece {
	
	public King(boolean isWhite) {
		super("king", isWhite);
	}
	
	@Override
	public boolean checkMove(int dx, int dy) {
		dx = Math.abs(dx);
		dy = Math.abs(dy);
		return dx <= 1 && dy <= 1;
	}
}
