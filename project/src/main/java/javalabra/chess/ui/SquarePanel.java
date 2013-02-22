package javalabra.chess.ui;

import java.awt.Color;
import java.awt.Graphics;

import javalabra.chess.domain.Piece;

import javax.swing.JPanel;

/**
 * Component that draws single square of the board.
 *
 * @author Nikita Frolov
 */
class SquarePanel extends JPanel {

	private static final Color COLOR_WHITE = new Color(0xAD6D2F), COLOR_BLACK = new Color(0xFFCE9E);

	private final int column, row;
	private Piece piece;

	public SquarePanel(final int column, final int row, final boolean white) {
		super();

		this.column = column;
		this.row = row;

		setBackground(white ? COLOR_WHITE : COLOR_BLACK);
	}

	public void setPiece(final Piece piece) {
		this.piece = piece;
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	@Override
	public void paintComponent(final Graphics g) {
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		paintPiece(g);
	}

	protected void paintPiece(final Graphics g) {
		if (null != piece) {
			piece.paint(new SquarePiecePainter(this, g.create()));
		}
	}

}