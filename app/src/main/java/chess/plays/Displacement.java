package chess.plays;


import chess.board.BoardState;
import chess.board.position.Position;

/**
 * Represents a Displacement play.
 * Displacements are differentiated from attacks because Pawns attack differently than they move.
 * The implementation is:
 * - Displacements are only valid to an empty position.
 * - Attacks are only valid when moving to a position occupied by the enemy.
 * @param from
 * @param to
 */
public record Displacement(Position from, Position to) implements Play {

    public Displacement(String from, String to) {
        this(new Position(from), new Position(to));
    }

    public void actUpon(BoardState boardState) throws IlegalPlay {
        var piece = boardState.getPieceAt(from).orElseThrow(() -> new IlegalPlay(this, "No piece at " + from));

        if (!piece.canMoveTo(to)) {
            throw new IlegalPlay(this, "Cant move to " + to + " because it is not a valid move.");
        }

        var targetPositionOccupation = boardState.getPieceAt(to);
        if (targetPositionOccupation.isPresent()) {
            throw new IlegalPlay(this, "Cant move to " + to + " because it is ocuppied by " + targetPositionOccupation + ".");
        }

        boardState.removePieceFromSquare(from);
        boardState.placePiece(to, piece);
    }
}
