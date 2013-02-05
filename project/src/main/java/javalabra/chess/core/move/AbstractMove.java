package javalabra.chess.core.move;

import javalabra.chess.domain.Board;
import javalabra.chess.domain.Move;
import javalabra.chess.domain.Piece;
import javalabra.chess.domain.Square;

public abstract class AbstractMove implements Move {

	protected final Piece piece;
	protected final Square source;
	protected final Square destination;

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
