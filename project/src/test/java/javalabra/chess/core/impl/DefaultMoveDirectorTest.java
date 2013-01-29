package javalabra.chess.core.impl;

import javalabra.chess.domain.Board;

import org.junit.Before;
import org.junit.Test;

public class DefaultMoveDirectorTest {

	Board board;
	DefaultMoveDirector director;

	@Before
	public void setUp() throws Exception {
		board = new Board();
		director = new DefaultMoveDirector(board);
	}

}
