package javalabra.chess.domain;

import java.util.Set;

import javalabra.chess.core.MoveDirector;

/**
 * Represents bishop.
 *
 * @author Nikita Frolov
 */
public class Bishop extends Piece {

	public Bishop(Color color) {
		super(color);
	}

	@Override
	public Set<Move> getLegalMoves(MoveDirector director) {
		return director.getLegalMoves(this);
	}

}
