package javalabra.chess.core;

import java.util.Collection;

import javalabra.chess.domain.Board;
import javalabra.chess.domain.Move;
import javalabra.chess.domain.Piece;

public interface Game {

	Board getBoard();

	Collection<Move> getLegalMoves(Piece piece);

	void performMove(Move move);

}
