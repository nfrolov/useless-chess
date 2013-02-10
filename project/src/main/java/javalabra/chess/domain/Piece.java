package javalabra.chess.domain;

import java.util.Set;

import javalabra.chess.core.GameContext;
import javalabra.chess.core.MoveDirector;

/**
 * Represents abstract piece.
 *
 * @author Nikita Frolov
 */
public abstract class Piece {

	private final Color color;

	/**
	 * @param	color		color of the piece
	 */
	public Piece(Color color) {
		this.color = color;
	}

	/**
	 * Returns color of the piece.
	 *
	 * @return				color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Returns all valid moves for the piece.
	 *
	 * @param 	director	director which is responsible for moves calculation
	 * @param	context		context representing game's current state
	 * @return 				collection of valid moves
	 */
	public abstract Set<Move> getLegalMoves(MoveDirector director, GameContext context);

}
