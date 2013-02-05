package javalabra.chess.core;

import javalabra.chess.domain.Color;

/**
 * Game state analyzer.
 *
 * @author Nikita Frolov
 */
public interface StateAnalyzer {

	/**
	 * Checks if the king of specified color is in check.
	 *
	 * @param	color	color of the king to check
	 * @return			true if in check
	 */
	boolean isCheck(Color color);

	/**
	 * Checks if the any king is checkmated.
	 *
	 * @return			true if checkmated
	 */
	boolean isCheckmate();

	/**
	 * Checks if the king of specified color is checkmated.
	 *
	 * @param	color	color of the king to check
	 * @return			true if checkmated
	 */
	boolean isCheckmate(Color color);

}
