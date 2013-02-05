package javalabra.chess.core.move;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import javalabra.chess.domain.Board;
import javalabra.chess.domain.Color;
import javalabra.chess.domain.Pawn;
import javalabra.chess.domain.Piece;
import javalabra.chess.domain.Square;

import org.junit.Before;
import org.junit.Test;

public class CaptureMoveTest {

	Piece piece, captured;
	Board board;
	Square source, destination;

	CaptureMove move;

	@Before
	public void setUp() throws Exception {
		piece = new Pawn(Color.WHITE);
		captured = new Pawn(Color.BLACK);
		board = new Board();

		source = null;
		destination = null;
		move = null;
	}

	@Test
	public void performCorrectlyDoNormalCapture() {
		source = board.getSquare(3, 3);
		destination = board.getSquare(2, 4);

		board.setSquare(source, piece);
		board.setSquare(destination, captured);

		move = new CaptureMove(piece, source, destination, captured);
		move.perform(board);

		assertThat(source.getPiece(), is(nullValue()));
		assertThat(destination.getPiece(), is(piece));
	}

	@Test
	public void performCorrectlyDoEnPassantCapture() {
		source = board.getSquare(3, 4);
		destination = board.getSquare(2, 5);

		board.setSquare(source, piece);
		board.setSquare(board.getSquare(2, 4), captured);

		move = new CaptureMove(piece, source, destination, captured);
		move.perform(board);

		assertThat(source.getPiece(), is(nullValue()));
		assertThat(destination.getPiece(), is(piece));
		assertThat(board.getSquare(2, 4).getPiece(), is(nullValue()));
	}

}
