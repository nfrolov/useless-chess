package javalabra.chess.domain;

import java.util.Collection;

import javalabra.chess.core.GameContext;
import javalabra.chess.core.MoveDirector;
import javalabra.chess.core.PiecePainter;

/**
 * Represents king.
 *
 * @author Nikita Frolov
 */
public class King extends Piece {

	public King(Color color) {
		super(color);
	}

	@Override
	public Collection<Move> getLegalMoves(MoveDirector director, GameContext context) {
		return director.getLegalMoves(this, context);
	}

	@Override
	public void paint(final PiecePainter painter) {
		painter.paint(this);
	}

}
