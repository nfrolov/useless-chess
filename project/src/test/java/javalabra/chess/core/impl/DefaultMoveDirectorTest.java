package javalabra.chess.core.impl;

import static org.junit.Assert.*;

import java.util.Set;

import javalabra.chess.domain.Board;
import javalabra.chess.domain.Color;
import javalabra.chess.domain.Pawn;
import javalabra.chess.domain.Piece;
import javalabra.chess.domain.PlayerBlack;
import javalabra.chess.domain.PlayerWhite;
import javalabra.chess.domain.Square;

import org.junit.Before;
import org.junit.Test;

public class DefaultMoveDirectorTest {

	Board board;
	DefaultMoveDirector director;

	Set<Square> moves;

	@Before
	public void setUp() throws Exception {
		board = new Board();
		director = new DefaultMoveDirector(board);
		moves = null;
	}

	@Test
	public void whitePawnCanMakeTwoMovesFromInitialPosition() {
		moves = getMoves(new Pawn(Color.WHITE, board.getSquare(0, 1)));
		assertEquals(2, moves.size());
		assertTrue(moves.contains(board.getSquare(0, 2)));
		assertTrue(moves.contains(board.getSquare(0, 3)));
	}

	@Test
	public void blackPawnCanMakeTwoMovesFromInitialPosition() {
		moves = getMoves(new Pawn(Color.BLACK, board.getSquare(0, 6)));
		assertEquals(2, moves.size());
		assertTrue(moves.contains(board.getSquare(0, 5)));
		assertTrue(moves.contains(board.getSquare(0, 4)));
	}

	@Test
	public void whitePawnCanMakeOneMove() {
		moves = getMoves(new Pawn(Color.WHITE, board.getSquare(0, 2)));
		assertEquals(1, moves.size());
		assertTrue(moves.contains(board.getSquare(0, 3)));
	}

	@Test
	public void blackPawnCanMakeOneMove() {
		moves = getMoves(new Pawn(Color.BLACK, board.getSquare(0, 5)));
		assertEquals(1, moves.size());
		assertTrue(moves.contains(board.getSquare(0, 4)));
	}

	public Set<Square> getMoves(Piece piece) {
		return piece.getLegalMoves(director);
	}

}
