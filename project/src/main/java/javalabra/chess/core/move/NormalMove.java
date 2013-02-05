package javalabra.chess.core.move;

import javalabra.chess.domain.Board;
import javalabra.chess.domain.Piece;
import javalabra.chess.domain.Square;

/**
 * Represents normal move.
 *
 * @author Nikita Frolov
 */
public class NormalMove extends AbstractMove {

	public NormalMove(Piece piece, Square source, Square destination) {
		super(piece, source, destination);
	}

	@Override
	public void perform(final Board board) {
		board.setSquare(source, null);
		board.setSquare(destination, piece);
	}

}
