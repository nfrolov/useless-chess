package javalabra.chess.core.impl;

import javalabra.chess.core.GameContext;
import javalabra.chess.core.MoveDirector;

/**
 * State interface representing game's current state.
 *
 * @author Nikita Frolov
 */
public interface GameState {

	/**
	 * Handles square selection. Returns next state object.
	 *
	 * @param	column		column index that was selected
	 * @param	row			row index that was selected
	 * @param	director	move director
	 * @param	context		game context
	 * @param	em			game's event emitter
	 * @return				state object to be set next
	 */
	GameState handle(int column, int row, MoveDirector director, GameContext context, GameEventEmitter em);

}
