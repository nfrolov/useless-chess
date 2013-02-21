package javalabra.chess.ui;

import java.awt.BorderLayout;

import javalabra.chess.core.GameEvent;
import javalabra.chess.core.GameListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Chess implements GameListener {

	private static final String STATUS_BEGIN = "White begin!", STATUS_CHECK = "Check!", STATUS_CHECKMATE = "Checkmate!",
			STATUS_TURN_WHITE = "Waiting white to move", STATUS_TURN_BLACK = "Waitining black to move";

	private JFrame frame;
	private BoardPanel panel;
	private JLabel status;

	public Chess() {
		construct();
	}

	@Override
	public void setBoardListener(final BoardListener listener) {
		panel.setBoardListener(listener);
	}

	@Override
	public void gameChanged(final GameEvent e) {
		updateStatus(e);
		panel.gameChanged(e);
	}

	public void display() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	private void updateStatus(final GameEvent e) {
		final StringBuilder sb = new StringBuilder();

		if (e.isCheckmate()) {
			sb.append(STATUS_CHECKMATE).append(" ");
		} else {
			if (e.isCheck()) {
				sb.append(STATUS_CHECK).append(" ");
			}
			if (e.isWhiteTurn()) {
				sb.append(STATUS_TURN_WHITE);
			} else {
				sb.append(STATUS_TURN_BLACK);
			}
		}

		status.setText(sb.toString());
	}

	private void construct() {
		frame = new JFrame();

		final JPanel container = new JPanel(new BorderLayout());
		container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		frame.setContentPane(container);

		panel = new BoardPanel();
		container.add(panel, BorderLayout.CENTER);

		final JPanel statusPanel = new JPanel();
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		statusPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

		status = new JLabel();
		status.setHorizontalAlignment(SwingConstants.LEFT);
		statusPanel.add(status);

		container.add(statusPanel, BorderLayout.SOUTH);
	}

}
