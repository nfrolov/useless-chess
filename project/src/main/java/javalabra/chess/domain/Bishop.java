package javalabra.chess.domain;

import java.util.Set;

import javalabra.chess.core.MoveDirector;

public class Bishop extends Piece {

	public Bishop(Color color, Square position) {
		super(color, position);
	}

	@Override
	public Set<Square> getLegalMoves(MoveDirector director) {
		return director.getLegalMoves(this);
	}

}
