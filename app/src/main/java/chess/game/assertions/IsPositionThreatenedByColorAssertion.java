package chess.game.assertions;

import chess.game.board.ReadonlyBoard;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.Piece;
import chess.game.grid.Position;
import java.util.Iterator;
import java.util.Map;

public class IsPositionThreatenedByColorAssertion implements BoardAssertion {
  private final Position position;
  private final Color color;

  public IsPositionThreatenedByColorAssertion(Color color, Position position) {
    this.color = color;
    this.position = position;
  }

  public boolean test(ReadonlyBoard board) {
    for (Iterator<Map.Entry<Position, Piece>> it = board.getPieces(color).iterator();
         it.hasNext(); ) {
      var piece = it.next().getValue();
      if (piece.threatens(position)) {
        return true;
      }
    }
    return false;
  }
}
