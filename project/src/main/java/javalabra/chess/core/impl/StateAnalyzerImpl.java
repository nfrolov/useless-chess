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

	private MoveDirector director;

	public StateAnalyzerImpl() {
		super();
	}

	@Override
	public void setMoveDirector(final MoveDirector director) {
		this.director = director;
	}

	@Override
	public boolean isCheck(final GameContext context) {
		return isCheck(Color.WHITE, context) || isCheck(Color.BLACK, context);
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
		return isCheck(color, context) && !hasLegalMoves(color, context);
	}

	@Override
	public boolean isStalemate(final GameContext context) {
		return isStalemate(Color.WHITE, context) || isStalemate(Color.BLACK, context);
	}

	@Override
	public boolean isStalemate(final Color color, final GameContext context) {
		return !isCheck(color, context) && !hasLegalMoves(color, context);
	}

	/**
	 * Checks if the specified player has legal moves in given context.
	 *
	 * @param	color		color to check moves for
	 * @param	context		game context
	 * @return				true if there is any legal move
	 */
	private boolean hasLegalMoves(final Color color, final GameContext context) {
		final Board board = context.getBoard();
		final Collection<Piece> pieces = board.getPieces(color);

		for (final Piece piece : pieces) {
			final Collection<Move> moves = piece.getLegalMoves(director, context);
			for (final Move move : moves) {
				final GameContext attempt = context.attempt(move);
				if (!isCheck(color, attempt)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Checks if any move in provided collection is to concrete square.
	 *
	 * @param	moves		collection of moves
	 * @param	square		target square
	 * @return				true if contains
	 */
	private boolean contains(final Collection<Move> moves, final Square square) {
		for (final Move move : moves) {
			if (move.getDestination().equals(square)) {
				return true;
			}
		}
		return false;
	}

}
