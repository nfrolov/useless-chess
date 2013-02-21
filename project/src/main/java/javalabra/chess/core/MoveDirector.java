package javalabra.chess.core;

import java.util.Collection;

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
	 * Sets state analyzer responsible for state checking.
	 *
	 * @param	analyzer
	 */
	void setStateAnalyzer(StateAnalyzer analyzer);

	/**
	 * Calculates legals moves for king.
	 *
	 * @param	piece
	 * @param	context
	 * @return			collection of valid moves
	 */
	Collection<Move> getLegalMoves(King piece, GameContext context);

	/**
	 * Calculates candidate moves for king without analyzing them.
	 *
	 * @param	piece
	 * @param	context
	 * @return			collection of valid moves
	 */
	Collection<Move> getCandidateMoves(King piece, GameContext context);

	/**
	 * Calculates legal moves for queen.
	 *
	 * @param	piece
	 * @param	context
	 * @return			collection of valid moves
	 */
	Collection<Move> getLegalMoves(Queen piece, GameContext context);

	/**
	 * Calculates candidate moves for queen without analyzing them.
	 *
	 * @param	piece
	 * @param	context
	 * @return			collection of valid moves
	 */
	Collection<Move> getCandidateMoves(Queen piece, GameContext context);

	/**
	 * Calculates legal moves for rook.
	 *
	 * @param	piece
	 * @param	context
	 * @return			collection of valid moves
	 */
	Collection<Move> getLegalMoves(Rook piece, GameContext context);

	/**
	 * Calculates candidate moves for rook without analyzing them.
	 *
	 * @param	piece
	 * @param	context
	 * @return			collection of valid moves
	 */
	Collection<Move> getCandidateMoves(Rook piece, GameContext context);

	/**
	 * Calculates moves for knight.
	 *
	 * @param	piece
	 * @param	context
	 * @return			collection of valid moves
	 */
	Collection<Move> getLegalMoves(Knight piece, GameContext context);

	/**
	 * Calculates candidate moves for knight without analyzing them.
	 *
	 * @param	piece
	 * @param	context
	 * @return			collection of valid moves
	 */
	Collection<Move> getCandidateMoves(Knight piece, GameContext context);

	/**
	 * Calculates legal moves for bishop.
	 *
	 * @param	piece
	 * @param	context
	 * @return			collection of valid moves
	 */
	Collection<Move> getLegalMoves(Bishop piece, GameContext context);

	/**
	 * Calculates candidate moves for bishop without analyzing them.
	 *
	 * @param	piece
	 * @param	context
	 * @return			collection of valid moves
	 */
	Collection<Move> getCandidateMoves(Bishop piece, GameContext context);

	/**
	 * Calculates legal moves for pawn.
	 *
	 * @param	piece
	 * @param	context
	 * @return			collection of valid moves
	 */
	Collection<Move> getLegalMoves(Pawn piece, GameContext context);

	/**
	 * Calculates candidate moves for pawn without analyzing them.
	 *
	 * @param	piece
	 * @param	context
	 * @return			collection of valid moves
	 */
	Collection<Move> getCandidateMoves(Pawn piece, GameContext context);

}
