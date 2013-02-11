package javalabra.chess.domain;

import java.util.Collection;
import java.util.HashSet;

/**
 * Represents chessboard.
 *
 * @author Nikita Frolov
 */

public class Board {

	private final Square[] squares;

	/**
	 * Creates empty board.
	 */
	public Board() {
		squares = new Square[64];
		for (int row = 0; row < 8; ++row) {
			for (int column = 0; column < 8; ++column) {
				squares[row * 8 + column] = new Square(column, row);
			}
		}
	}

	/**
	 * Copy constructor.
	 *
	 * @param	origin	board to copy from
	 */
	public Board(final Board origin) {
		squares = new Square[64];
		for (int i = 0; i < origin.squares.length; ++i) {
			squares[i] = new Square(origin.squares[i]);
		}
	}

	/**
	 * Returns square by coordinates.
	 *
	 * @param	column	column, same as file - 1
	 * @param	row		row, same as rank - 1
	 * @return			square on specified coordinates
	 */
	public Square getSquare(int column, int row) {
		return squares[row * 8 + column];
	}

	/**
	 * Sets piece on square specified by coordinates, null marks square as empty.
	 *
	 * @param	column
	 * @param	row
	 * @param	piece	piece to be set
	 */
	public void setSquare(final int column, final int row, final Piece piece) {
		getSquare(column, row).setPiece(piece);
	}

	/**
	 * Sets piece on specified square, null marks square as empty.
	 *
	 * @param square	occupying square
	 * @param piece		piece to be set
	 */
	public void setSquare(final Square square, final Piece piece) {
		setSquare(square.getColumn(), square.getRow(), piece);
	}

	/**
	 * Finds concrete piece's position on board.
	 *
	 * @param	piece	piece to look for
	 * @return			piece's position or null if not found
	 */
	public Square getPiecePosition(final Piece piece) {
		for (final Square square : squares) {
			if (piece == square.getPiece()) {
				return square;
			}
		}
		return null;
	}

	/**
	 * Sets piece on specified square.
	 *
	 * @param piece		piece to be set
	 * @param square	occupying square
	 */
	@Deprecated
	public void setPiecePosition(final Piece piece, final Square square) {
		setSquare(square, piece);
	}

	/**
	 * Returns all the pieces on the board.
	 *
	 * @param	color
	 * @return			list of pieces
	 */
	public Collection<Piece> getPieces(final Color color) {
		final Collection<Piece> pieces = new HashSet<Piece>(32);

		for (final Square square : squares) {
			Piece piece = square.getPiece();
			if (null != piece && color == piece.getColor()) {
				pieces.add(piece);
			}
		}

		return pieces;
	}

	/**
	 * Finds king's position.
	 *
	 * @param	color	color to look for
	 * @return			king piece
	 */
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

	/**
	 * Returns copy of the board.
	 *
	 * @return			copy of the board
	 */
	public Board copy() {
		return new Board(this);
	}

}
