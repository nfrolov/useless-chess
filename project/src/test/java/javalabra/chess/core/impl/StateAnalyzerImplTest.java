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

import org.junit.Before;
import org.junit.Test;

public class StateAnalyzerImplTest {

	Board board;
	StateAnalyzer analyzer;
	GameContext context;

	Piece whiteKing, whitePawn;
	Piece blackKing, blackQueen, blackPawn;

	@Before
	public void setUp() throws Exception {
		board = new Board();
		context = new GameContextImpl(board);
		analyzer = new StateAnalyzerImpl(new MoveDirectorImpl());

		whiteKing = new King(Color.WHITE);
		whitePawn = new Pawn(Color.WHITE);

		blackKing = new King(Color.BLACK);
		blackQueen = new Queen(Color.BLACK);
		blackPawn = new Pawn(Color.BLACK);

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


}
