package chess.game.plays;


import static chess.game.plays.PlayFunctions.getPieceFromBoard;

import chess.game.board.Board;
import chess.game.board.PlayHistory;
import chess.game.board.pieces.Color;
import chess.game.grid.Position;
import chess.game.plays.validation.MovePatternNotAllowedValidationError;
import chess.game.plays.validation.PlayValidationError;
import chess.game.plays.validation.SquareAlreadyOccupiedValidationError;

/**
 * Represents a Displacement play.
 * Displacements are differentiated from attacks because Pawns attack differently than they move.
 * The implementation is:
 * - Displacements are only valid to an empty position.
 * - Attacks are only valid when moving to a position occupied by the enemy.
 */
public record Move(Color color, Position from, Position to) implements Play {

  public Runnable validateAndGetAction(Board board, PlayHistory playHistory)
      throws PlayValidationError {
    var piece = getPieceFromBoard(color, from, board);

    if (!piece.couldMoveToIfEmpty(to)) {
      throw new MovePatternNotAllowedValidationError(piece, from, to);
    }

    var targetPositionOccupation = board.getPieceAt(to);
    if (targetPositionOccupation.isPresent()) {
      throw new SquareAlreadyOccupiedValidationError(to, targetPositionOccupation.get());
    }

    return () -> {
      board.removePieceFromSquare(from);
      board.placePiece(to, piece);
      playHistory.push(this);
    };
  }


  public Color getPlayerColor() {
    return this.color;
  }

  public PlayDto toDto() {
    return new PlayDto() {
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
