package javalabra.chess.core;

import java.util.Set;

import javalabra.chess.domain.Bishop;
import javalabra.chess.domain.King;
import javalabra.chess.domain.Knight;
import javalabra.chess.domain.Move;
import javalabra.chess.domain.Pawn;
import javalabra.chess.domain.Queen;
import javalabra.chess.domain.Rook;

/**
 * Move calculator for pieces.
 *
 * @author Nikita Frolov
 */
public interface MoveDirector {

	/**
	 * Calculates moves for king.
	 *
	 * @param	piece
	 * @return			collection of valid moves
	 */
	Set<Move> getLegalMoves(King piece);

	/**
	 * Calculates moves for queen.
	 *
	 * @param	piece
	 * @return			collection of valid moves
	 */
	Set<Move> getLegalMoves(Queen piece);

	/**
	 * Calculates moves for rook.
	 *
	 * @param	piece
	 * @return			collection of valid moves
	 */
	Set<Move> getLegalMoves(Rook piece);

	/**
	 * Calculates moves for knight.
	 *
	 * @param	piece
	 * @return			collection of valid moves
	 */
	Set<Move> getLegalMoves(Knight piece);

	/**
	 * Calculates moves for bishop.
	 *
	 * @param	piece
	 * @return			collection of valid moves
	 */
	Set<Move> getLegalMoves(Bishop piece);

	/**
	 * Calculates moves for pawn.
	 *
	 * @param	piece
	 * @return			collection of valid moves
	 */
	Set<Move> getLegalMoves(Pawn piece);

}
