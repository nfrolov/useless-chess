package javalabra.chess.core.move;

import javalabra.chess.domain.Board;
import javalabra.chess.domain.Piece;
import javalabra.chess.domain.Square;

public class CaptureMove extends AbstractMove {

	protected final Piece captured;

	public CaptureMove(Piece piece, Square source, Square destination, Piece captured) {
		super(piece, source, destination);
		this.captured = captured;
	}

	public Piece getCaptured() {
		return captured;
	}

	@Override
	public void perform(final Board board) {
	}

}
