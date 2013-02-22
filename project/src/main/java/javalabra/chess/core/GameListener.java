package javalabra.chess.core;

import javalabra.chess.ui.BoardListener;

/**
 * The listener interface for receiving game events.
 *
 * @author Nikita Frolov
 */
public interface GameListener {

	/**
	 * Sets board listener.
	 *
	 * @param	listener	listener object
	 */
	void setBoardListener(BoardListener listener);

	/**
	 * Triggered when game state is changed.
	 *
	 * @param	e			event object
	 */
	void gameChanged(GameEvent e);

}
