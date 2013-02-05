package javalabra.chess.core;

import javalabra.chess.domain.Color;

public interface StateAnalyzer {

	boolean isCheck(Color color);
	boolean isCheckmate();
	boolean isCheckmate(Color color);

}
