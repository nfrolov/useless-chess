package javalabra.chess.core;

import javalabra.chess.domain.Board;

/**
 * Context object which represents concrete moment in the game.
 *
 * @author Nikita Frolov
 */
public interface GameContext {

	/**
	 * Returns board object.
	 *
	 * @return			board object
	 */
	Board getBoard();

	/**
	 * Returns independent copy of the context.
	 *
	 * @return			context's copy
	 */
	GameContext copy();

}
