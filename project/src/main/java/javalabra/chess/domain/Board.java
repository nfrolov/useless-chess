package javalabra.chess.domain;

import java.util.Collection;

public class Board {

	private final Square[][] squares;

	public Board(final Player white, final Player black) {
		squares = new Square[8][8];
		for (int row = 0; row < 8; ++row) {
			for (int column = 0; column < 8; ++column) {
				squares[row][column] = new Square(column, row);
			}
		}
		setup(white.getPieces(), black.getPieces());
	}

	public Square getSquare(int column, int row) {
		return squares[row][column];
	}

	private void setup(final Collection<Piece> whites, final Collection<Piece> blacks) {
		assert whites.isEmpty() && blacks.isEmpty();

		for (int col = 0; col < 8; ++col) {
			whites.add(new Pawn(Color.WHITE, squares[1][col]));
		}

		whites.add(new Rook(Color.WHITE, squares[0][0]));
		whites.add(new Knight(Color.WHITE, squares[0][1]));
		whites.add(new Bishop(Color.WHITE, squares[0][2]));
		whites.add(new Queen(Color.WHITE, squares[0][3]));
		whites.add(new King(Color.WHITE, squares[0][4]));
		whites.add(new Bishop(Color.WHITE, squares[0][5]));
		whites.add(new Knight(Color.WHITE, squares[0][6]));
		whites.add(new Rook(Color.WHITE, squares[0][7]));

		for (int col = 0; col < 8; ++col) {
			blacks.add(new Pawn(Color.BLACK, squares[6][col]));
		}

		blacks.add(new Rook(Color.BLACK, squares[7][0]));
		blacks.add(new Knight(Color.BLACK, squares[7][1]));
		blacks.add(new Bishop(Color.BLACK, squares[7][2]));
		blacks.add(new King(Color.BLACK, squares[7][3]));
		blacks.add(new Queen(Color.BLACK, squares[7][4]));
		blacks.add(new Bishop(Color.BLACK, squares[7][5]));
		blacks.add(new Knight(Color.BLACK, squares[7][6]));
		blacks.add(new Rook(Color.BLACK, squares[7][7]));

		assert 16 == whites.size() && 16 == blacks.size();
	}

}
