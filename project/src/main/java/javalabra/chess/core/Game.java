package javalabra.chess.core;


/**
 * Facade to game logic. All the communication between logic and presentation tiers
 * should happen through this interface.
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
