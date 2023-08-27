package chess.game.plays;


import chess.game.board.BoardState;
import chess.game.grid.Position;

/**
 * Represents a Displacement play.
 * Displacements are differentiated from attacks because Pawns attack differently than they move.
 * The implementation is:
 * - Displacements are only valid to an empty position.
 * - Attacks are only valid when moving to a position occupied by the enemy.
 */
public class Move implements Play {

    private final Position from;
    private final Position to;

    public Move(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    public void actUpon(BoardState boardState) throws IlegalPlay {
        var piece = boardState.getPieceAt(from).orElseThrow(() -> new IlegalPlay(this, "No piece at " + from));

        if (!piece.couldMoveToIfEmpty(to)) {
            throw new IlegalPlay(this, "Cant move to " + to + " because it is not a valid move.");
        }

        var targetPositionOccupation = boardState.getPieceAt(to);
        if (targetPositionOccupation.isPresent()) {
            throw new IlegalPlay(this, "Cant move to " + to + " because it is ocuppied by " + targetPositionOccupation + ".");
        }

        boardState.removePieceFromSquare(from);
        boardState.placePiece(to, piece);
    }

    public PlayDTO toDTO() {
        return new PlayDTO() {
            public PlayName getName() {
                return PlayName.MOVE;
            }

            public Position getFrom() {
                return Move.this.from;
            }

            public Position getTo() {
                return Move.this.to;
            }
        };
    }
}
