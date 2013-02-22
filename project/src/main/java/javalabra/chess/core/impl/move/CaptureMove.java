package javalabra.chess.core.impl.move;

import javalabra.chess.domain.Board;
import javalabra.chess.domain.Piece;
import javalabra.chess.domain.Square;

/**
 * Implementation of the capture move.
 *
 * @author Nikita Frolov
 */
public class CaptureMove extends AbstractMove {

	protected final Piece captured;

	public CaptureMove(Piece piece, Square source, Square destination, Piece captured) {
		super(piece, source, destination);
		this.captured = captured;
	}

	public Piece getCaptured() {
		return captured;
	}

	/**
	 * Performs capture move on concrete board. Piece is moved from source location
	 * to destination, captured piece is removed from the board.
	 *
	 * @param	board		board object to perform move on
	 */
	@Override
	public void perform(final Board board) {
		board.setSquare(board.getPiecePosition(captured), null);
		board.setSquare(source, null);
		board.setSquare(destination, piece);
	}

}
