package javalabra.chess.core;

import javalabra.chess.domain.Bishop;
import javalabra.chess.domain.King;
import javalabra.chess.domain.Knight;
import javalabra.chess.domain.Pawn;
import javalabra.chess.domain.Queen;
import javalabra.chess.domain.Rook;

/**
 * Interface for delegating piece painting.
 *
 * @author Nikita Frolov
 */
public interface PiecePainter {

	/**
	 * Paint king.
	 *
	 * @param	piece
	 */
	void paint(King piece);

	/**
	 * Paint queen.
	 *
	 * @param	piece
	 */
	void paint(Queen piece);

	/**
	 * Paint rook.
	 *
	 * @param	piece
	 */
	void paint(Rook piece);

	/**
	 * Paint knight.
	 *
	 * @param	piece
	 */
	void paint(Knight piece);

	/**
	 * Paint bishop.
	 *
	 * @param	piece
	 */
	void paint(Bishop piece);

	/**
	 * Paint pawn.
	 *
	 * @param	piece
	 */
	void paint(Pawn piece);

}
