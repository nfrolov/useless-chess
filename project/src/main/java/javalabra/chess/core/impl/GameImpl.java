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

	/**
	 * White player.
	 */
	private final PlayerWhite white;

	/**
	 * Black player.
	 */
	private final PlayerBlack black;

	/**
	 * Chessboard.
	 */
	private final Board board;

	/**
	 * Game's context.
	 */
	private final GameContext context;

	/**
	 * Move director responsible for move calculations.
	 */
	private final MoveDirector director;

	/**
	 * State analyzer responsible for check/checkmate detections.
	 */
	private final StateAnalyzer analyzer;

	/**
	 * Helper object for event triggering.
	 */
	private GameEventEmitter em;

	/**
	 * Current state of the game.
	 */
	private GameState state;

	/**
	 * Prepares new game to begin.
	 */
	public GameImpl() {
		white = new PlayerWhite();
		black = new PlayerBlack();
		board = getBoardBuilder().build(white, black);

		context = new GameContextImpl(board);

		analyzer = new StateAnalyzerImpl();
		director = new MoveDirectorImpl();

		analyzer.setMoveDirector(director);
		director.setStateAnalyzer(analyzer);

		em = new GameEventEmitter(context, analyzer);
	}

	@Override
	public void begin(final GameListener listener) {
		em.setGameListener(listener);
		listener.setBoardListener(this);
		state = new SelectPieceState(Color.WHITE);
		em.trigger(Color.WHITE);
	}

	@Override
	public void onSquareSelected(int column, int row) {
		state = state.handle(column, row, director, context, em);
	}

	protected BoardBuilder getBoardBuilder() {
		return new BoardBuilderImpl();
	}

}
