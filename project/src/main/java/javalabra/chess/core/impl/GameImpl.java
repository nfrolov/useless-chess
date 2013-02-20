package javalabra.chess.core.impl;

import javalabra.chess.core.BoardBuilder;
import javalabra.chess.core.Game;
import javalabra.chess.core.GameContext;
import javalabra.chess.core.GameListener;
import javalabra.chess.core.MoveDirector;
import javalabra.chess.core.StateAnalyzer;
import javalabra.chess.core.impl.state.SelectPieceState;
import javalabra.chess.domain.Board;
import javalabra.chess.domain.Color;
import javalabra.chess.domain.PlayerBlack;
import javalabra.chess.domain.PlayerWhite;
import javalabra.chess.ui.BoardListener;

/**
 * Implementation of game facade.
 *
 * @author Nikita Frolov
 */
public class GameImpl implements Game, BoardListener {

	private final PlayerWhite white;
	private final PlayerBlack black;
	private final Board board;
	private final GameContext context;

	private final MoveDirector director;
	private final StateAnalyzer analyzer;

	private GameListener listener;

	private GameState state;

	public GameImpl() {
		white = new PlayerWhite();
		black = new PlayerBlack();
		board = getBoardBuilder().build(white, black);
		context = new GameContextImpl(board);
		director = new MoveDirectorImpl();
		analyzer = new StateAnalyzerImpl(director);
		state = new SelectPieceState(Color.WHITE);
	}

	@Override
	public void begin(final GameListener listener) {
		this.listener = listener;
		this.listener.setBoardListener(this);
		this.listener.update(board);
	}

	@Override
	public void onSquareSelected(int column, int row) {
		state = state.handle(column, row, director, context, listener);
	}

	protected BoardBuilder getBoardBuilder() {
		return new BoardBuilderImpl();
	}

}
