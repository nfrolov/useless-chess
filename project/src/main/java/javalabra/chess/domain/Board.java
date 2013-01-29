package javalabra.chess.domain;



public class Board {

	private final Square[] squares;

	public Board() {
		squares = new Square[64];
		for (int row = 0; row < 8; ++row) {
			for (int column = 0; column < 8; ++column) {
				squares[row * 8 + column] = new Square(column, row);
			}
		}
	}

	public Square getSquare(int column, int row) {
		return squares[row * 8 + column];
	}

	public Square getPiecePosition(final Piece piece) {
		for (final Square square : squares) {
			if (piece == square.getPiece()) {
				return square;
			}
		}
		return null;
	}

	public void setPiecePosition(final Piece piece, final Square square) {
		square.setPiece(piece);
	}

}
