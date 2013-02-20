package javalabra.chess.core;

import javalabra.chess.ui.BoardListener;

public interface GameListener {

	void setBoardListener(BoardListener listener);

	void gameChanged(GameEvent e);

}
