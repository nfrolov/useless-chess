package javalabra.chess.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents abstract player.
 *
 * @author Nikita Frolov
 */
public abstract class Player {

	private final List<Piece> pieces;

	public Player() {
		pieces = new ArrayList<Piece>();
	}

	public List<Piece> getPieces() {
		return pieces;
	}

}
