package javalabra.chess.domain;

/**
 *
 * @author Nikita Frolov
 */
public enum Color {

	WHITE, BLACK;

	public Color opposite() {
		return this == WHITE ? BLACK : WHITE;
	}

}
