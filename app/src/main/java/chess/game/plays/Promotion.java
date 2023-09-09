package chess.game.plays;

import static chess.game.plays.PlayFunctions.getPawnOrThrown;

import chess.game.board.Board;
import chess.game.board.PlayHistory;
import chess.game.grid.Position;
import chess.game.grid.Rank;
import chess.game.pieces.Bishop;
import chess.game.pieces.Color;
import chess.game.pieces.Knight;
import chess.game.pieces.Queen;
import chess.game.pieces.Rook;
import chess.game.pieces.Type;
import chess.game.plays.validation.PlayValidationError;

public record Promotion(Color color, Position at, Type to) implements Play {

  public static Rank getPromotionRankForColor(Color color) {
    return color == Color.WHITE ? Rank.EIGHT : Rank.ONE;
  }

  @Override
  public Runnable validateAndGetAction(Board board, PlayHistory playHistory)
      throws PlayValidationError {
    var expectedRank = getPromotionRankForColor(color);

    if (!this.at.rank().equals(expectedRank)) {
      throw new PlayValidationError(this.color + " can only promote at " + expectedRank);
    }

    getPawnOrThrown(board, this.color, this.at);

    var newPiece = switch (this.to) {
      case ROOK -> new Rook(this.color);
      case KNIGHT -> new Knight(this.color);
      case BISHOP -> new Bishop(this.color);
      case QUEEN -> new Queen(this.color);
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
