package javalabra.chess.core.impl.move;

import javalabra.chess.domain.Board;
import javalabra.chess.domain.Piece;
import javalabra.chess.domain.Square;

/**
 * Implementation of the normal move.
 *
 * @author Nikita Frolov
 */
public class NormalMove extends AbstractMove {

	public NormalMove(Piece piece, Square source, Square destination) {
		super(piece, source, destination);
	}

	/**
	 * Performs normal move on concrete board. Piece is only moved from source location
	 * to destination.
	 *
	 * @param	board		board object to perform move on
	 */
	@Override
	public void perform(final Board board) {
		board.setSquare(source, null);
		board.setSquare(destination, piece);
	}

}
