package javalabra.chess.ui;

import java.awt.BorderLayout;
import java.awt.Container;

import javalabra.chess.core.Game;

import javax.swing.JFrame;

public class Chess {

	private final Game game;

	private JFrame frame;

	public Chess(final Game game) {
		this.game = game;
		construct();
	}

	public void display() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	private void construct() {
		frame = new JFrame();

		final Container container = frame.getContentPane();

		container.setLayout(new BorderLayout());
		container.add(new BoardPanel(game.getBoard()), BorderLayout.CENTER);
	}

}
