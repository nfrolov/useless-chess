package javalabra.chess.core.impl.move;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import javalabra.chess.domain.Board;
import javalabra.chess.domain.Color;
import javalabra.chess.domain.Knight;
import javalabra.chess.domain.Piece;
import javalabra.chess.domain.Square;

import org.junit.Before;
import org.junit.Test;

public class NormalMoveTest {

	Piece piece;
	Board board;
	Square source, destination;

	NormalMove move;

	@Before
	public void setUp() throws Exception {
		piece = new Knight(Color.WHITE);
		board = new Board();
	}

	@Test
	public void performCorrectlyDoNormalMove() {
		source = board.getSquare(3, 3);
		destination = board.getSquare(5, 4);

		board.setSquare(source, piece);

		move = new NormalMove(piece, source, destination);
		move.perform(board);

		assertThat(source.getPiece(), is(nullValue()));
		assertThat(destination.getPiece(), is(piece));
	}

}
