package javalabra.chess.core;


/**
 * Facade to game logic. All the communication between logic and presentation tiers
 * should happen through this interface.
 *
 * @author Nikita Frolov
 */
public interface Game {

	void begin(GameListener listener);

}
