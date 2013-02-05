package javalabra.chess.core;

import javalabra.chess.domain.Board;
import javalabra.chess.domain.PlayerBlack;
import javalabra.chess.domain.PlayerWhite;

/**
 * Chessboard builder.
 *
 * @author Nikita Frolov
 */
public interface BoardBuilder {

	/**
	 * Places pieces on the board.
	 *
	 * @param	white	white player
	 * @param	black	black player
	 * @return			constructed board
	 */
	Board build(PlayerWhite white, PlayerBlack black);

}
