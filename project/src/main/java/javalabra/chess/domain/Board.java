package javalabra.chess.domain;


public class Board {

	private final Square[][] squares;

	public Board() {
		squares = new Square[8][8];
		for (int row = 0; row < 8; ++row) {
			for (int column = 0; column < 8; ++column) {
				squares[row][column] = new Square(column, row);
			}
		}
	}

	public Square getSquare(int column, int row) {
		return squares[row][column];
	}

}
