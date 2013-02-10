package javalabra.chess.core;

import javalabra.chess.domain.Board;

public interface GameContext {

	Board getBoard();

	GameContext copy();

}
