package javalabra.chess.domain;

import java.util.Collection;

import javalabra.chess.core.GameContext;
import javalabra.chess.core.MoveDirector;
import javalabra.chess.core.PiecePainter;

/**
 * Represents pawn.
 *
 * @author Nikita Frolov
 */
public class Pawn extends Piece {

	public Pawn(Color color) {
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
