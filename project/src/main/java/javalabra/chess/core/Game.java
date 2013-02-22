package javalabra.chess.core;


/**
 * Game facade.
 *
 * @author Nikita Frolov
 */
public interface Game {

	/**
	 * Begin new game and bind it to the specified listener.
	 *
	 * @param listener	listener object that will receive game events
	 */
	void begin(GameListener listener);

}
