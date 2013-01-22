package javalabra.chess.core;

import javalabra.chess.domain.Board;
import javalabra.chess.domain.PlayerBlack;
import javalabra.chess.domain.PlayerWhite;

public interface BoardBuilder {

	Board build(PlayerWhite white, PlayerBlack black);

}
