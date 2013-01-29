package javalabra.chess.core.impl;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Set;

import javalabra.chess.domain.Board;
import javalabra.chess.domain.Color;
import javalabra.chess.domain.Move;
import javalabra.chess.domain.Pawn;
import javalabra.chess.domain.Piece;
import javalabra.chess.domain.Square;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;

public class DefaultMoveDirectorTest {

	public static class MoveToMatcher extends TypeSafeMatcher<Move> {

		private final int col, row;

		public MoveToMatcher(int col, int row) {
			this.col = col;
			this.row = row;
		}

		@Override
		public void describeTo(Description desc) {
			desc.appendText(String.format("%c%c", 'a' + col, '1' + row));
		}

		@Override
		protected boolean matchesSafely(Move move) {
			Square destination = move.getDestination();
			return destination.getColumn() == col && destination.getRow() == row;
		}

		@Override
		protected void describeMismatchSafely(Move move, Description desc) {
			desc.appendText(move.getDestination().toString());
		}

	}

	public static Matcher<? super Move> moveTo(int col, int row) {
		return new MoveToMatcher(col, row);
	}

	Board board;
	DefaultMoveDirector director;

	Set<Move> moves;
	Piece piece;

	@Before
	public void setUp() throws Exception {
		board = new Board();
		director = new DefaultMoveDirector(board);
		moves = null;
		piece = null;
	}

	@Test
	public void whitePawnCanMakeTwoMovesFromInitialPosition() {
		piece = new Pawn(Color.WHITE);
		board.setPiecePosition(piece, board.getSquare(1, 1));

		moves = piece.getLegalMoves(director);

		assertThat(moves, hasSize(2));
		assertThat(moves, hasItems(moveTo(1, 2), moveTo(1, 3)));
	}

	@Test
	public void whitePawnCanMakeOneMoveFromInitialPositionIfSecondSquareIsOccupied() {
		piece = new Pawn(Color.WHITE);
		board.setPiecePosition(piece, board.getSquare(1, 1));
		board.setPiecePosition(new Pawn(Color.BLACK), board.getSquare(1, 3));

		moves = piece.getLegalMoves(director);

		assertThat(moves, hasSize(1));
		assertThat(moves, hasItems(moveTo(1, 2)));
	}

	@Test
	public void whitePawnCanMakeNoMovesFromInitialPositionIfBlocked() {
		piece = new Pawn(Color.WHITE);
		board.setPiecePosition(piece, board.getSquare(1, 1));
		board.setPiecePosition(new Pawn(Color.BLACK), board.getSquare(1, 2));

		moves = piece.getLegalMoves(director);

		assertThat(moves, hasSize(0));
	}

	@Test
	public void whitePawnCanMakeOneMoveFromOtherPosition() {
		piece = new Pawn(Color.WHITE);
		board.setPiecePosition(piece, board.getSquare(1, 2));

		moves = piece.getLegalMoves(director);

		assertThat(moves, hasSize(1));
		assertThat(moves, hasItems(moveTo(1, 3)));
	}

	@Test
	public void whitePawnCanMakeNoMovesIfBlocked() {
		piece = new Pawn(Color.WHITE);
		board.setPiecePosition(piece, board.getSquare(1, 2));
		board.setPiecePosition(new Pawn(Color.BLACK), board.getSquare(1, 3));

		moves = piece.getLegalMoves(director);

		assertThat(moves, hasSize(0));
	}

	@Test
	public void whitePawnCanCaptureBothDirections() {
		piece = new Pawn(Color.WHITE);
		board.setPiecePosition(piece, board.getSquare(1, 2));
		board.setPiecePosition(new Pawn(Color.BLACK), board.getSquare(0, 3));
		board.setPiecePosition(new Pawn(Color.BLACK), board.getSquare(2, 3));

		moves = piece.getLegalMoves(director);

		assertThat(moves, hasSize(3));
		assertThat(moves, hasItems(moveTo(0, 3), moveTo(1, 3), moveTo(2, 3)));
	}

