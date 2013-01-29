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

public class DefaultMoveDirector implements MoveDirector {

	private final Board board;

	public DefaultMoveDirector(Board board) {
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
		return null;
	}

	@Override
	public Set<Move> getLegalMoves(Rook piece) {
		final Set<Move> moves = new HashSet<Move>();
		final Square pos = board.getPiecePosition(piece);
		final int col = pos.getColumn(), row = pos.getRow();

		addVerticalMoves(piece, moves, col, row);
		addHorizontalMoves(piece, moves, col, row);

		return moves;
	}

	@Override
	public Set<Move> getLegalMoves(Knight piece) {
		return null;
	}

	@Override
	public Set<Move> getLegalMoves(Bishop piece) {
		return null;
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

	private void addVerticalMoves(Piece piece, Collection<Move> moves, int col, int row) {
		for (int i = row + 1; addMove(piece, moves, col, i); ++i);
		for (int i = row - 1; addMove(piece, moves, col, i); --i);
	}

	private void addHorizontalMoves(Piece piece, Collection<Move> moves, int col, int row) {
		for (int i = col + 1; addMove(piece, moves, i, row); ++i);
		for (int i = col - 1; addMove(piece, moves, i, row); --i);
	}

	private boolean addMove(Piece piece, Collection<Move> moves, int col, int row, boolean canCapture, boolean captureOnly) {
		if (col < 0 || col > 7 || row < 0 || row > 7) {
			return false;
		}

		final Square src = board.getPiecePosition(piece), dst = board.getSquare(col, row);
		Move move = null;

		if (dst.isOccupied()) {
			final Piece occupier = dst.getPiece();
			if (canCapture && occupier.getColor() != piece.getColor()) {
				move = new CaptureMove(piece, src, dst, occupier);
			}
		} else if (!captureOnly) {
			move = new NormalMove(piece, src, dst);
		}

		if (null == move) {
			return false;
		}

		// TODO add checks for check and checkmate

		moves.add(move);

		return true;
	}

}
