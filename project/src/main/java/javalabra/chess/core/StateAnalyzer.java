package javalabra.chess.core;

import javalabra.chess.domain.Color;

/**
 * Game state analyzer.
 *
 * @author Nikita Frolov
 */
public interface StateAnalyzer {

	/**
	 * Sets move director responsible for moves calculation.
	 *
	 * @param	director
	 */
	void setMoveDirector(MoveDirector director);

	/**
	 * Checks if the king of specified color is in check.
	 *
	 * @param	color	color of the king to check
	 * @param	context	context representing game's current state
	 * @return			true if in check
	 */
	boolean isCheck(Color color, GameContext context);

	/**
	 * Checks if the any king is checkmated.
	 *
	 * @param	context	context representing game's current state
	 * @return			true if checkmated
	 */
	boolean isCheckmate(GameContext context);

	/**
	 * Checks if the king of specified color is checkmated.
	 *
	 * @param	color	color of the king to check
	 * @param	context	context representing game's current state
	 * @return			true if checkmated
	 */
	boolean isCheckmate(Color color, GameContext context);

	/**
	 * Check if any king is in stalemate.
	 *
	 * @param	context	context representing game's current state
	 * @return			true if stalemate
	 */
	boolean isStalemate(GameContext context);

	/**
	 * Check is king of specified color is in stalemate.
	 *
	 * @param	color	color of the king to check
	 * @param	context	context representing game's current state
	 * @return			true if in stalemate
	 */
	boolean isStalemate(Color color, GameContext context);

}
