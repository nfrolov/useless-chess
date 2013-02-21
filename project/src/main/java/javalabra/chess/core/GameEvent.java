package javalabra.chess.core;

import java.util.Collection;

import javalabra.chess.domain.Board;
import javalabra.chess.domain.Square;

public interface GameEvent {

	Board getBoard();

	Square getCurrentPiece();

	Collection<Square> getLegalMoves();

	boolean isWhiteTurn();

	boolean isCheck();

	boolean isCheckmate();

}
