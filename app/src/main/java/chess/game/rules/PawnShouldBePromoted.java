package chess.game.rules;

import static chess.game.plays.Promotion.getPromotionRankForColor;

import chess.game.board.Board;
import chess.game.grid.File;
import chess.game.grid.Position;
import chess.game.pieces.Type;
import chess.game.plays.Play;
import chess.game.plays.validation.PawnShouldBePromotedValidationError;

public class PawnShouldBePromoted {

  private PawnShouldBePromoted() {
  }

  public static void validateStateAfterPlay(Board board, Play play)
      throws PawnShouldBePromotedValidationError {
    for (var file : File.values()) {
      var position = new Position(file, getPromotionRankForColor(play.getPlayerColor()));
      var pieceOptional = board.getPieceAt(position);
      if (pieceOptional.isPresent()) {
        var piece = pieceOptional.get();
        if (piece.getType() == Type.PAWN) {
          throw new PawnShouldBePromotedValidationError(play.getPlayerColor());
        }
        ;
      }
    }
  }
}
