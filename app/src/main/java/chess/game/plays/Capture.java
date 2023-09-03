package chess.game.plays;

import chess.game.board.BoardState;
import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.plays.validation.CapturePatternNotAllowedValidationError;
import chess.game.plays.validation.NoPieceAtPositionValidationError;
import chess.game.plays.validation.PieceAtPositionIsOfUnexpectedColorValidationError;
import chess.game.plays.validation.PlayValidationError;

import static chess.game.plays.PlayFunctions.getPieceFromBoard;

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
    private final Color color;

    public Capture(Color color, Position from, Position to) {
        this.from = from;
        this.to = to;
        this.color = color;
    }

    private Runnable validatePlay(BoardState boardState) throws PlayValidationError {
        var piece = getPieceFromBoard(color, from, boardState);

        if (!piece.couldCaptureEnemyAt(to)) {
            throw new CapturePatternNotAllowedValidationError(piece, from, to);
        }

        var targetPositionOccupation = boardState.getPieceAt(to);
        if (targetPositionOccupation.isEmpty()) {
            throw new NoPieceAtPositionValidationError(to);
        }

        var victim = targetPositionOccupation.get();
        if (!victim.isEnemyOf(piece)) {
            throw new PieceAtPositionIsOfUnexpectedColorValidationError(to, piece.getColor().opposite());
        }

        return () -> {
            boardState.removePieceFromSquare(from);
            boardState.placePiece(to, piece);
        };
    }

    public boolean isValid(BoardState boardState) {
        try {
            this.validatePlay(boardState);
            return true;
        } catch (PlayValidationError e) {
            return false;
        }
    }

    public void actUpon(BoardState boardState) throws PlayValidationError {
        var action = this.validatePlay(boardState);
        action.run();
    }

    public Color getPlayerColor() {
        return this.color;
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
