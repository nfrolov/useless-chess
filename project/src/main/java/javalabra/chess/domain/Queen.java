package javalabra.chess.domain;

import java.util.Set;

import javalabra.chess.core.MoveDirector;

/**
 * Represents queen.
 *
 * @author Nikita Frolov
 */
public class Queen extends Piece {

	public Queen(Color color) {
		super(color);
	}

	@Override
	public Set<Move> getLegalMoves(MoveDirector director) {
		return director.getLegalMoves(this);
	}

}
