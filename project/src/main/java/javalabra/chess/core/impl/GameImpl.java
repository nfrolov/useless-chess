package javalabra.chess.core.impl;

import java.util.Collection;

import javalabra.chess.core.BoardBuilder;
import javalabra.chess.core.Game;
import javalabra.chess.core.GameContext;
import javalabra.chess.core.MoveDirector;
import javalabra.chess.core.StateAnalyzer;
import javalabra.chess.domain.Board;
import javalabra.chess.domain.Move;
import javalabra.chess.domain.Piece;
import javalabra.chess.domain.PlayerBlack;
import javalabra.chess.domain.PlayerWhite;

/**
 * Implementation of game facade.
 *
 * @author Nikita Frolov
 */
public class GameImpl implements Game {

	private final PlayerWhite white;
	private final PlayerBlack black;
	private final Board board;
	private final GameContext context;

	private final MoveDirector director;
	private final StateAnalyzer analyzer;

	public GameImpl() {
		white = new PlayerWhite();
		black = new PlayerBlack();
		board = getBoardBuilder().build(white, black);
		context = new GameContextImpl(board);
		director = new MoveDirectorImpl();
		analyzer = new StateAnalyzerImpl(director);
	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public Collection<Move> getLegalMoves(final Piece piece) {
		return piece.getLegalMoves(director, context);
	}

	@Override
	public void performMove(final Move move) {
		move.perform(board);
	}

	protected BoardBuilder getBoardBuilder() {
		return new BoardBuilderImpl();
	}

}
