package javalabra.chess.domain;

import java.util.Set;

import javalabra.chess.core.MoveDirector;

public class King extends Piece {

	public King(Color color) {
		super(color);
	}

	@Override
	public Set<Move> getLegalMoves(MoveDirector director) {
		return director.getLegalMoves(this);
	}

}
