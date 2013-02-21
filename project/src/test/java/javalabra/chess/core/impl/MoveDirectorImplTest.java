package javalabra.chess.core.impl;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import javalabra.chess.core.GameContext;
import javalabra.chess.domain.Bishop;
import javalabra.chess.domain.Board;
import javalabra.chess.domain.Color;
import javalabra.chess.domain.King;
import javalabra.chess.domain.Knight;
import javalabra.chess.domain.Move;
import javalabra.chess.domain.Pawn;
import javalabra.chess.domain.Piece;
import javalabra.chess.domain.Queen;
import javalabra.chess.domain.Rook;
import javalabra.chess.domain.Square;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("unchecked")
public class MoveDirectorImplTest {

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
	MoveDirectorImpl director;
	GameContext context;

	Collection<Move> moves;
	Piece piece;

	@Before
	public void setUp() throws Exception {
		board = new Board();
		context = new GameContextImpl(board);
		director = new MoveDirectorImpl();
		moves = null;
		piece = null;
	}

	@Test
	public void whitePawnCanMakeTwoMovesFromInitialPosition() {
		piece = new Pawn(Color.WHITE);
		board.setSquare(1, 1, piece);
		board.setSquare(1, 1, piece);

		moves = piece.getLegalMoves(director, context);

		assertThat(moves, hasSize(2));
		assertThat(moves, hasItems(
				moveTo(1, 2), moveTo(1, 3)
				));
	}

	@Test
	public void whitePawnCanMakeOneMoveFromInitialPositionIfSecondSquareIsOccupied() {
		piece = new Pawn(Color.WHITE);
		board.setSquare(1, 1, piece);
		board.setSquare(1, 3, new Pawn(Color.BLACK));

		moves = piece.getLegalMoves(director, context);

		assertThat(moves, hasSize(1));
		assertThat(moves, hasItems(
				moveTo(1, 2)
				));
	}

	@Test
	public void whitePawnCanMakeNoMovesFromInitialPositionIfBlocked() {
		piece = new Pawn(Color.WHITE);
		board.setSquare(1, 1, piece);
		board.setSquare(1, 2, new Pawn(Color.BLACK));

		moves = piece.getLegalMoves(director, context);

		assertThat(moves, hasSize(0));
	}

	@Test
	public void whitePawnCanMakeOneMoveFromOtherPosition() {
		piece = new Pawn(Color.WHITE);
		board.setSquare(1, 2, piece);

		moves = piece.getLegalMoves(director, context);

		assertThat(moves, hasSize(1));
		assertThat(moves, hasItems(
				moveTo(1, 3)
				));
	}

	@Test
	public void whitePawnCanMakeNoMovesIfBlocked() {
		piece = new Pawn(Color.WHITE);
		board.setSquare(1, 2, piece);
		board.setSquare(1, 3, new Pawn(Color.BLACK));

		moves = piece.getLegalMoves(director, context);

		assertThat(moves, hasSize(0));
	}

	@Test
	public void whitePawnCanCaptureBothDirections() {
		piece = new Pawn(Color.WHITE);
		board.setSquare(1, 2, piece);
		board.setSquare(0, 3, new Pawn(Color.BLACK));
		board.setSquare(2, 3, new Pawn(Color.BLACK));

		moves = piece.getLegalMoves(director, context);

		assertThat(moves, hasSize(3));
		assertThat(moves, hasItems(
				moveTo(0, 3), moveTo(1, 3), moveTo(2, 3)
				));
	}

	@Test
	public void whitePawnCanCaptureBothDirectionsEvenIfBlocked() {
		piece = new Pawn(Color.WHITE);
		board.setSquare(1, 2, piece);
		board.setSquare(0, 3, new Pawn(Color.BLACK));
		board.setSquare(1, 3, new Pawn(Color.BLACK));
		board.setSquare(2, 3, new Pawn(Color.BLACK));

		moves = piece.getLegalMoves(director, context);

		assertThat(moves, hasSize(2));
		assertThat(moves, hasItems(
				moveTo(0, 3), moveTo(2, 3)
				));
	}

