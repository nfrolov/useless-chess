package javalabra.chess.domain;

/**
 * Represents single player's move, usually known as ply.
 *
 * @author Nikita Frolov
 */
public interface Move {

	/**
	 * Returns position to move from.
	 *
	 * @return			square to move from
	 */
	Square getSource();

	/**
	 * Returns position to move to.
	 *
	 * @return			square to move to
	 */
	Square getDestination();

	/**
	 * Performs move on specified board.
	 *
	 * @param	board	board to make move on
	 */
	void perform(Board board);

}
