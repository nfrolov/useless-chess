package javalabra.chess.core.impl.state;

import javalabra.chess.core.GameContext;
import javalabra.chess.core.MoveDirector;
import javalabra.chess.core.impl.GameEventEmitter;
import javalabra.chess.core.impl.GameState;
import javalabra.chess.domain.Square;

/**
 * Abstract game state.
 *
 * @author Nikita Frolov
 */
public abstract class AbstractGameState implements GameState {

	@Override
	public GameState handle(final int column, final int row, final MoveDirector director, final GameContext context, final GameEventEmitter em) {
		return handle(context.getBoard().getSquare(column, row), director, context, em);
	}

	/**
	 * Handles square selection and returns next state.
	 *
	 * @param	square		selected square
	 * @param	director	move director
	 * @param	context		game context
	 * @param	em			game's event emitter
	 * @return				state object to be set
	 */
	protected abstract GameState handle(Square square, MoveDirector director, GameContext context, GameEventEmitter em);

}
