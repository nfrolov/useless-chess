package javalabra.chess.domain;

public interface Move {

	Square getSource();
	Square getDestination();
	void perform(Board board);

}
