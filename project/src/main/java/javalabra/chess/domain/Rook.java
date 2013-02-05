package javalabra.chess.domain;

import java.util.Set;

import javalabra.chess.core.MoveDirector;

/**
 * Represents rook.
 *
 * @author Nikita Frolov
 */
public class Rook extends Piece {

	public Rook(Color color) {
		super(color);
	}

	@Override
	public Set<Move> getLegalMoves(MoveDirector director) {
		return director.getLegalMoves(this);
	}

}
