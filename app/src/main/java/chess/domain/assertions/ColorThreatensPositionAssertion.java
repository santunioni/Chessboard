package chess.domain.assertions;

import chess.domain.board.Piece;
import chess.domain.board.PieceColor;
import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.Position;
import java.util.Iterator;

public class ColorThreatensPositionAssertion implements BoardAssertion {
  private final Position position;
  private final PieceColor color;

  public ColorThreatensPositionAssertion(PieceColor color, Position position) {
    this.color = color;
    this.position = position;
  }

  public boolean test(ReadonlyBoard board) {
    for (Iterator<Piece> it = board.getPieces(color).iterator(); it.hasNext(); ) {
      var piece = it.next();
      if (piece.threatens(position)) {
        return true;
      }
    }
    return false;
  }
}
