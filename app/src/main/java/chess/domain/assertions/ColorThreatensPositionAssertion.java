package chess.domain.assertions;

import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.Position;
import chess.domain.pieces.Color;
import chess.domain.pieces.Piece;
import java.util.Iterator;

public class ColorThreatensPositionAssertion implements BoardAssertion {
  private final Position position;
  private final Color color;

  public ColorThreatensPositionAssertion(Color color, Position position) {
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