	@Test
	public void blackPawnCanMakeTwoMovesFromInitialPosition() {
		piece = new Pawn(Color.BLACK);
		board.setSquare(1, 6, piece);

		moves = piece.getLegalMoves(director, context);

		assertThat(moves, hasSize(2));
		assertThat(moves, hasItems(
				moveTo(1, 5), moveTo(1, 4)
				));
	}

	@Test
	public void blackPawnCanMakeOneMoveFromOtherPosition() {
		piece = new Pawn(Color.BLACK);
		board.setSquare(1, 5, piece);

		moves = piece.getLegalMoves(director, context);

		assertThat(moves, hasSize(1));
		assertThat(moves, hasItems(
				moveTo(1, 4)
				));
	}

	@Test
	public void blackPawnCanMakeNoMovesFromInitialPositionIfBlocked() {
		piece = new Pawn(Color.BLACK);
		board.setSquare(1, 6, piece);
		board.setSquare(1, 5, new Pawn(Color.BLACK));

		moves = piece.getLegalMoves(director, context);

		assertThat(moves, hasSize(0));
	}

	@Test
	public void blackPawnCanMakeOneMoveFromInitialPositionIfSecondSquareIsOccupied() {
		piece = new Pawn(Color.BLACK);
		board.setSquare(1, 6, piece);
		board.setSquare(1, 4, new Pawn(Color.WHITE));

		moves = piece.getLegalMoves(director, context);

		assertThat(moves, hasSize(1));
		assertThat(moves, hasItems(
				moveTo(1, 5)
				));
	}

	@Test
	public void blackPawnCanMakeNoMovesIfBlocked() {
		piece = new Pawn(Color.BLACK);
		board.setSquare(1, 6, piece);
		board.setSquare(1, 5, new Pawn(Color.WHITE));

		moves = piece.getLegalMoves(director, context);

		assertThat(moves, hasSize(0));
	}

	@Test
	public void blackPawnCanCaptureBothDirections() {
		piece = new Pawn(Color.BLACK);
		board.setSquare(1, 5, piece);
		board.setSquare(0, 4, new Pawn(Color.WHITE));
		board.setSquare(2, 4, new Pawn(Color.WHITE));

		moves = piece.getLegalMoves(director, context);

		assertThat(moves, hasSize(3));
		assertThat(moves, hasItems(
				moveTo(0, 4), moveTo(1, 4), moveTo(2, 4)
				));
	}

	@Test
	public void blackPawnCanCaptureBothDirectionsEvenIfBlocked() {
		piece = new Pawn(Color.BLACK);
		board.setSquare(1, 5, piece);
		board.setSquare(0, 4, new Pawn(Color.WHITE));
		board.setSquare(1, 4, new Pawn(Color.WHITE));
		board.setSquare(2, 4, new Pawn(Color.WHITE));

		moves = piece.getLegalMoves(director, context);

		assertThat(moves, hasSize(2));
		assertThat(moves, hasItems(
				moveTo(0, 4), moveTo(2, 4)
				));
	}

	@Test
	public void kingCanMakeAllLegalMoves() {
		piece = new King(Color.WHITE);
		board.setSquare(2, 2, piece);

		moves = piece.getLegalMoves(director, context);

		assertThat(moves, hasSize(8));
		assertThat(moves, hasItems(
				moveTo(1, 3), moveTo(2, 3), moveTo(3, 3),
				moveTo(1, 2), moveTo(3, 2),
				moveTo(1, 1), moveTo(2, 1), moveTo(3, 1)
				));
	}

	@Test
	public void kingCanMakeAllLegalMovesAtBorder() {
		piece = new King(Color.WHITE);
		board.setSquare(0, 2, piece);

		moves = piece.getLegalMoves(director, context);

		assertThat(moves, hasSize(5));
		assertThat(moves, hasItems(
				moveTo(0, 3), moveTo(1, 3),
				moveTo(1, 2),
				moveTo(0, 1), moveTo(1, 1)
				));
	}

