package javalabra.chess.domain;

import java.util.Set;

import javalabra.chess.core.MoveDirector;

/**
 * Represents pawn.
 *
 * @author Nikita Frolov
 */
public class Pawn extends Piece {

	public Pawn(Color color) {
		super(color);
	}

	@Override
	public Set<Move> getLegalMoves(MoveDirector director) {
		return director.getLegalMoves(this);
	}

}
