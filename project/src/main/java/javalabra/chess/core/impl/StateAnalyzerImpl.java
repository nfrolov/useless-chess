package javalabra.chess.core.impl;

import java.util.Collection;

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

	private final Board board;
	private final MoveDirector director;

	public StateAnalyzerImpl(final Board board, final MoveDirector director) {
		this.board = board;
		this.director = director;
	}

	@Override
	public boolean isCheck(final Color color) {
		final Square king = board.getKingPosition(color);
		final Collection<Piece> pieces = board.getPieces();

		for (final Piece piece : pieces) {
			if (color == piece.getColor()) {
				continue;
			}
			final Collection<Move> moves = piece.getLegalMoves(director);
			if (contains(moves, king)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isCheckmate() {
		return isCheckmate(Color.WHITE) || isCheckmate(Color.BLACK);
	}

	@Override
	public boolean isCheckmate(final Color color) {
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
