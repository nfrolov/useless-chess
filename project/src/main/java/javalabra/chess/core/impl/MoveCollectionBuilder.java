package javalabra.chess.core.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javalabra.chess.core.GameContext;
import javalabra.chess.core.StateAnalyzer;
import javalabra.chess.core.impl.move.CaptureMove;
import javalabra.chess.core.impl.move.NormalMove;
import javalabra.chess.domain.Board;
import javalabra.chess.domain.Move;
import javalabra.chess.domain.Piece;
import javalabra.chess.domain.Square;

/**
 * Helper class to build moves' collections.
 *
 * @author Nikita Frolov
 */
public class MoveCollectionBuilder {

	/**
	 * Piece for which moves are constructed.
	 */
	private final Piece piece;

	/**
	 * Context representing game's current state.
	 */
	private final GameContext context;

	/**
	 * Current board.
	 */
	private final Board board;

	/**
	 * Piece's current position.
	 */
	private final Square source;

	/**
	 * State analyzer used for check/checkmate validation.
	 */
	private final StateAnalyzer analyzer;

	/**
	 * Collection containing all calculated moves.
	 */
	private final Set<Move> moves;

	/**
	 * Represents how many moves were added by last add command.
	 */
	private int added;

	/**
	 * Constructs builder object for specified piece in given context.
	 *
	 * @param	piece
	 * @param	context
	 * @param	analyzer		state
	 */
	public MoveCollectionBuilder(final Piece piece, final GameContext context, final StateAnalyzer analyzer) {
		this.piece = piece;
		this.context = context;
		this.analyzer = analyzer;

		this.board = context.getBoard();
		this.source = board.getPiecePosition(piece);
		this.moves = new HashSet<Move>();
		this.added = 0;
	}

	/**
	 * Adds any move specified by offsets.
	 *
	 * @param	deltaColumn	column offset
	 * @param	deltaRow	row offset
	 * @return				same builder object
	 */
	public MoveCollectionBuilder addMove(final int deltaColumn, final int deltaRow) {
		final int column = source.getColumn(), row = source.getRow();

		added = 0;
		addMove(column + deltaColumn, row + deltaRow, true, false);

		return this;
	}

	/**
	 * Adds straight moves to all directions.
	 *
	 * @return				same builder object
	 */
	public MoveCollectionBuilder addStraightMoves() {
		added = 0;
		addLineMoves(0, -1);
		addLineMoves(+1, 0);
		addLineMoves(0, +1);
		addLineMoves(-1, 0);

		return this;
	}

	/**
	 * Adds diagonal moves to all directions.
	 *
	 * @return				same builder object
	 */
	public MoveCollectionBuilder addDiagonalMoves() {
		added = 0;
		addLineMoves(-1, -1);
		addLineMoves(-1, +1);
		addLineMoves(+1, -1);
		addLineMoves(+1, +1);

		return this;
	}

	/**
	 * Adds only normal move specified by offsets. Move won't be added if target square is occupied by any piece.
	 *
	 * @param	deltaColumn	column offset
	 * @param	deltaRow	row offset
	 * @return				same builder object
	 */
	public MoveCollectionBuilder addNormalMove(int deltaColumn, int deltaRow) {
		final int column = source.getColumn(), row = source.getRow();

		added = 0;
		addMove(column + deltaColumn, row + deltaRow, false, false);

		return this;
	}

	/**
	 * Adds only capture move specified by offsets. Move won't be added if square is empty.
	 *
	 * @param	deltaColumn	column offset
	 * @param	deltaRow	row offset
	 * @return				same builder object
	 */
	public MoveCollectionBuilder addCaptureMove(int deltaColumn, int deltaRow) {
		final int column = source.getColumn(), row = source.getRow();

		added = 0;
		addMove(column + deltaColumn, row + deltaRow, true, true);

		return this;
	}

	/**
	 * Returns true if last add command added any move.
	 *
	 * @return				true if added
	 */
	public boolean added() {
		return 0 != added;
	}

	/**
	 * Returns all the calculated moves at the moment.
	 *
	 * @return				collection of moves
	 */
	public Collection<Move> getCandidateMoves() {
		return moves;
	}

	/**
	 * Returns all the legal moves calculated at the moment. Such moves are verified not to place
	 * same color king in check.
	 *
	 * @return				collection of moves
	 */
	public Collection<Move> getLegalMoves() {
		final Collection<Move> legal = new HashSet<Move>(moves);
		final Iterator<Move> it = legal.iterator();

		while (it.hasNext()) {
			final Move move = it.next();
			final GameContext attempt = context.attempt(move);
			if (analyzer.isCheck(piece.getColor(), attempt)) {
				it.remove();
			}
		}

		return legal;
	}

	/**
	 * Adds all moves in line specified by offset. Stops at occupied squares. Squares occupied by enemy pieces results in capture moves.
	 *
	 * @param	deltaCol	horizontal direction by offset
	 * @param	deltaRow	vertical direction by offset
	 */
	private void addLineMoves(final int deltaCol, final int deltaRow) {
		int column = source.getColumn(), row = source.getRow();
		do {
			column += deltaCol;
			row += deltaRow;
		} while (addMove(column, row, true, false));
	}

	/**
	 * Adds move to specified positions. Moves are validated before adding.
	 *
	 * @param	column		target column
	 * @param	row			target row
	 * @param	canCapture	if occupied by enemy piece can be captured
	 * @param	captureOnly	if only capture can be made
	 * @return				true only if normal move was added
	 */
	private boolean addMove(int column, int row, boolean canCapture, boolean captureOnly) {
		if (column < 0 || column > 7 || row < 0 || row > 7) {
			return false;
		}

		final Board board = context.getBoard();
		final Square destination = board.getSquare(column, row);

		if (destination.isOccupied()) {
			final Piece occupier = destination.getPiece();
			if (canCapture && occupier.getColor() != piece.getColor()) {
				add(new CaptureMove(piece, source, destination, occupier));
			}
			return false;
		} else if (!captureOnly) {
			add(new NormalMove(piece, source, destination));
			return true;
		}

		return false;
	}

	/**
	 * Adds move to collection.
	 *
	 * @param	move
	 */
	private void add(final Move move) {
		moves.add(move);
		++added;
	}

}