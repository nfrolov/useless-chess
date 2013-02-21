package javalabra.chess.core.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import javalabra.chess.core.GameContext;
import javalabra.chess.core.GameEvent;
import javalabra.chess.core.GameListener;
import javalabra.chess.core.StateAnalyzer;
import javalabra.chess.domain.Board;
import javalabra.chess.domain.Color;
import javalabra.chess.domain.Move;
import javalabra.chess.domain.Square;

/**
 * Utility class for triggering game events. Simplifies GameEvent object construction.
 *
 * @author Nikita Frolov
 */
public class GameEventEmitter {

	private final static Collection<Square> EMPTY_MOVES_LIST = Collections.emptyList();

	private final GameContext context;
	private final StateAnalyzer analyzer;
	private GameListener listener;

	public GameEventEmitter(final GameContext context, final StateAnalyzer analyzer) {
		this.context = context;
		this.analyzer = analyzer;
		this.listener = null;
	}

	public void setGameListener(final GameListener listener) {
		this.listener = listener;
	}

	/**
	 * Triggers game event.
	 *
	 * @param color			current color allowed to make move
	 * @param currentPiece	selected piece's position
	 * @param legalMoves	collection of legal moves for the selected piece
	 */
	public void trigger(final Color color, final Square currentPiece, final Collection<Move> legalMoves) {
		if (null == listener) {
			throw new IllegalStateException("Game listener is not set");
		}

		final boolean whiteTurn = Color.WHITE == color;
		final Collection<Square> legalDestinations = extractDestinations(legalMoves);
		final Board board = context.getBoard();
		final boolean check = analyzer.isCheck(context), checkmate = analyzer.isCheckmate(context);

		final GameEvent event = new GameEventImpl(board, whiteTurn, currentPiece, legalDestinations, check, checkmate);

		listener.gameChanged(event);
	}

	/**
	 * Triggers game event with no current piece neither legal moves.
	 *
	 * @param color			current color allowed to move
	 */
	public void trigger(final Color color) {
		trigger(color, null, null);
	}

	private static Collection<Square> extractDestinations(final Collection<Move> moves) {
		if (null == moves || moves.isEmpty()) {
			return EMPTY_MOVES_LIST;
		}

		final Collection<Square> destinations = new HashSet<Square>();

		for (final Move move : moves) {
			destinations.add(move.getDestination());
		}

		return destinations;
	}

}
