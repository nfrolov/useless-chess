package javalabra.chess.core.impl;

import java.util.Collection;

import javalabra.chess.core.GameContext;
import javalabra.chess.core.MoveDirector;
import javalabra.chess.core.StateAnalyzer;
import javalabra.chess.domain.Board;
import javalabra.chess.domain.Color;
import javalabra.chess.domain.Move;
import javalabra.chess.domain.Piece;
import javalabra.chess.domain.Square;

/**
 * Standard implementation of the state analyzer.
 *
 * @author Nikita Frolov
 */
public class StateAnalyzerImpl implements StateAnalyzer {

	private final MoveDirector director;

	public StateAnalyzerImpl(final MoveDirector director) {
		this.director = director;
	}

	@Override
	public boolean isCheck(final Color color, final GameContext context) {
		final Board board = context.getBoard();
		final Square king = board.getKingPosition(color);
		final Collection<Piece> pieces = board.getPieces();

		for (final Piece piece : pieces) {
			if (color == piece.getColor()) {
				continue;
			}
			final Collection<Move> moves = piece.getLegalMoves(director, context);
			if (contains(moves, king)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isCheckmate(final GameContext context) {
		return isCheckmate(Color.WHITE, context) || isCheckmate(Color.BLACK, context);
	}

	@Override
	public boolean isCheckmate(final Color color, final GameContext context) {
		return false;
	}

	private boolean contains(final Collection<Move> moves, final Square square) {
		for (final Move move : moves) {
			if (move.getDestination().equals(square)) {
				return true;
			}
		}
		return false;
	}

}
