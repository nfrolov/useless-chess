package javalabra.chess.core.impl;

import javalabra.chess.core.GameContext;
import javalabra.chess.core.MoveDirector;

public interface GameState {

	GameState handle(int column, int row, MoveDirector director, GameContext context, GameEventEmitter em);

}
