package javalabra.chess.core;

import java.util.Collection;

import javalabra.chess.domain.Board;
import javalabra.chess.domain.Move;
import javalabra.chess.domain.Piece;

/**
 * Facade to game logic. All the communication between logic and presentation tiers
 * should happen through this interface.
 *
 * @author Nikita Frolov
 */
public interface Game {

	/**
	 * Returns chessboard represented by game object.
	 *
	 * @return			board object
	 */
	Board getBoard();

	/**
	 * Returns collection of all the legal moves for concrete piece.
	 *
	 * @param	piece	piece to look moves for
	 * @return			collection of legal moves
	 */
	Collection<Move> getLegalMoves(Piece piece);

	/**
	 * Performs provided move.
	 *
	 * @param	move	move object to be executed
	 */
	void performMove(Move move);

}
