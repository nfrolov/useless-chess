package javalabra.chess.ui;

/**
 * The listener interface for receiving board events.
 *
 * @author Nikita Frolov
 */
public interface BoardListener {

	/**
	 * Triggered when square on the board is selected.
	 *
	 * @param	col		column index
	 * @param	row		row index
	 */
	void onSquareSelected(int col, int row);

}
