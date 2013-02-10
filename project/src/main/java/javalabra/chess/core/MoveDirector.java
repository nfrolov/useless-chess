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
	 * @param	context
	 * @return			collection of valid moves
	 */
	Set<Move> getLegalMoves(King piece, GameContext context);

	/**
	 * Calculates moves for queen.
	 *
	 * @param	piece
	 * @param	context
	 * @return			collection of valid moves
	 */
	Set<Move> getLegalMoves(Queen piece, GameContext context);

	/**
	 * Calculates moves for rook.
	 *
	 * @param	piece
	 * @param	context
	 * @return			collection of valid moves
	 */
	Set<Move> getLegalMoves(Rook piece, GameContext context);

	/**
	 * Calculates moves for knight.
	 *
	 * @param	piece
	 * @param	context
	 * @return			collection of valid moves
	 */
	Set<Move> getLegalMoves(Knight piece, GameContext context);

	/**
	 * Calculates moves for bishop.
	 *
	 * @param	piece
	 * @param	context
	 * @return			collection of valid moves
	 */
	Set<Move> getLegalMoves(Bishop piece, GameContext context);

	/**
	 * Calculates moves for pawn.
	 *
	 * @param	piece
	 * @param	context
	 * @return			collection of valid moves
	 */
	Set<Move> getLegalMoves(Pawn piece, GameContext context);

}
