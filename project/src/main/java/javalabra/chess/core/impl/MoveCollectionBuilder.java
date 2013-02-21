package javalabra.chess.core.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javalabra.chess.core.GameContext;
import javalabra.chess.core.move.CaptureMove;
import javalabra.chess.core.move.NormalMove;
import javalabra.chess.domain.Board;
import javalabra.chess.domain.Move;
import javalabra.chess.domain.Piece;
import javalabra.chess.domain.Square;

public class MoveCollectionBuilder {

	private final Piece piece;
	private final GameContext context;
	private final Board board;
	private final Square source;

	private final Set<Move> moves;

	private int added;

	public MoveCollectionBuilder(final Piece piece, final GameContext context) {
		this.piece = piece;
		this.context = context;
		this.board = context.getBoard();
		this.source = board.getPiecePosition(piece);
		this.moves = new HashSet<Move>();
		this.added = 0;
	}

	public MoveCollectionBuilder addMove(final int deltaColumn, final int deltaRow) {
		final int column = source.getColumn(), row = source.getRow();

		added = 0;
		addMove(column + deltaColumn, row + deltaRow, true, false);

		return this;
	}

	public MoveCollectionBuilder addStraightMoves() {
		added = 0;
		addLineMoves(0, -1);
		addLineMoves(+1, 0);
		addLineMoves(0, +1);
		addLineMoves(-1, 0);

		return this;
	}

	public MoveCollectionBuilder addDiagonalMoves() {
		added = 0;
		addLineMoves(-1, -1);
		addLineMoves(-1, +1);
		addLineMoves(+1, -1);
		addLineMoves(+1, +1);

		return this;
	}

	public MoveCollectionBuilder addNormalMove(int deltaColumn, int deltaRow) {
		final int column = source.getColumn(), row = source.getRow();

		added = 0;
		addMove(column + deltaColumn, row + deltaRow, false, false);

		return this;
	}

	public MoveCollectionBuilder addCaptureMove(int deltaColumn, int deltaRow) {
		final int column = source.getColumn(), row = source.getRow();

		added = 0;
		addMove(column + deltaColumn, row + deltaRow, true, true);

		return this;
	}

	public boolean added() {
		return 0 != added;
	}

	public Collection<Move> getCandidateMoves() {
		return moves;
	}

	public Collection<Move> getLegalMoves() {
		// FIXME analyze moves
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
		moves.add(move);
		++added;
	}

}