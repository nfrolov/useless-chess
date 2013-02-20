package javalabra.chess.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;

import javalabra.chess.core.GameEvent;
import javalabra.chess.core.GameListener;
import javalabra.chess.domain.Board;
import javalabra.chess.domain.Piece;
import javalabra.chess.domain.Square;

import javax.swing.BorderFactory;
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
	public void gameChanged(final GameEvent e) {
		final Board board = e.getBoard();

		for (int column = 0; column < 8; ++column) {
			for (int row = 0; row < 8; ++row) {
				final Piece piece = board.getSquare(column, row).getPiece();
				final SquarePanel square = squares[column][row];
				square.setPiece(piece);
				square.setBorder(BorderFactory.createEmptyBorder());
			}
		}

		final Square current = e.getCurrentPiece();
		final Collection<Square> moves = e.getLegalMoves();

		if (null != current) {
			highlightSquare(current, new Color(0x4382C6));
			for (final Square ms : moves) {
				highlightSquare(ms, new Color(0x52CA41));
			}
		}

		repaint();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(520, 520);
	}

	@Override
	public void paintComponent(final Graphics g) {
	}

	private void highlightSquare(final Square square, final Color color) {
		final int col = square.getColumn(), row = square.getRow();

		squares[col][row].setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(2, 2, 2, 2),
				BorderFactory.createLineBorder(color, 2)
				));
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
