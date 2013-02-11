package javalabra.chess.core.impl;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javalabra.chess.core.GameContext;
import javalabra.chess.core.StateAnalyzer;
import javalabra.chess.domain.Board;
import javalabra.chess.domain.Color;
import javalabra.chess.domain.King;
import javalabra.chess.domain.Pawn;
import javalabra.chess.domain.Piece;
import javalabra.chess.domain.Queen;
import javalabra.chess.domain.Rook;

import org.junit.Before;
import org.junit.Test;

public class StateAnalyzerImplTest {

	Board board;
	StateAnalyzer analyzer;
	GameContext context;

	Piece whiteKing, whitePawn, whiteRook1;
	Piece blackKing, blackQueen, blackPawn, blackRook1, blackRook2;

	@Before
	public void setUp() throws Exception {
		board = new Board();
		context = new GameContextImpl(board);
		analyzer = new StateAnalyzerImpl(new MoveDirectorImpl());

		whiteKing = new King(Color.WHITE);
		whitePawn = new Pawn(Color.WHITE);
		whiteRook1 = new Rook(Color.WHITE);

		blackKing = new King(Color.BLACK);
		blackQueen = new Queen(Color.BLACK);
		blackPawn = new Pawn(Color.BLACK);
		blackRook1 = new Rook(Color.BLACK);
		blackRook2 = new Rook(Color.BLACK);

		board.setSquare(4, 0, whiteKing);
		board.setSquare(4, 7, blackKing);
	}

	@Test
	public void detectsNoCheckIfAbsent() {
		board.setSquare(2, 6, blackPawn);

		assertThat(analyzer.isCheck(Color.WHITE, context), is(false));
		assertThat(analyzer.isCheck(Color.BLACK, context), is(false));
	}

	@Test
	public void detectsCheckByStraightMove() {
		board.setSquare(4, 3, blackQueen);

		assertThat(analyzer.isCheck(Color.WHITE, context), is(true));
		assertThat(analyzer.isCheck(Color.BLACK, context), is(false));
	}

	@Test
	public void detectsCheckByDiagonalMove() {
		board.setSquare(2, 2, blackQueen);

		assertThat(analyzer.isCheck(Color.WHITE, context), is(true));
		assertThat(analyzer.isCheck(Color.BLACK, context), is(false));
	}

	@Test
	public void detectsNoCheckIfDiagonalMoveIsBlocked() {
		board.setSquare(2, 2, blackQueen);
		board.setSquare(3, 1, whitePawn);

		assertThat(analyzer.isCheck(Color.WHITE, context), is(false));
		assertThat(analyzer.isCheck(Color.BLACK, context), is(false));
	}

	@Test
	public void detectsNoCheckIfDiagonalMoveIsBlockedBySameColorPiece() {
		board.setSquare(1, 3, blackQueen);
		board.setSquare(2, 2, blackPawn);

		assertThat(analyzer.isCheck(Color.WHITE, context), is(false));
		assertThat(analyzer.isCheck(Color.BLACK, context), is(false));
	}

	@Test
	public void detectsNoCheckIfStraightMoveIsBlocked() {
		board.setSquare(4, 4, blackQueen);
		board.setSquare(4, 1, whitePawn);

		assertThat(analyzer.isCheck(Color.WHITE, context), is(false));
		assertThat(analyzer.isCheck(Color.BLACK, context), is(false));
	}

	@Test
	public void detectsNoCheckIfStraightMoveIsBlockedBySameColorPiece() {
		board.setSquare(4, 4, blackQueen);
		board.setSquare(4, 2, blackPawn);

		assertThat(analyzer.isCheck(Color.WHITE, context), is(false));
		assertThat(analyzer.isCheck(Color.BLACK, context), is(false));
	}

	@Test
	public void detectsNoCheckmateIfAbsent() {
		board.setSquare(2, 6, blackPawn);

		assertThat(analyzer.isCheckmate(Color.WHITE, context), is(false));
		assertThat(analyzer.isCheckmate(Color.BLACK, context), is(false));
	}