	@Test
	public void whitePawnCanCaptureBothDirectionsEvenIfBlocked() {
		piece = new Pawn(Color.WHITE);
		board.setPiecePosition(piece, board.getSquare(1, 2));
		board.setPiecePosition(new Pawn(Color.BLACK), board.getSquare(0, 3));
		board.setPiecePosition(new Pawn(Color.BLACK), board.getSquare(1, 3));
		board.setPiecePosition(new Pawn(Color.BLACK), board.getSquare(2, 3));

		moves = piece.getLegalMoves(director);

		assertThat(moves, hasSize(2));
		assertThat(moves, hasItems(moveTo(0, 3), moveTo(2, 3)));
	}

	@Test
	public void blackPawnCanMakeTwoMovesFromInitialPosition() {
		piece = new Pawn(Color.BLACK);
		board.setPiecePosition(piece, board.getSquare(1, 6));

		moves = piece.getLegalMoves(director);

		assertThat(moves, hasSize(2));
		assertThat(moves, hasItems(moveTo(1, 5), moveTo(1, 4)));
	}

	@Test
	public void blackPawnCanMakeOneMoveFromOtherPosition() {
		piece = new Pawn(Color.BLACK);
		board.setPiecePosition(piece, board.getSquare(1, 5));

		moves = piece.getLegalMoves(director);

		assertThat(moves, hasSize(1));
		assertThat(moves, hasItems(moveTo(1, 4)));
	}

	@Test
	public void blackPawnCanMakeNoMovesFromInitialPositionIfBlocked() {
		piece = new Pawn(Color.BLACK);
		board.setPiecePosition(piece, board.getSquare(1, 6));
		board.setPiecePosition(new Pawn(Color.BLACK), board.getSquare(1, 5));

		moves = piece.getLegalMoves(director);

		assertThat(moves, hasSize(0));
	}

	@Test
	public void blackPawnCanMakeOneMoveFromInitialPositionIfSecondSquareIsOccupied() {
		piece = new Pawn(Color.BLACK);
		board.setPiecePosition(piece, board.getSquare(1, 6));
		board.setPiecePosition(new Pawn(Color.WHITE), board.getSquare(1, 4));

		moves = piece.getLegalMoves(director);

		assertThat(moves, hasSize(1));
		assertThat(moves, hasItems(moveTo(1, 5)));
	}

	@Test
	public void blackPawnCanMakeNoMovesIfBlocked() {
		piece = new Pawn(Color.BLACK);
		board.setPiecePosition(piece, board.getSquare(1, 6));
		board.setPiecePosition(new Pawn(Color.WHITE), board.getSquare(1, 5));

		moves = piece.getLegalMoves(director);

		assertThat(moves, hasSize(0));
	}

	@Test
	public void blackPawnCanCaptureBothDirections() {
		piece = new Pawn(Color.BLACK);
		board.setPiecePosition(piece, board.getSquare(1, 5));
		board.setPiecePosition(new Pawn(Color.WHITE), board.getSquare(0, 4));
		board.setPiecePosition(new Pawn(Color.WHITE), board.getSquare(2, 4));

		moves = piece.getLegalMoves(director);

		assertThat(moves, hasSize(3));
		assertThat(moves, hasItems(moveTo(0, 4), moveTo(1, 4), moveTo(2, 4)));
	}

	@Test
	public void blackPawnCanCaptureBothDirectionsEvenIfBlocked() {
		piece = new Pawn(Color.BLACK);
		board.setPiecePosition(piece, board.getSquare(1, 5));
		board.setPiecePosition(new Pawn(Color.WHITE), board.getSquare(0, 4));
		board.setPiecePosition(new Pawn(Color.WHITE), board.getSquare(1, 4));
		board.setPiecePosition(new Pawn(Color.WHITE), board.getSquare(2, 4));

		moves = piece.getLegalMoves(director);

		assertThat(moves, hasSize(2));
		assertThat(moves, hasItems(moveTo(0, 4), moveTo(2, 4)));
	}

}
