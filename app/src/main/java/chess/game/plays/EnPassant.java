package chess.game.plays;

import chess.game.board.Board;
import chess.game.board.PlayHistory;
import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.pieces.Pawn;
import chess.game.pieces.Piece;
import chess.game.pieces.Type;
import chess.game.plays.validation.CantEnPassantOnInvalidRank;
import chess.game.plays.validation.CantEnPassantPawnThatDidntJumpLastRound;
import chess.game.plays.validation.CapturePatternNotAllowedValidationError;
import chess.game.plays.validation.NoPieceAtPositionValidationError;
import chess.game.plays.validation.PlayValidationError;

public record EnPassant(Color color, Position from, Position to) implements Play {

  private Piece getPawn(Board board, Color expectedColor, Position position)
      throws PlayValidationError {
    var piece = board.getPieceAt(position)
        .orElseThrow(() -> new NoPieceAtPositionValidationError(position));

    if (piece.getType() != Type.PAWN) {
      throw new PlayValidationError("Only pawns can participate on En Passant");
    }

    if (piece.getColor() != expectedColor) {
      throw new PlayValidationError("Unexpected Color");
    }

    return piece;
  }

  private boolean hasVictimJumpedTwoSquaresLastRound(Board board,
                                                     PlayHistory playHistory,
                                                     Position victimPosition)
      throws PlayValidationError {
    var victim = this.getPawn(board, this.color.opposite(), victimPosition);
    var lastPlay = playHistory.getLastPlay()
        .orElseThrow(() -> new PlayValidationError("En Passant cant be the first play."));
    var pawnJumpingTwoSquares = new Move(
        this.color.opposite(),
        new Position(victimPosition.file(), Pawn.getStartRank(victim.getColor())),
        victimPosition
    );
    return lastPlay.equals(pawnJumpingTwoSquares);
  }

  public Runnable validateAndGetAction(Board board, PlayHistory playHistory)
      throws PlayValidationError {
    if (Pawn.getEnPassantRank(color) != from.rank()) {
      throw new CantEnPassantOnInvalidRank(color);
    }

    var attacker = this.getPawn(board, this.color, this.from);

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
