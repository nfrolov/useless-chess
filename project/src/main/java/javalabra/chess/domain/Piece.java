package javalabra.chess.domain;

import java.util.Set;

import javalabra.chess.core.MoveDirector;

public abstract class Piece {

	private final Color color;
	private Square position;

	public Piece(Color color, Square position) {
		this.color = color;
		move(position);
	}

	public Color getColor() {
		return color;
	}

	public Square getPosition() {
		return position;
	}

	public void move(Square target) {
		if (null != position) {
			position.setPiece(null);
		}
		position = target;
		position.setPiece(this);
	}

	public abstract Set<Square> getLegalMoves(MoveDirector director);

}
