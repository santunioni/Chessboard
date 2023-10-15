package chess.game.plays;

import static chess.game.plays.PlayFunctions.getPieceFromBoard;

import chess.game.board.Board;
import chess.game.board.PlayHistory;
import chess.game.board.pieces.Color;
import chess.game.grid.Position;
import chess.game.plays.validation.CapturePatternNotAllowedValidationError;
import chess.game.plays.validation.NoPieceAtPositionValidationError;
import chess.game.plays.validation.PieceAtPositionIsOfUnexpectedColorValidationError;
import chess.game.plays.validation.PlayValidationError;

/**
 * Represents a Capture play.
 * Attacks are differentiated from displacements because Pawns captures differently than they move.
 * The implementation is:
 * - Displacements are only valid to an empty position.
 * - Attacks are only valid when moving to a position occupied by the enemy.
 */
public record Capture(Color color, Position from, Position to) implements Play {


  public Runnable validateAndGetAction(Board board, PlayHistory playHistory)
      throws PlayValidationError {
    var piece = getPieceFromBoard(color, from, board);

    if (!piece.couldCaptureEnemyAt(to)) {
      throw new CapturePatternNotAllowedValidationError(piece, from, to);
    }

    var targetPositionOccupation = board.getPieceAt(to);
    if (targetPositionOccupation.isEmpty()) {
      throw new NoPieceAtPositionValidationError(to);
    }

    var victim = targetPositionOccupation.get();
    if (!victim.isEnemyOf(piece)) {
      throw new PieceAtPositionIsOfUnexpectedColorValidationError(to,
          piece.getSpecification().color().opposite());
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
