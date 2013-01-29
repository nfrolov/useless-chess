package javalabra.chess.core.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javalabra.chess.core.MoveDirector;
import javalabra.chess.domain.Bishop;
import javalabra.chess.domain.Board;
import javalabra.chess.domain.Color;
import javalabra.chess.domain.King;
import javalabra.chess.domain.Knight;
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
	public Set<Square> getLegalMoves(King piece) {
		return null;
	}

	@Override
	public Set<Square> getLegalMoves(Queen piece) {
		return null;
	}

	@Override
	public Set<Square> getLegalMoves(Rook piece) {
		return null;
	}

	@Override
	public Set<Square> getLegalMoves(Knight piece) {
		return null;
	}

	@Override
	public Set<Square> getLegalMoves(Bishop piece) {
		return null;
	}

	@Override
	public Set<Square> getLegalMoves(Pawn piece) {
		final Set<Square> moves = new HashSet<Square>();
		final Square pos = board.getPiecePosition(piece);
		final int col = pos.getColumn(), row = pos.getRow();

		if (Color.WHITE == piece.getColor()) {
			addSimpleMove(piece, moves, col, row + 1);
			if (1 == row) {
				addSimpleMove(piece, moves, col, row + 2);
			}
		} else {
			addSimpleMove(piece, moves, col, row - 1);
			if (6 == row) {
				addSimpleMove(piece, moves, col, row - 2);
			}
		}

		// TODO attack
		// TODO en passant

		return moves;
	}

	private void addSimpleMove(Piece piece, Collection<Square> moves, int col, int row) {
		if (col < 0 || col > 7 || row < 0 || row > 7) {
			return;
		}

		final Square square = board.getSquare(col, row);

		if (square.isOccupied() && square.getPiece().getColor() == piece.getColor()) {
			return;
		}

		// TODO add checks for check and checkmate

		moves.add(square);
	}

}
