package chess.game.plays;

import static chess.game.plays.PlayFunctions.getPawnOrThrown;

import chess.game.board.Board;
import chess.game.board.PlayHistory;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.Pawn;
import chess.game.grid.Position;
import chess.game.plays.validation.CantEnPassantOnInvalidRank;
import chess.game.plays.validation.CantEnPassantPawnThatDidntJumpLastRound;
import chess.game.plays.validation.CapturePatternNotAllowedValidationError;
import chess.game.plays.validation.PlayValidationError;

public record EnPassant(Color color, Position from, Position to) implements Play {

  private boolean hasVictimJumpedTwoSquaresLastRound(Board board,
                                                     PlayHistory playHistory,
                                                     Position victimPosition)
      throws PlayValidationError {
    var victim = getPawnOrThrown(board, this.color.opposite(), victimPosition);
    var lastPlay = playHistory.getLastPlay()
        .orElseThrow(() -> new PlayValidationError("En Passant cant be the first play."));
    var pawnJumpingTwoSquares = new Move(
        this.color.opposite(),
        new Position(victimPosition.file(), Pawn.getStartRank(victim.getSpecification().color())),
        victimPosition
    );
    return lastPlay.equals(pawnJumpingTwoSquares);
  }

  public Runnable validateAndGetAction(Board board, PlayHistory playHistory)
      throws PlayValidationError {
    if (Pawn.getEnPassantRank(color) != from.rank()) {
      throw new CantEnPassantOnInvalidRank(color);
    }

    var attacker = getPawnOrThrown(board, this.color, this.from);

    if (!attacker.couldCaptureEnemyAt(to)) {
      throw new CapturePatternNotAllowedValidationError(attacker, from, to);
    }

    var victimPosition = new Position(this.to.file(), Pawn.getEnPassantRank(color));
    if (!this.hasVictimJumpedTwoSquaresLastRound(board, playHistory, victimPosition)) {
      throw new CantEnPassantPawnThatDidntJumpLastRound();
    }

    return () -> {
      board.removePieceFromSquare(victimPosition);
      board.removePieceFromSquare(this.from);
      board.placePiece(this.to, attacker);
      playHistory.push(this);
    };
  }

  public Color getPlayerColor() {
    return this.color;
  }

  public PlayDto toDto() {
    return new PlayDto() {
      public PlayName getName() {
        return PlayName.EN_PASSANT;
      }

      public Position getFrom() {
        return EnPassant.this.from;
      }

      public Position getTo() {
        return EnPassant.this.to;
      }
    };
  }
}
