package javalabra.chess.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javalabra.chess.core.GameListener;
import javalabra.chess.domain.Board;
import javalabra.chess.domain.Piece;

import javax.swing.JPanel;

public class BoardPanel extends JPanel implements GameListener {

	private final SquareMouseListener mouseListener;
	private final SquarePanel[][] squares;
	private BoardListener boardListener;

	public BoardPanel() {
		super(new GridLayout(8, 8));

		mouseListener = new SquareMouseListener();
		squares = new SquarePanel[8][8];
		boardListener = null;

		// start from top left corner
		for (int row = 7; row >= 0; --row) {
			for (int column = 0; column <= 7; ++column) {
				final SquarePanel square = new SquarePanel(column, row, (row + column) % 2 == 0); // WTF
				square.addMouseListener(mouseListener);
				add(square);
				squares[column][row] = square;
			}
		}
	}

	@Override
	public void setBoardListener(final BoardListener listener) {
		this.boardListener = listener;
	}

	@Override
	public void update(final Board board) {
		for (int column = 0; column < 8; ++column) {
			for (int row = 0; row < 8; ++row) {
				final Piece piece = board.getSquare(column, row).getPiece();
				squares[column][row].setPiece(piece);
			}
		}
		repaint();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(520, 520);
	}

	@Override
	public void paint(Graphics g) {
		paintChildren(g);
	}

	private class SquareMouseListener extends MouseAdapter {

		@Override
		public void mouseReleased(final MouseEvent e) {
			final Component source = e.getComponent();
			if (null != source && source instanceof SquarePanel) {
				final SquarePanel square = (SquarePanel) source;
				boardListener.onSquareSelected(square.getColumn(), square.getRow());
			}
		}

	}

}
