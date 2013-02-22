package javalabra.chess.domain;

/**
 *
 * @author Nikita Frolov
 */
public enum Color {

	WHITE, BLACK;

	/**
	 * Returns opposite color to current one.
	 *
	 * @return
	 */
	public Color opposite() {
		return this == WHITE ? BLACK : WHITE;
	}

}
