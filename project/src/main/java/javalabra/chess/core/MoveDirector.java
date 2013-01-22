package javalabra.chess.core;

import java.util.Set;

import javalabra.chess.domain.Bishop;
import javalabra.chess.domain.King;
import javalabra.chess.domain.Knight;
import javalabra.chess.domain.Pawn;
import javalabra.chess.domain.Queen;
import javalabra.chess.domain.Rook;
import javalabra.chess.domain.Square;

public interface MoveDirector {

	Set<Square> getLegalMoves(King piece);
	Set<Square> getLegalMoves(Queen piece);
	Set<Square> getLegalMoves(Rook piece);
	Set<Square> getLegalMoves(Knight piece);
	Set<Square> getLegalMoves(Bishop piece);
	Set<Square> getLegalMoves(Pawn piece);

}
