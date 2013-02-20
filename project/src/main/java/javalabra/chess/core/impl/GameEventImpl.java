package javalabra.chess.core.impl;

import java.util.Collection;

import javalabra.chess.core.GameEvent;
import javalabra.chess.domain.Board;
import javalabra.chess.domain.Square;

public class GameEventImpl implements GameEvent {

	private final Board board;
	private final Square currentPiece;
	private final Collection<Square> legalMoves;
	private final boolean whiteTurn;

	public GameEventImpl(final Board board, final boolean whiteTurn, final Square current, final Collection<Square> moves) {
		this.board = board;
		this.whiteTurn = whiteTurn;
		this.currentPiece = current;
		this.legalMoves = moves;
	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public Square getCurrentPiece() {
		return currentPiece;
	}

	@Override
	public Collection<Square> getLegalMoves() {
		return legalMoves;
	}

	@Override
	public boolean isWhiteTurn() {
		return whiteTurn;
	}

}
