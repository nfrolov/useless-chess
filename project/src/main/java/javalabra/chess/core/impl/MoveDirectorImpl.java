package javalabra.chess.core.impl;

import java.util.HashSet;
import java.util.Set;

import javalabra.chess.core.GameContext;
import javalabra.chess.core.MoveDirector;
import javalabra.chess.core.move.CaptureMove;
import javalabra.chess.core.move.NormalMove;
import javalabra.chess.domain.Bishop;
import javalabra.chess.domain.Board;
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

	public MoveDirectorImpl() {
	}

	@Override
	public Set<Move> getLegalMoves(final King piece, final GameContext context) {
		return newBuilder(piece, context)
				.addMove(-1, +1)
				.addMove(0, +1)
				.addMove(+1, +1)
				.addMove(-1, 0)
				.addMove(+1, 0)
				.addMove(-1, -1)
				.addMove(0, -1)
				.addMove(+1, -1)
				.build();
	}

	@Override
	public Set<Move> getLegalMoves(final Queen piece, final GameContext context) {
		return newBuilder(piece, context)
				.addStraightMoves()
				.addDiagonalMoves()
				.build();
	}

	@Override
	public Set<Move> getLegalMoves(final Rook piece, final GameContext context) {
		return newBuilder(piece, context)
				.addStraightMoves()
				.build();
	}

	@Override
	public Set<Move> getLegalMoves(final Knight piece, final GameContext context) {
		return newBuilder(piece, context)
				.addMove(-2, -1)
				.addMove(-1, -2)
				.addMove(+1, -2)
				.addMove(+2, -1)
				.addMove(+2, +1)
				.addMove(+1, +2)
				.addMove(-1, +2)
				.addMove(-2, +1)
				.build();
	}

	@Override
	public Set<Move> getLegalMoves(final Bishop piece, final GameContext context) {
		return newBuilder(piece, context)
				.addDiagonalMoves()
				.build();
	}

	@Override
	public Set<Move> getLegalMoves(final Pawn piece, final GameContext context) {
		final MoveSetBuilder builder = newBuilder(piece, context);
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

		return builder.build();
	}

	private MoveSetBuilder newBuilder(final Piece piece, final GameContext context) {
		return new MoveSetBuilder(piece, context);
	}

	private class MoveSetBuilder {

		private final Piece piece;
		private final GameContext context;
		private final Board board;
		private final Square source;

		private final Set<Move> moves;

		private int added;

		public MoveSetBuilder(final Piece piece, final GameContext context) {
			this.piece = piece;
			this.context = context;
			this.board = context.getBoard();
			this.source = board.getPiecePosition(piece);
			this.moves = new HashSet<Move>();
			this.added = 0;
		}

		public MoveSetBuilder addMove(final int deltaColumn, final int deltaRow) {
			final int column = source.getColumn(), row = source.getRow();

			added = 0;
			addMove(column + deltaColumn, row + deltaRow, true, false);

			return this;
		}

		public MoveSetBuilder addStraightMoves() {
			added = 0;
			addLineMoves(0, -1);
			addLineMoves(+1, 0);
			addLineMoves(0, +1);
			addLineMoves(-1, 0);

			return this;
		}

		public MoveSetBuilder addDiagonalMoves() {
			added = 0;
			addLineMoves(-1, -1);
			addLineMoves(-1, +1);
			addLineMoves(+1, -1);
			addLineMoves(+1, +1);

			return this;
		}

		public MoveSetBuilder addNormalMove(int deltaColumn, int deltaRow) {
			final int column = source.getColumn(), row = source.getRow();

			added = 0;
			addMove(column + deltaColumn, row + deltaRow, false, false);

			return this;
		}

		public MoveSetBuilder addCaptureMove(int deltaColumn, int deltaRow) {
			final int column = source.getColumn(), row = source.getRow();

			added = 0;
			addMove(column + deltaColumn, row + deltaRow, true, true);

			return this;
		}

		public boolean added() {
			return 0 != added;
		}

		public Set<Move> build() {
			return moves;
		}

		private void addLineMoves(final int deltaCol, final int deltaRow) {
			int column = source.getColumn(), row = source.getRow();
			do {
				column += deltaCol;
				row += deltaRow;
			} while (addMove(column, row, true, false));
		}

		private boolean addMove(int column, int row, boolean canCapture, boolean captureOnly) {
			if (column < 0 || column > 7 || row < 0 || row > 7) {
				return false;
			}

			final Board board = context.getBoard();
			final Square destination = board.getSquare(column, row);

			if (destination.isOccupied()) {
				final Piece occupier = destination.getPiece();
				if (canCapture && occupier.getColor() != piece.getColor()) {
					add(new CaptureMove(piece, source, destination, occupier));
				}
				return false;
			} else if (!captureOnly) {
				add(new NormalMove(piece, source, destination));
				return true;
			}

			return false;
		}

		private void add(final Move move) {
			// TODO add check for check :-)
			moves.add(move);
			++added;
		}

	}

}
