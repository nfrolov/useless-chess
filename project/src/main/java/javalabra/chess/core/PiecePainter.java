package javalabra.chess.core;

import javalabra.chess.domain.Bishop;
import javalabra.chess.domain.King;
import javalabra.chess.domain.Knight;
import javalabra.chess.domain.Pawn;
import javalabra.chess.domain.Queen;
import javalabra.chess.domain.Rook;

public interface PiecePainter {

	void paint(King piece);
	void paint(Queen piece);
	void paint(Rook piece);
	void paint(Knight piece);
	void paint(Bishop piece);
	void paint(Pawn piece);

}
