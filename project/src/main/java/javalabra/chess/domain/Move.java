package javalabra.chess.domain;

public interface Move {

	Square getDestination();
	void perform(Board board);

}