	@Test
	public void kingCanMakeAllLegalMovesInCorner() {
		piece = new King(Color.WHITE);
		board.setSquare(7, 7, piece);

		moves = piece.getLegalMoves(director, context);

		assertThat(moves, hasSize(3));
		assertThat(moves, hasItems(
				moveTo(6, 7),
				moveTo(6, 6), moveTo(7, 6)
				));
	}

	@Test
	public void rookCanMakeAllLegalMoves() {
		piece = new Rook(Color.WHITE);
		board.setSquare(5, 5, piece);

		moves = piece.getLegalMoves(director, context);

		assertThat(moves, hasSize(14));
		assertThat(moves, hasItems(
				moveTo(5, 7), moveTo(5, 6),
				moveTo(5, 0), moveTo(5, 1), moveTo(5, 2), moveTo(5, 3), moveTo(5, 4),
				moveTo(0, 5), moveTo(1, 5), moveTo(2, 5), moveTo(3, 5), moveTo(4, 5),
				moveTo(6, 5), moveTo(7, 5)
				));
	}

	@Test
	public void bishopCanMakeAllLegalMoves() {
		piece = new Bishop(Color.WHITE);
		board.setSquare(4, 2, piece);

		moves = piece.getLegalMoves(director, context);

		assertThat(moves, hasSize(11));
		assertThat(moves, hasItems(
				moveTo(3, 1), moveTo(2, 0),
				moveTo(5, 1), moveTo(6, 0),
				moveTo(3, 3), moveTo(2, 4), moveTo(1, 5), moveTo(0, 6),
				moveTo(5, 3), moveTo(6, 4), moveTo(7, 5)
				));
	}

	@Test
	public void queenCanMakeAllLegalMoves() {
		piece = new Queen(Color.WHITE);
		board.setSquare(2, 4, piece);

		moves = piece.getLegalMoves(director, context);

		assertThat(moves, hasSize(25));
		assertThat(moves, hasItems(
				moveTo(1, 3), moveTo(0, 2),
				moveTo(2, 3), moveTo(2, 2), moveTo(2, 1), moveTo(2, 0),
				moveTo(3, 3), moveTo(4, 2), moveTo(5, 1), moveTo(6, 0),
				moveTo(3, 4), moveTo(4, 4), moveTo(5, 4), moveTo(6, 4), moveTo(7, 4),
				moveTo(3, 5), moveTo(4, 6), moveTo(5, 7),
				moveTo(2, 5), moveTo(2, 6), moveTo(2, 7),
				moveTo(1, 5), moveTo(0, 6),
				moveTo(1, 4), moveTo(0, 4)
				));
	}

	@Test
	public void queenMovesCanBeBlocked() {
		piece = new Queen(Color.WHITE);
		board.setSquare(0, 0, piece);
		board.setSquare(2, 0, new Rook(Color.WHITE));
		board.setSquare(3, 3, new Pawn(Color.BLACK));
		board.setSquare(0, 1, new Pawn(Color.WHITE));

		moves = piece.getLegalMoves(director, context);

		assertThat(moves, hasSize(4));
		assertThat(moves, hasItems(
				moveTo(1, 1),
				moveTo(1, 1), moveTo(2, 2), moveTo(3, 3)
				));
	}

	@Test
	public void knightCanMakeAllLegalMoves() {
		piece = new Knight(Color.WHITE);
		board.setSquare(3, 3, piece);

		moves = piece.getLegalMoves(director, context);

		assertThat(moves, hasSize(8));
		assertThat(moves, hasItems(
				moveTo(1, 2), moveTo(2, 1), moveTo(4, 1), moveTo(5, 2),
				moveTo(5, 4), moveTo(4, 5), moveTo(2, 5), moveTo(1, 4)
				));
	}

}
