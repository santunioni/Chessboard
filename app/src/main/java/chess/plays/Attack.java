package chess.plays;

import chess.board.BoardState;
import chess.board.position.Position;

/**
 * Represents an Attack play.
 * Attacks are differentiated from displacements because Pawns attack differently than they move.
 * The implementation is:
 * - Displacements are only valid to an empty position.
 * - Attacks are only valid when moving to a position occupied by the enemy.
 *
 * @param from
 * @param to
 */
public record Attack(Position from, Position to) implements Play {
    @Override
    public void actUpon(BoardState boardState) throws IlegalPlay {
        var piece = boardState.getPieceAt(from).orElseThrow(() -> new IlegalPlay(this, "No piece at " + from));

        if (!piece.couldAttackIfOccupiedByEnemy(to)) {
            throw new IlegalPlay(this, "Cant attack " + to + " because piece doesnt threatens it.");
        }

        var targetPositionOccupation = boardState.getPieceAt(to);
        if (targetPositionOccupation.isEmpty()) {
            throw new IlegalPlay(this, "Cant attack " + to + " because it is not ocuppied.");
        }
        var victim = targetPositionOccupation.get();

        if (victim.getColor() == piece.getColor()) {
            throw new IlegalPlay(this, "Cant attack pieces of same color.");
        }

        boardState.removePieceFromSquare(from);
        boardState.placePiece(to, piece);
    }
}
