package javalabra.chess.core.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

public class MoveDirectorImpl implements MoveDirector {

	private final Board board;

	public MoveDirectorImpl(Board board) {
		this.board = board;
	}

	@Override
	public Set<Move> getLegalMoves(King piece) {
		final Set<Move> moves = new HashSet<Move>();
		final Square pos = board.getPiecePosition(piece);
		final int col = pos.getColumn(), row = pos.getRow();

		addMove(piece, moves, col - 1, row + 1);
		addMove(piece, moves, col, row + 1);
		addMove(piece, moves, col + 1, row + 1);

		addMove(piece, moves, col - 1, row);
		addMove(piece, moves, col + 1, row);

		addMove(piece, moves, col - 1, row - 1);
		addMove(piece, moves, col, row - 1);
		addMove(piece, moves, col + 1, row - 1);

		return moves;
	}

	@Override
	public Set<Move> getLegalMoves(Queen piece) {
		final Set<Move> moves = new HashSet<Move>();
		final Square pos = board.getPiecePosition(piece);
		final int col = pos.getColumn(), row = pos.getRow();

		addStraightMoves(piece, moves, col, row);
		addDiagonalMoves(piece, moves, col, row);

		return moves;
	}

	@Override
	public Set<Move> getLegalMoves(Rook piece) {
		final Set<Move> moves = new HashSet<Move>();
		final Square pos = board.getPiecePosition(piece);
		final int col = pos.getColumn(), row = pos.getRow();

		addStraightMoves(piece, moves, col, row);

		return moves;
	}

	@Override
	public Set<Move> getLegalMoves(Knight piece) {
		final Set<Move> moves = new HashSet<Move>();
		final Square pos = board.getPiecePosition(piece);
		final int col = pos.getColumn(), row = pos.getRow();

		addMove(piece, moves, col - 2, row - 1);
		addMove(piece, moves, col - 1, row - 2);
		addMove(piece, moves, col + 1, row - 2);
		addMove(piece, moves, col + 2, row - 1);

		addMove(piece, moves, col + 2, row + 1);
		addMove(piece, moves, col + 1, row + 2);
		addMove(piece, moves, col - 1, row + 2);
		addMove(piece, moves, col - 2, row + 1);

		return moves;
	}

	@Override
	public Set<Move> getLegalMoves(Bishop piece) {
		final Set<Move> moves = new HashSet<Move>();
		final Square pos = board.getPiecePosition(piece);
		final int col = pos.getColumn(), row = pos.getRow();

		addDiagonalMoves(piece, moves, col, row);

		return moves;
	}

	@Override
	public Set<Move> getLegalMoves(Pawn piece) {
		final Set<Move> moves = new HashSet<Move>();
		final Square pos = board.getPiecePosition(piece);
		final int col = pos.getColumn(), row = pos.getRow();

		if (Color.WHITE == piece.getColor()) {
			if (addNormalMove(piece, moves, col, row + 1)) {
				if (1 == row) {
					addNormalMove(piece, moves, col, row + 2);
				}
			}
			addCaptureMove(piece, moves, col - 1, row + 1);
			addCaptureMove(piece, moves, col + 1, row + 1);
		} else {
			if (addNormalMove(piece, moves, col, row - 1)) {
				if (6 == row) {
					addNormalMove(piece, moves, col, row - 2);
				}
			}
			addCaptureMove(piece, moves, col - 1, row - 1);
			addCaptureMove(piece, moves, col + 1, row - 1);
		}

		// TODO attack
		// TODO en passant

		return moves;
	}

	private boolean addNormalMove(Piece piece, Collection<Move> moves, int col, int row) {
		return addMove(piece, moves, col, row, false, false);
	}

	private boolean addCaptureMove(Piece piece, Collection<Move> moves, int col, int row) {
		return addMove(piece, moves, col, row, true, true);
	}

	private boolean addMove(Piece piece, Collection<Move> moves, int col, int row) {
		return addMove(piece, moves, col, row, true, false);
	}

	private void addStraightMoves(Piece piece, Collection<Move> moves, int col, int row) {
		addLineMoves(piece, moves, col, row, 0, -1);
		addLineMoves(piece, moves, col, row, +1, 0);
		addLineMoves(piece, moves, col, row, 0, +1);
		addLineMoves(piece, moves, col, row, -1, 0);
	}

	private void addDiagonalMoves(Piece piece, Collection<Move> moves, int col, int row) {
		addLineMoves(piece, moves, col, row, -1, -1);
		addLineMoves(piece, moves, col, row, -1, +1);
		addLineMoves(piece, moves, col, row, +1, -1);
		addLineMoves(piece, moves, col, row, +1, +1);
	}

	private void addLineMoves(Piece piece, Collection<Move> moves, int col, int row, int deltaCol, int deltaRow) {
		do {
			col += deltaCol;
			row += deltaRow;
		} while (addMove(piece, moves, col, row));
	}

	private boolean addMove(Piece piece, Collection<Move> moves, int col, int row, boolean canCapture, boolean captureOnly) {
		if (col < 0 || col > 7 || row < 0 || row > 7) {
			return false;
		}

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
