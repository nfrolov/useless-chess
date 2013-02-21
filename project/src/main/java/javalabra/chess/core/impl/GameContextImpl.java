package javalabra.chess.core.impl;

import javalabra.chess.core.GameContext;
import javalabra.chess.domain.Board;
import javalabra.chess.domain.Move;

/**
 * Implementation of game context.
 *
 * @author Nikita
 */
public class GameContextImpl implements GameContext {

	private final Board board;

	public GameContextImpl(final Board board) {
		this.board = board;
	}

	public GameContextImpl(GameContextImpl context) {
		board = context.board.copy();
	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public GameContext attempt(final Move move) {
		final Board copy = board.copy();

		move.perform(copy);

		return new GameContextImpl(copy);
	}

}
