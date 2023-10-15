package chess.game.plays;

import chess.game.board.ReadonlyBoard;
import chess.game.board.pieces.Piece;
import chess.game.grid.Direction;
import chess.game.grid.Path;
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
            new Move(piece.getSpecification().color(), piece.currentPosition(), position));
        callback.call(
            new Capture(piece.getSpecification().color(), piece.currentPosition(), position));
        if (board.getPieceAt(position).isPresent()) {
          break;
        }
      }
    }
  }
}
