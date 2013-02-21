package javalabra.chess.core;

import javalabra.chess.domain.Board;
import javalabra.chess.domain.Move;

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
	 * Performs move and returns a new game context to represent new state.
	 *
	 * @param	move	move to perform
	 * @return			new context object
	 */
	GameContext attempt(Move move);

}
