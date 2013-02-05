package javalabra.chess.domain;

import java.util.Collection;
import java.util.HashSet;

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

	public Board(final Board origin) {
		squares = new Square[64];
		for (int i = 0; i < origin.squares.length; ++i) {
			squares[i] = new Square(origin.squares[i]);
		}
	}

	public Square getSquare(int column, int row) {
		return squares[row * 8 + column];
	}

	public void setSquare(final int column, final int row, final Piece piece) {
		getSquare(column, row).setPiece(piece);
	}

	public void setSquare(final Square square, final Piece piece) {
		setSquare(square.getColumn(), square.getRow(), piece);
	}

	public Square getPiecePosition(final Piece piece) {
		for (final Square square : squares) {
			if (piece == square.getPiece()) {
				return square;
			}
		}
		return null;
	}

	@Deprecated
	public void setPiecePosition(final Piece piece, final Square square) {
		setSquare(square, piece);
	}

	public Collection<Piece> getPieces() {
		final Collection<Piece> pieces = new HashSet<Piece>(32);

		for (final Square square : squares) {
			Piece piece = square.getPiece();
			if (null != piece) {
				pieces.add(piece);
			}
		}

		return pieces;
	}

	public Square getKingPosition(Color color) {
		// FIXME find better way
		for (final Square square : squares) {
			Piece piece = square.getPiece();
			if (null != piece && piece.getColor() == color && piece instanceof King) {
				return square;
			}
		}
		throw new IllegalStateException("cannot find king");
	}

	public Board copy() {
		return new Board(this);
	}

}