	@Test
	public void detectsNoCheckmateIfCheckIsAvoidable() {
		board.setSquare(4, 5, blackQueen);

		assertThat(analyzer.isCheckmate(Color.WHITE, context), is(false));
		assertThat(analyzer.isCheckmate(Color.BLACK, context), is(false));
	}

	@Test
	public void detectsCheckmateByTwoRooks() {
		board.setSquare(0, 0, blackRook1);
		board.setSquare(2, 1, blackRook2);

		assertThat(analyzer.isCheckmate(Color.WHITE, context), is(true));
		assertThat(analyzer.isCheckmate(Color.BLACK, context), is(false));
	}

	@Test
	public void detectsNoCheckmateIfRookCanBeCapturedByKing() {
		board.setSquare(0, 0, blackRook1);
		board.setSquare(3, 1, blackRook2);

		assertThat(analyzer.isCheckmate(Color.WHITE, context), is(false));
		assertThat(analyzer.isCheckmate(Color.BLACK, context), is(false));
	}

	@Test
	public void detectsCheckmateByCoveredQueen() {
		board.setSquare(4, 1, blackQueen);
		board.setSquare(5, 2, blackPawn);

		assertThat(analyzer.isCheckmate(Color.WHITE, context), is(true));
		assertThat(analyzer.isCheckmate(Color.BLACK, context), is(false));
	}

	@Test
	public void detectsNoCheckmateInCaseOfStalemate() {
		board.setSquare(4, 1, blackPawn);
		board.setSquare(0, 1, blackRook1);
		board.setSquare(7, 1, blackRook2);

		assertThat(analyzer.isCheckmate(Color.WHITE, context), is(false));
		assertThat(analyzer.isCheckmate(Color.BLACK, context), is(false));
	}

	@Test
	public void detectsNoCheckmateByTwoRooksIfItCanBeBlocked() {
		board.setSquare(0, 0, blackRook1);
		board.setSquare(2, 1, blackRook2);
		board.setSquare(3, 5, whiteRook1);

		assertThat(analyzer.isCheckmate(Color.WHITE, context), is(false));
		assertThat(analyzer.isCheckmate(Color.BLACK, context), is(false));
	}

	@Test
	public void detectsNoStalemateIfAbsent() {
		board.setSquare(0, 2, blackRook1);
		board.setSquare(6, 5, blackQueen);
		board.setSquare(6, 2, whitePawn);

		assertStalemate(false, false);
	}

	@Test
	public void detectsStalemateByTwoRooksAndQueen() {
		board.setSquare(0, 1, blackRook1);
		board.setSquare(3, 4, blackQueen);
		board.setSquare(5, 6, blackRook2);

		assertStalemate(true, false);
	}

	@Test
	public void detectsNoStalemateByTwoRooksAndQueenIfAnyOtherPieceCanMove() {
		board.setSquare(0, 1, blackRook1);
		board.setSquare(3, 4, blackQueen);
		board.setSquare(5, 6, blackRook2);
		board.setSquare(7, 1, whitePawn);

		assertStalemate(false, false);
	}

	@Test
	public void detectsNoStalemateIfCheckmated() {
		board.setSquare(0, 1, blackRook1);
		board.setSquare(1, 1, blackRook2);

		assertStalemate(false, false);
	}

	@Test
	public void detectsNoStalemateIfCheckmatedEvenIfOtherPieceCouldMove() {
		board.setSquare(0, 1, blackRook1);
		board.setSquare(1, 1, blackRook2);
		board.setSquare(7, 1, whitePawn);

		assertStalemate(false, false);
	}

	private void assertStalemate(final boolean whites, final boolean blacks) {
		assertThat("whites are in stalemate", analyzer.isStalemate(Color.WHITE, context), is(whites));
		assertThat("blacks are in stalemate", analyzer.isStalemate(Color.BLACK, context), is(blacks));
		assertThat("game is in stalemate", analyzer.isStalemate(context), is(whites || blacks));
	}

}
