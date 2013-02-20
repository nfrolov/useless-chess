package javalabra.chess.ui;

import java.awt.BorderLayout;
import java.awt.Container;

import javalabra.chess.core.GameEvent;
import javalabra.chess.core.GameListener;

import javax.swing.JFrame;

public class Chess implements GameListener {

	private JFrame frame;
	private BoardPanel panel;

	public Chess() {
		construct();
	}

	@Override
	public void setBoardListener(final BoardListener listener) {
		panel.setBoardListener(listener);
	}

	@Override
	public void gameChanged(final GameEvent e) {
		panel.gameChanged(e);
	}

	public void display() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	private void construct() {
		frame = new JFrame();
		panel = new BoardPanel();

		final Container container = frame.getContentPane();

		container.setLayout(new BorderLayout());
		container.add(panel, BorderLayout.CENTER);
	}

}
