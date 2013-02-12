package javalabra.chess.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javalabra.chess.domain.Board;
import javalabra.chess.domain.Square;

import javax.swing.JPanel;

public class BoardPanel extends JPanel {

	private final Board board;

	public BoardPanel(final Board board) {
		super(new GridLayout(8, 8));

		for (int row = 7; row >= 0; --row) {
			for (int column = 0; column <= 7; ++column) {
				final Square square = board.getSquare(column, row);
				add(new SquarePanel(square));
			}
		}

		this.board = board;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(520, 520);
	}

	@Override
	public void paint(Graphics g) {
		paintChildren(g);
	}

}
