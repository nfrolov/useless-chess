package javalabra.chess.domain;

public class Square {

	private final int column; // file
	private final int row; // rank

	private Piece piece;

	public Square(int column, int row) {
		this.column = column;
		this.row = row;
		this.piece = null;
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	public char getFile() {
		return (char) ('a' + column);
	}

	public char getRank() {
		return (char) ('1' + row);
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	@Override
	public String toString() {
		return String.format("%c%c", getFile(), getRank());
	}

}
