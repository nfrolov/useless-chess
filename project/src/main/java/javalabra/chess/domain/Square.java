package javalabra.chess.domain;

/**
 * Represents one square on the board.
 *
 * @author Nikita Frolov
 */
public class Square {

	private final int column; // file
	private final int row; // rank

	/**
	 * Piece occupuing square.
	 */
	private Piece piece;

	/**
	 * Constructs square for specified column and row.
	 *
	 * @param	column
	 * @param	row
	 */
	public Square(int column, int row) {
		this.column = column;
		this.row = row;
		this.piece = null;
	}

	/**
	 * Constructs copy of square.
	 *
	 * @param	origin	original square
	 */
	public Square(final Square origin) {
		column = origin.column;
		row = origin.row;
		piece = origin.piece;
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	/**
	 * Returns human-readable name for the column.
	 *
	 * @return
	 */
	public char getFile() {
		return (char) ('a' + column);
	}

	/**
	 * Returns human-readable name for the row.
	 *
	 * @return
	 */
	public char getRank() {
		return (char) ('1' + row);
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public boolean isOccupied() {
		return null != piece;
	}

	public boolean isWhite() {
		return (row + column) % 2 == 0;
	}

	@Override
	public String toString() {
		return String.format("%c%c", getFile(), getRank());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Square other = (Square) obj;
		if (column != other.column) {
			return false;
		}
		if (row != other.row) {
			return false;
		}
		return true;
	}

}
