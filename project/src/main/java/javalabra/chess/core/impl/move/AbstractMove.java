package javalabra.chess.core.impl.move;

import javalabra.chess.domain.Board;
import javalabra.chess.domain.Move;
import javalabra.chess.domain.Piece;
import javalabra.chess.domain.Square;

/**
 * Abstract skeleton for move objects.
 *
 * @author Nikita Frolov
 */
public abstract class AbstractMove implements Move {

	/**
	 * Main piece of the move.
	 */
	protected final Piece piece;

	/**
	 * Piece's source position.
	 */
	protected final Square source;

	/**
	 * Piece's destination position.
	 */
	protected final Square destination;

	/**
	 * @param	piece		main piece to be moved
	 * @param	source		source position
	 * @param	destination	destination position
	 */
	public AbstractMove(Piece piece, Square source, Square destination) {
		this.piece = piece;
		this.source = source;
		this.destination = destination;
	}

	public Piece getPiece() {
		return piece;
	}

	@Override
	public Square getSource() {
		return source;
	}

	@Override
	public Square getDestination() {
		return destination;
	}

	@Override
	public String toString() {
		return source.toString() + destination.toString();
	}

	@Override
	public abstract void perform(Board board);

}
