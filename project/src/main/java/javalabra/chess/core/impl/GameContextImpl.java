package javalabra.chess.core.impl;

import javalabra.chess.core.GameContext;
import javalabra.chess.domain.Board;

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
	public GameContext copy() {
		return new GameContextImpl(this);
	}

}
