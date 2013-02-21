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
		final Collection<Piece> pieces = board.getPieces(color.opposite());

		for (final Piece piece : pieces) {
			final Collection<Move> moves = piece.getCandidateMoves(director, context);
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
		return isCheck(color, context) && !hasValidMoves(color, context);
	}

	@Override
	public boolean isStalemate(final GameContext context) {
		return isStalemate(Color.WHITE, context) || isStalemate(Color.BLACK, context);
	}

	@Override
	public boolean isStalemate(final Color color, final GameContext context) {
		return !isCheck(color, context) && !hasValidMoves(color, context);
	}

	private boolean hasValidMoves(final Color color, final GameContext context) {
		final Board board = context.getBoard();
		final Collection<Piece> pieces = board.getPieces(color);

		for (final Piece piece : pieces) {
			final Collection<Move> moves = piece.getLegalMoves(director, context);
			for (final Move move : moves) {
				final GameContext attempt = context.copy();
				move.perform(attempt.getBoard());
				if (!isCheck(color, attempt)) {
					return true;
				}
			}
		}

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
