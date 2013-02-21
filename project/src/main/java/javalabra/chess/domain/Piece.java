package javalabra.chess.domain;

import java.util.Collection;

import javalabra.chess.core.GameContext;
import javalabra.chess.core.MoveDirector;
import javalabra.chess.core.PiecePainter;

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
	 * Returns true if color of the piece is white.
	 *
	 * @return				true if white
	 */
	public boolean isWhite() {
		return Color.WHITE == color;
	}

	/**
	 * Returns all legal moves for the piece.
	 *
	 * @param 	director	director which is responsible for moves calculation
	 * @param	context		context representing game's current state
	 * @return 				collection of legal moves
	 */
	public abstract Collection<Move> getLegalMoves(MoveDirector director, GameContext context);

	/**
	 * Returns all candidate moves for piece. Such moves are not analyzed and must not be performed.
	 *
	 * @param	director	director which is responsible for moves calculation
	 * @param 	context		context representing game's current state
	 * @return				collection of candidate moves
	 */
	public abstract Collection<Move> getCandidateMoves(MoveDirector director, GameContext context);

	/**
	 * Delegates piece painting to specified painter object.
	 *
	 * @param	painter		painter object responsible for painting
	 */
	public abstract void paint(PiecePainter painter);

}
