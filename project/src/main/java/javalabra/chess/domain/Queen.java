package javalabra.chess.domain;

import java.util.Set;

import javalabra.chess.core.MoveDirector;

public class Queen extends Piece {

	public Queen(Color color) {
		super(color);
	}

	@Override
	public Set<Move> getLegalMoves(MoveDirector director) {
		return director.getLegalMoves(this);
	}

}
