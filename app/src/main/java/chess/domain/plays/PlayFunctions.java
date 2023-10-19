package chess.domain.plays;

import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.Direction;
import chess.domain.grid.Path;
import chess.domain.pieces.Piece;
import java.util.Set;


public class PlayFunctions {
  private PlayFunctions() {
  }


  public static void collectDirectionalPlays(Piece piece, ReadonlyBoard board,
                                             Set<Direction> directions,
                                             PlayIteratorCallback callback) {
    collectDirectionalPlays(piece, board, directions, callback, 8);
  }

  public static void collectDirectionalPlays(Piece piece, ReadonlyBoard board,
                                             Set<Direction> directions,
                                             PlayIteratorCallback callback, int maxSteps) {
    for (var direction : directions) {
      for (var position : new Path(piece.currentPosition(), direction, maxSteps)) {
        callback.call(
            new Move(piece.type(), piece.color(), piece.currentPosition(), position));
        callback.call(
            new Capture(piece.type(), piece.color(), piece.currentPosition(), position));
        if (board.getPieceAt(position).isPresent()) {
          break;
        }
      }
    }
  }
}
