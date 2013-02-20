package javalabra.chess.core.impl.state;

import javalabra.chess.core.GameContext;
import javalabra.chess.core.GameListener;
import javalabra.chess.core.MoveDirector;
import javalabra.chess.core.impl.GameState;
import javalabra.chess.domain.Square;

public abstract class AbstractGameState implements GameState {

	@Override
	public GameState handle(final int column, final int row, final MoveDirector director, final GameContext context, final GameListener listener) {
		return handle(context.getBoard().getSquare(column, row), director, context, listener);
	}

	protected abstract GameState handle(Square square, MoveDirector director, GameContext context, GameListener listener);

}
