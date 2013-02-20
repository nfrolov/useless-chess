package javalabra.chess.core.impl.state;

import java.util.Collection;

import javalabra.chess.core.GameContext;
import javalabra.chess.core.GameListener;
import javalabra.chess.core.MoveDirector;
import javalabra.chess.core.impl.GameState;
import javalabra.chess.domain.Board;
import javalabra.chess.domain.Move;
import javalabra.chess.domain.Piece;
import javalabra.chess.domain.Square;

public class SelectMoveState extends AbstractGameState {

	private final Piece piece;
	private final Collection<Move> moves;

	public SelectMoveState(final Piece piece, final Collection<Move> moves) {
		this.piece = piece;
		this.moves = moves;
	}

	@Override
	protected GameState handle(final Square square, final MoveDirector director, final GameContext context, final GameListener listener) {
		final Move move = findMove(square);

		if (null == move) {
			return new SelectPieceState(piece.getColor());
		}

		final Board board = context.getBoard();

		move.perform(board);
		listener.update(board);

		return new SelectPieceState(piece.getColor().opposite());
	}

	private Move findMove(final Square square) {
		for (final Move move : moves) {
			if (move.getDestination().equals(square)) {
				return move;
			}
		}
		return null;
	}
}
