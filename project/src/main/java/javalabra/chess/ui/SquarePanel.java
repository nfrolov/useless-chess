package javalabra.chess.ui;

import java.awt.Color;
import java.awt.Graphics;

import javalabra.chess.domain.Piece;
import javalabra.chess.domain.Square;

import javax.swing.JPanel;

class SquarePanel extends JPanel {

	private final Square square;

	public SquarePanel(final Square square) {
		super();

		this.square = square;

		if (square.isWhite()) {
			setBackground(new Color(0xAD6D2F));
		} else {
			setBackground(new Color(0xFFCE9E));
		}
	}

	@Override
	public void paint(final Graphics g) {
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		paintPiece(g);
	}

	protected void paintPiece(final Graphics g) {
		final Piece piece = square.getPiece();
		if (null != piece) {
			piece.paint(new SquarePiecePainter(this, g.create()));
		}
	}

}