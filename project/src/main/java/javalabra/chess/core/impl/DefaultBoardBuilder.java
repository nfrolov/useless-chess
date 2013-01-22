package javalabra.chess.core.impl;

import java.util.Collection;

import javalabra.chess.core.BoardBuilder;
import javalabra.chess.domain.Bishop;
import javalabra.chess.domain.Board;
import javalabra.chess.domain.Color;
import javalabra.chess.domain.King;
import javalabra.chess.domain.Knight;
import javalabra.chess.domain.Pawn;
import javalabra.chess.domain.Piece;
import javalabra.chess.domain.PlayerBlack;
import javalabra.chess.domain.PlayerWhite;
import javalabra.chess.domain.Queen;
import javalabra.chess.domain.Rook;

public class DefaultBoardBuilder implements BoardBuilder {

	public Board build(PlayerWhite white, PlayerBlack black) {
		final Board board = new Board();

		build(board, white.getPieces(), black.getPieces());

		return board;
	}

	private void build(Board board, Collection<Piece> whites, Collection<Piece> blacks) {
		assert whites.isEmpty() && blacks.isEmpty();

		createPawnRow(Color.WHITE, board, whites);
		createMainRow(Color.WHITE, board, whites);

		createPawnRow(Color.BLACK, board, blacks);
		createMainRow(Color.BLACK, board, blacks);

		assert 16 == whites.size() && 16 == blacks.size();
	}

	private void createMainRow(Color color, Board board, Collection<Piece> pieces) {
		final int row = Color.WHITE == color ? 0 : 7;

		pieces.add(new Rook(color, board.getSquare(0, row)));
		pieces.add(new Knight(color, board.getSquare(1, row)));
		pieces.add(new Bishop(color, board.getSquare(2, row)));

		if (Color.WHITE == color) {
			pieces.add(new Queen(color, board.getSquare(3, row)));
			pieces.add(new King(color, board.getSquare(4, row)));
		} else {
			pieces.add(new King(color, board.getSquare(3, row)));
			pieces.add(new Queen(color, board.getSquare(4, row)));
		}

		pieces.add(new Bishop(color, board.getSquare(5, row)));
		pieces.add(new Knight(color, board.getSquare(6, row)));
		pieces.add(new Rook(color, board.getSquare(7, row)));
	}

	private void createPawnRow(Color color, Board board, Collection<Piece> pieces) {
		final int row = Color.WHITE == color ? 1 : 6;
		for (int col = 0; col < 8; ++col) {
			pieces.add(new Pawn(color, board.getSquare(col, row)));
		}
	}

}
