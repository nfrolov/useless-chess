package javalabra.chess.domain;

import java.util.Set;

import javalabra.chess.core.GameContext;
import javalabra.chess.core.MoveDirector;

/**
 * Represents king.
 *
 * @author Nikita Frolov
 */
public class King extends Piece {

	public King(Color color) {
		super(color);
	}

	@Override
	public Set<Move> getLegalMoves(MoveDirector director, GameContext context) {
		return director.getLegalMoves(this, context);
	}

}
