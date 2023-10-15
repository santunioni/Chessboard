package chess.game.plays;

import chess.game.board.Board;
import chess.game.board.BoardPlacement;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.Piece;
import chess.game.board.pieces.PieceType;
import chess.game.grid.Direction;
import chess.game.grid.Path;
import chess.game.grid.Position;
import chess.game.plays.validation.NoPieceAtPositionValidationError;
import chess.game.plays.validation.PieceAtPositionIsOfUnexpectedColorValidationError;
import chess.game.plays.validation.PlayValidationError;
import java.util.Set;


public class PlayFunctions {
  private PlayFunctions() {
  }

  public static Piece getPieceFromBoard(Color expectedColor, Position from, Board board)
      throws PlayValidationError {
    var pieceOptional = board.getPieceAt(from);
    if (pieceOptional.isEmpty()) {
      throw new NoPieceAtPositionValidationError(from);
    }

    var piece = pieceOptional.get();
    if (piece.getSpecification().color() != expectedColor) {
      throw new PieceAtPositionIsOfUnexpectedColorValidationError(from, expectedColor);
    }

    return piece;
  }

  public static Piece getPawnOrThrown(Board board, Color expectedColor, Position position)
      throws PlayValidationError {
    var piece = getPieceFromBoard(expectedColor, position, board);

    if (piece.getSpecification().pieceType() != PieceType.PAWN) {
      throw new PlayValidationError("Piece is not a pawn");
    }

    return piece;
  }

  public static boolean isPositionThreatenedBy(Board state, Position position, Color color) {
    for (var piece : state.getPiecesOf(color)) {
      if (piece.couldCaptureEnemyAt(position)) {
        return true;
      }
    }
    return false;
  }

  public static void collectDirectionalPlays(Piece piece, BoardPlacement board,
                                             Set<Direction> directions,
                                             PlayIteratorCallback callback) {
    collectDirectionalPlays(piece, board, directions, callback, 8);
  }

  public static void collectDirectionalPlays(Piece piece, BoardPlacement board,
                                             Set<Direction> directions,
                                             PlayIteratorCallback callback, int maxSteps) {
    for (var direction : directions) {
      for (var position : new Path(board.getMyPosition(), direction, maxSteps)) {
        callback.call(new Move(piece.getSpecification().color(), board.getMyPosition(), position));
        callback.call(
            new Capture(piece.getSpecification().color(), board.getMyPosition(), position));
        if (board.getPieceAt(position).isPresent()) {
          break;
        }
      }
    }
  }
}
