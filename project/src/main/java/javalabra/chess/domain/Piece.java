package javalabra.chess.domain;

import java.util.Set;

import javalabra.chess.core.MoveDirector;

public abstract class Piece {

	private final Color color;

	public Piece(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public abstract Set<Square> getLegalMoves(MoveDirector director);

}
