package javalabra.chess;

import javalabra.chess.core.impl.GameImpl;
import javalabra.chess.ui.Chess;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Chess(new GameImpl()).display();
				} catch (Exception e) {
					// FIXME display error dialog
					throw new RuntimeException(e);
				}
			}
		});
	}

}
