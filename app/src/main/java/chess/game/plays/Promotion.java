package chess.game.plays;

import chess.game.board.Board;
import chess.game.board.pieces.Bishop;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.Knight;
import chess.game.board.pieces.Pawn;
import chess.game.board.pieces.PieceSpecification;
import chess.game.board.pieces.PieceType;
import chess.game.board.pieces.Queen;
import chess.game.board.pieces.Rook;
import chess.game.grid.Position;
import chess.game.plays.validation.NoPieceAtPositionValidationError;
import chess.game.plays.validation.PlayValidationError;

public record Promotion(Color color, Position at, PieceType to) implements Play {

  @Override
  public Runnable validateAndGetAction(Board board)
      throws PlayValidationError {
    var expectedRank = Pawn.getPromotionRankFor(color);

    if (!this.at.rank().equals(expectedRank)) {
      throw new PlayValidationError(this.color + " can only promote at " + expectedRank);
    }

    var oldPiece =
        board.getPieceAt(this.at, new PieceSpecification(this.color, PieceType.PAWN))
            .orElseThrow(() -> new NoPieceAtPositionValidationError(this.at));

    var newPiece = switch (this.to) {
      case ROOK -> new Rook(oldPiece.idInBoard(), this.color);
      case KNIGHT -> new Knight(oldPiece.idInBoard(), this.color);
      case BISHOP -> new Bishop(oldPiece.idInBoard(), this.color);
      case QUEEN -> new Queen(oldPiece.idInBoard(), this.color);
      default -> throw new PlayValidationError("Invalid Type at Promotion");
    };

    return () -> board.placePiece(this.at, newPiece);
  }

  public Color getPlayerColor() {
    return this.color;
  }

  public PlayDto toDto() {
    return new PlayDto() {
      public PlayName getName() {
        return null;
      }

      public Position getFrom() {
        return null;
      }

      public Position getTo() {
        return null;
      }
    };
  }
}
