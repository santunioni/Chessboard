package chess.game.assertions;


import chess.game.board.ReadonlyBoard;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.Pawn;
import chess.game.board.pieces.PieceSpecification;
import chess.game.board.pieces.PieceType;
import chess.game.grid.File;
import chess.game.grid.Position;

public class PawnsOnFinalRankArePromotedAssertion implements BoardAssertion {

  public boolean test(ReadonlyBoard board) {
    for (var color : Color.values()) {
      for (var file : File.values()) {
        var position = new Position(file, Pawn.getPromotionRankFor(color));
        var pawnAtPromotionLocation = board.getPieceAt(position)
            .map(p -> p.getSpecification().equals(new PieceSpecification(color, PieceType.PAWN)))
            .orElse(false);
        if (pawnAtPromotionLocation) {
          return false;
        }
      }
    }
    return true;
  }
}
