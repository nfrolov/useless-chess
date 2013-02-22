package javalabra.chess.core.impl;

import java.util.Collection;

import javalabra.chess.core.GameContext;
import javalabra.chess.core.MoveDirector;
import javalabra.chess.core.StateAnalyzer;
import javalabra.chess.domain.Bishop;
import javalabra.chess.domain.Color;
import javalabra.chess.domain.King;
import javalabra.chess.domain.Knight;
import javalabra.chess.domain.Move;
import javalabra.chess.domain.Pawn;
import javalabra.chess.domain.Piece;
import javalabra.chess.domain.Queen;
import javalabra.chess.domain.Rook;
import javalabra.chess.domain.Square;

/**
 * Standard implementation of move director.
 *
 * @author Nikita Frolov
 */
public class MoveDirectorImpl implements MoveDirector {

	private StateAnalyzer analyzer;

	public MoveDirectorImpl() {
		super();
	}

	@Override
	public void setStateAnalyzer(final StateAnalyzer analyzer) {
		this.analyzer = analyzer;
	}

	@Override
	public Collection<Move> getLegalMoves(final King piece, final GameContext context) {
		return build(piece, context).getLegalMoves();
	}

	@Override
	public Collection<Move> getCandidateMoves(final King piece, final GameContext context) {
		return build(piece, context).getCandidateMoves();
	}

	@Override
	public Collection<Move> getLegalMoves(final Queen piece, final GameContext context) {
		return build(piece, context).getLegalMoves();
	}

	@Override
	public Collection<Move> getCandidateMoves(final Queen piece, final GameContext context) {
		return build(piece, context).getCandidateMoves();
	}

	@Override
	public Collection<Move> getLegalMoves(final Rook piece, final GameContext context) {
		return build(piece, context).getLegalMoves();
	}

	@Override
	public Collection<Move> getCandidateMoves(final Rook piece, final GameContext context) {
		return build(piece, context).getCandidateMoves();
	}

	@Override
	public Collection<Move> getLegalMoves(final Knight piece, final GameContext context) {
		return build(piece, context).getLegalMoves();
	}

	@Override
	public Collection<Move> getCandidateMoves(final Knight piece, final GameContext context) {
		return build(piece, context).getCandidateMoves();
	}

	@Override
	public Collection<Move> getLegalMoves(final Bishop piece, final GameContext context) {
		return build(piece, context).getLegalMoves();
	}

	@Override
	public Collection<Move> getCandidateMoves(final Bishop piece, final GameContext context) {
		return build(piece, context).getCandidateMoves();
	}

	@Override
	public Collection<Move> getLegalMoves(final Pawn piece, final GameContext context) {
		return build(piece, context).getLegalMoves();
	}

	@Override
	public Collection<Move> getCandidateMoves(final Pawn piece, final GameContext context) {
		return build(piece, context).getCandidateMoves();
	}

	/**
	 * Creates builder and add all moves for the king.
	 *
	 * @param	piece		king piece
	 * @param	context
	 * @return				builder object with all moves added
	 */
	private MoveCollectionBuilder build(final King piece, final GameContext context) {
		// TODO castling
		return newBuilder(piece, context)
				.addMove(-1, +1)
				.addMove(0, +1)
				.addMove(+1, +1)
				.addMove(-1, 0)
				.addMove(+1, 0)
				.addMove(-1, -1)
				.addMove(0, -1)
				.addMove(+1, -1);
	}

	/**
	 * Creates builder and add all moves for the queen.
	 *
	 * @param	piece		queen piece
	 * @param	context
	 * @return				builder object with all moves added
	 */
	private MoveCollectionBuilder build(final Queen piece, final GameContext context) {
		return newBuilder(piece, context)
				.addStraightMoves()
				.addDiagonalMoves();
	}

	/**
	 * Creates builder and add all moves for the rook.
	 *
	 * @param	piece		rook piece
	 * @param	context
	 * @return				builder object with all moves added
	 */
	private MoveCollectionBuilder build(final Rook piece, final GameContext context) {
		return newBuilder(piece, context)
				.addStraightMoves();
	}

	/**
	 * Creates builder and add all moves for the knight.
	 *
	 * @param	piece		knight piece
	 * @param	context
	 * @return				builder object with all moves added
	 */
	private MoveCollectionBuilder build(final Knight piece, final GameContext context) {
		return newBuilder(piece, context)
				.addMove(-2, -1)
				.addMove(-1, -2)
				.addMove(+1, -2)
				.addMove(+2, -1)
				.addMove(+2, +1)
				.addMove(+1, +2)
				.addMove(-1, +2)
				.addMove(-2, +1);
	}

	private MoveCollectionBuilder build(final Bishop piece, final GameContext context) {
		return newBuilder(piece, context)
				.addDiagonalMoves();
	}

	/**
	 * Creates builder and add all moves for the pawn.
	 *
	 * @param	piece		pawn piece
	 * @param	context
	 * @return				builder object with all moves added
	 */
	private MoveCollectionBuilder build(final Pawn piece, final GameContext context) {
		final MoveCollectionBuilder builder = newBuilder(piece, context);
		final Square pos = context.getBoard().getPiecePosition(piece);
		final int row = pos.getRow();

		if (Color.WHITE == piece.getColor()) {
			if (builder.addNormalMove(0, +1).added()) {
				if (1 == row) {
					builder.addNormalMove(0, +2);
				}
			}
			builder.addCaptureMove(-1, +1);
			builder.addCaptureMove(+1, +1);
		} else {
			if (builder.addNormalMove(0, -1).added()) {
				if (6 == row) {
					builder.addNormalMove(0, -2);
				}
			}
			builder.addCaptureMove(-1, -1);
			builder.addCaptureMove(+1, -1);
		}

		// TODO en passant

		return builder;
	}

	/**
	 * Creates new buidler for provided piece.
	 *
	 * @param	piece
	 * @param	context
	 * @return				new builder object
	 */
	private MoveCollectionBuilder newBuilder(final Piece piece, final GameContext context) {
		return new MoveCollectionBuilder(piece, context, analyzer);
	}

}
