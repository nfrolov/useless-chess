package javalabra.chess.core.impl.state;

import java.util.Collection;

import javalabra.chess.core.GameContext;
import javalabra.chess.core.MoveDirector;
import javalabra.chess.core.impl.GameEventEmitter;
import javalabra.chess.core.impl.GameState;
import javalabra.chess.domain.Color;
import javalabra.chess.domain.Move;
import javalabra.chess.domain.Piece;
import javalabra.chess.domain.Square;

/**
 * State object representing state when game is waiting for piece of concrete
 * color to be selected.
 *
 * @author Nikita Frolov
 */
public class SelectPieceState extends AbstractGameState {

	private final Color color;

	public SelectPieceState(final Color color) {
		this.color = color;
	}

	@Override
	protected GameState handle(final Square square, final MoveDirector director, final GameContext context, final GameEventEmitter em) {
		final Piece piece = square.getPiece();

		if (null == piece || color != piece.getColor()) {
			return this;
		}

		final Collection<Move> moves = piece.getLegalMoves(director, context);

		em.trigger(piece.getColor(), square, moves);

		return new SelectMoveState(piece, moves);
	}

}
