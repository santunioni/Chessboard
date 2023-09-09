package chess.game.plays;

import chess.game.board.Board;
import chess.game.board.BoardPlacement;
import chess.game.grid.BoardPath;
import chess.game.grid.BoardPathDirection;
import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.pieces.Piece;
import chess.game.pieces.Type;
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
    if (piece.getColor() != expectedColor) {
      throw new PieceAtPositionIsOfUnexpectedColorValidationError(from, expectedColor);
    }

    return piece;
  }

  public static Piece getPawnOrThrown(Board board, Color expectedColor, Position position)
      throws PlayValidationError {
    var piece = getPieceFromBoard(expectedColor, position, board);

    if (piece.getType() != Type.PAWN) {
      throw new PlayValidationError("Piece is not a pawn");
    }

    return piece;
  }

  public static boolean isPositionThreatenedBy(Board state, Position position, Color color) {
    for (var piece : state.getPlayerPieces(color)) {
      if (piece.couldCaptureEnemyAt(position)) {
        return true;
      }
    }
    return false;
  }

  public static void collectDirectionalPlays(Piece piece, BoardPlacement board,
                                             Set<BoardPathDirection> directions,
                                             PlayIteratorCallback callback) {
    collectDirectionalPlays(piece, board, directions, callback, 8);
  }

  public static void collectDirectionalPlays(Piece piece, BoardPlacement board,
                                             Set<BoardPathDirection> directions,
                                             PlayIteratorCallback callback, int maxSteps) {
    for (var direction : directions) {
      for (var position : new BoardPath(board.getMyPosition(), direction, maxSteps)) {
        callback.call(new Move(piece.getColor(), board.getMyPosition(), position));
        callback.call(new Capture(piece.getColor(), board.getMyPosition(), position));
        if (board.getPieceAt(position).isPresent()) {
          break;
        }
      }
    }
  }
}
