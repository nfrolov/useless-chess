package javalabra.chess.core;

import javalabra.chess.domain.Board;
import javalabra.chess.ui.BoardListener;

public interface GameListener {

	void setBoardListener(BoardListener listener);

	void update(Board board);

}
