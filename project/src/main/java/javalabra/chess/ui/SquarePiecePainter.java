package javalabra.chess.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javalabra.chess.core.PiecePainter;
import javalabra.chess.domain.Bishop;
import javalabra.chess.domain.King;
import javalabra.chess.domain.Knight;
import javalabra.chess.domain.Pawn;
import javalabra.chess.domain.Queen;
import javalabra.chess.domain.Rook;

import javax.imageio.ImageIO;

class SquarePiecePainter implements PiecePainter {

	private final SquarePanel panel;
	private final Graphics2D graphics;

	public SquarePiecePainter(final SquarePanel panel, final Graphics graphics) {
		this.panel = panel;
		this.graphics = (Graphics2D) graphics;
	}

	@Override
	public void paint(final King piece) {
		paintImage("king", piece.isWhite());
	}

	@Override
	public void paint(final Queen piece) {
		paintImage("queen", piece.isWhite());
	}

	@Override
	public void paint(final Rook piece) {
		paintImage("rook", piece.isWhite());
	}

	@Override
	public void paint(final Knight piece) {
		paintImage("knight", piece.isWhite());
	}

	@Override
	public void paint(final Bishop piece) {
		paintImage("bishop", piece.isWhite());
	}

	@Override
	public void paint(final Pawn piece) {
		paintImage("pawn", piece.isWhite());
	}

	protected void paintImage(final String name, final boolean white) {
		final BufferedImage image = loadImage(name, white);

		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		graphics.drawImage(image, 0, 0, panel.getWidth(), panel.getHeight(), null);
	}

	protected BufferedImage loadImage(final String name, final boolean white)  {
		final String prefix = "res/piece/", suffix = ".png", fullname;

		fullname = prefix + ((white ? "white." : "black.") + name) + suffix;

		try {
			final InputStream is = getClass().getResourceAsStream(fullname);
			return ImageIO.read(is);
		} catch (IOException e) {
			throw new RuntimeException("failed to load image: " + name, e);
		}
	}

}