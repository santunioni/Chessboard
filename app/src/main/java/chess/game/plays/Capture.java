package chess.game.plays;

import chess.game.board.BoardState;
import chess.game.grid.Position;

/**
 * Represents a Capture play.
 * Attacks are differentiated from displacements because Pawns captures differently than they move.
 * The implementation is:
 * - Displacements are only valid to an empty position.
 * - Attacks are only valid when moving to a position occupied by the enemy.
 */
public class Capture implements Play {

    private final Position from;
    private final Position to;

    public Capture(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

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

        if (!victim.isEnemyOf(piece)) {
            throw new IlegalPlay(this, "Cant attack friends.");
        }

        boardState.removePieceFromSquare(from);
        boardState.placePiece(to, piece);
    }

    public PlayDTO toDTO() {
        return new PlayDTO() {
            public PlayName getName() {
                return PlayName.CAPTURE;
            }

            public Position getFrom() {
                return Capture.this.from;
            }

            public Position getTo() {
                return Capture.this.to;
            }
        };
    }
}
