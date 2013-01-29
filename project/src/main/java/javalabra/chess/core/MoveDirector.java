package javalabra.chess.core;

import java.util.Set;

import javalabra.chess.domain.Bishop;
import javalabra.chess.domain.King;
import javalabra.chess.domain.Knight;
import javalabra.chess.domain.Move;
import javalabra.chess.domain.Pawn;
import javalabra.chess.domain.Queen;
import javalabra.chess.domain.Rook;

public interface MoveDirector {

	Set<Move> getLegalMoves(King piece);
	Set<Move> getLegalMoves(Queen piece);
	Set<Move> getLegalMoves(Rook piece);
	Set<Move> getLegalMoves(Knight piece);
	Set<Move> getLegalMoves(Bishop piece);
	Set<Move> getLegalMoves(Pawn piece);

}
