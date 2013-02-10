package javalabra.chess.core.impl;

import java.util.Collection;
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
		final Set<Move> moves = new HashSet<Move>();
		final Square pos = context.getBoard().getPiecePosition(piece);
		final int col = pos.getColumn(), row = pos.getRow();

		addMove(context, piece, moves, col - 1, row + 1);
		addMove(context, piece, moves, col, row + 1);
		addMove(context, piece, moves, col + 1, row + 1);

		addMove(context, piece, moves, col - 1, row);
		addMove(context, piece, moves, col + 1, row);

		addMove(context, piece, moves, col - 1, row - 1);
		addMove(context, piece, moves, col, row - 1);
		addMove(context, piece, moves, col + 1, row - 1);

		return moves;
	}

	@Override
	public Set<Move> getLegalMoves(final Queen piece, final GameContext context) {
		final Set<Move> moves = new HashSet<Move>();
		final Square pos = context.getBoard().getPiecePosition(piece);
		final int col = pos.getColumn(), row = pos.getRow();

		addStraightMoves(context, piece, moves, col, row);
		addDiagonalMoves(context, piece, moves, col, row);

		return moves;
	}

	@Override
	public Set<Move> getLegalMoves(final Rook piece, final GameContext context) {
		final Set<Move> moves = new HashSet<Move>();
		final Square pos = context.getBoard().getPiecePosition(piece);
		final int col = pos.getColumn(), row = pos.getRow();

		addStraightMoves(context, piece, moves, col, row);

		return moves;
	}

	@Override
	public Set<Move> getLegalMoves(final Knight piece, final GameContext context) {
		final Set<Move> moves = new HashSet<Move>();
		final Square pos = context.getBoard().getPiecePosition(piece);
		final int col = pos.getColumn(), row = pos.getRow();

		addMove(context, piece, moves, col - 2, row - 1);
		addMove(context, piece, moves, col - 1, row - 2);
		addMove(context, piece, moves, col + 1, row - 2);
		addMove(context, piece, moves, col + 2, row - 1);

		addMove(context, piece, moves, col + 2, row + 1);
		addMove(context, piece, moves, col + 1, row + 2);
		addMove(context, piece, moves, col - 1, row + 2);
		addMove(context, piece, moves, col - 2, row + 1);

		return moves;
	}

	@Override
	public Set<Move> getLegalMoves(final Bishop piece, final GameContext context) {
		final Set<Move> moves = new HashSet<Move>();
		final Square pos = context.getBoard().getPiecePosition(piece);
		final int col = pos.getColumn(), row = pos.getRow();

		addDiagonalMoves(context, piece, moves, col, row);

		return moves;
	}

	@Override
	public Set<Move> getLegalMoves(final Pawn piece, final GameContext context) {
		final Set<Move> moves = new HashSet<Move>();
		final Square pos = context.getBoard().getPiecePosition(piece);
		final int col = pos.getColumn(), row = pos.getRow();

		if (Color.WHITE == piece.getColor()) {
			if (addNormalMove(context, piece, moves, col, row + 1)) {
				if (1 == row) {
					addNormalMove(context, piece, moves, col, row + 2);
				}
			}
			addCaptureMove(context, piece, moves, col - 1, row + 1);
			addCaptureMove(context, piece, moves, col + 1, row + 1);
		} else {
			if (addNormalMove(context, piece, moves, col, row - 1)) {
				if (6 == row) {
					addNormalMove(context, piece, moves, col, row - 2);
				}
			}
			addCaptureMove(context, piece, moves, col - 1, row - 1);
			addCaptureMove(context, piece, moves, col + 1, row - 1);
		}

		// TODO attack
		// TODO en passant

		return moves;
	}

	private boolean addNormalMove(GameContext context, Piece piece, Collection<Move> moves, int col, int row) {
		return addMove(context, piece, moves, col, row, false, false);
	}

	private boolean addCaptureMove(GameContext context, Piece piece, Collection<Move> moves, int col, int row) {
		return addMove(context, piece, moves, col, row, true, true);
	}

	private boolean addMove(GameContext context, Piece piece, Collection<Move> moves, int col, int row) {
		return addMove(context, piece, moves, col, row, true, false);
	}

	private void addStraightMoves(GameContext context, Piece piece, Collection<Move> moves, int col, int row) {
		addLineMoves(context, piece, moves, col, row, 0, -1);
		addLineMoves(context, piece, moves, col, row, +1, 0);
		addLineMoves(context, piece, moves, col, row, 0, +1);
		addLineMoves(context, piece, moves, col, row, -1, 0);
	}

	private void addDiagonalMoves(GameContext context, Piece piece, Collection<Move> moves, int col, int row) {
		addLineMoves(context, piece, moves, col, row, -1, -1);
		addLineMoves(context, piece, moves, col, row, -1, +1);
		addLineMoves(context, piece, moves, col, row, +1, -1);
		addLineMoves(context, piece, moves, col, row, +1, +1);
	}

	private void addLineMoves(GameContext context, Piece piece, Collection<Move> moves, int col, int row, int deltaCol, int deltaRow) {
		do {
			col += deltaCol;
			row += deltaRow;
		} while (addMove(context, piece, moves, col, row));
	}

	private boolean addMove(GameContext context, Piece piece, Collection<Move> moves, int col, int row, boolean canCapture, boolean captureOnly) {
		if (col < 0 || col > 7 || row < 0 || row > 7) {
			return false;
		}

		final Board board = context.getBoard();
		final Square src = board.getPiecePosition(piece), dst = board.getSquare(col, row);

		if (dst.isOccupied()) {
			final Piece occupier = dst.getPiece();
			if (canCapture && occupier.getColor() != piece.getColor()) {
				moves.add(new CaptureMove(piece, src, dst, occupier));
			}
			return false;
		} else if (!captureOnly) {
			moves.add(new NormalMove(piece, src, dst));
			return true;
		}

		// TODO add checks for check and checkmate

		return false;
	}

}
