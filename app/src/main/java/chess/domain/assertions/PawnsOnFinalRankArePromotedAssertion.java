package chess.domain.assertions;


import chess.domain.board.PieceColor;
import chess.domain.board.PieceType;
import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.File;
import chess.domain.grid.Position;
import chess.domain.grid.Rank;

public class PawnsOnFinalRankArePromotedAssertion implements BoardAssertion {

  public boolean test(ReadonlyBoard board) {
    for (var color : PieceColor.values()) {
      var rank = color == PieceColor.WHITE ? Rank.EIGHT : Rank.ONE;
      for (var file : File.values()) {
        if (board.getPieceAt(new Position(file, rank), color, PieceType.PAWN).isPresent()) {
          return false;
        }
      }
    }
    return true;
  }
}
