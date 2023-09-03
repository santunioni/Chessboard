package chess.game.plays;


import chess.game.board.BoardHistory;
import chess.game.board.BoardState;
import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.plays.validation.MovePatternNotAllowedValidationError;
import chess.game.plays.validation.PlayValidationError;
import chess.game.plays.validation.SquareAlreadyOccupiedValidationError;

import static chess.game.plays.PlayFunctions.getPieceFromBoard;

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
    private final Color color;

    public Move(Color color, Position from, Position to) {
        this.color = color;
        this.from = from;
        this.to = to;
    }


    public void actOn(BoardState boardState, BoardHistory boardHistory) throws PlayValidationError {
        var piece = getPieceFromBoard(color, from, boardState);

        if (!piece.couldMoveToIfEmpty(to)) {
            throw new MovePatternNotAllowedValidationError(piece, from, to);
        }

        var targetPositionOccupation = boardState.getPieceAt(to);
        if (targetPositionOccupation.isPresent()) {
            throw new SquareAlreadyOccupiedValidationError(to, targetPositionOccupation.get());
        }

        boardState.removePieceFromSquare(from);
        boardState.placePiece(to, piece);
        boardHistory.push(this);
    }

    public Color getPlayerColor() {
        return this.color;
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
