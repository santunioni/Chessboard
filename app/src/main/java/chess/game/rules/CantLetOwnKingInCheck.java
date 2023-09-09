package chess.game.rules;

import static chess.game.plays.PlayFunctions.isPositionThreatenedBy;

import chess.game.board.Board;
import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.pieces.PieceProperties;
import chess.game.pieces.Type;
import chess.game.plays.Play;
import chess.game.rules.validation.IlegalPlay;
import java.util.Optional;


public class CantLetOwnKingInCheck {


  private CantLetOwnKingInCheck() {
  }

  private static Optional<Position> findKing(Board state, Color color) {
    var possiblePositionsForKing = state.findPositionsWithPiece(new PieceProperties() {
      public Color getColor() {
        return color;
      }

      public Type getType() {
        return Type.KING;
      }
    });
    if (possiblePositionsForKing.size() != 1) {
      return Optional.empty();
    }
    Position ownKingPosition = possiblePositionsForKing.get(0);
    return Optional.of(ownKingPosition);
  }

  public static void validateStateAfterPlay(
      Board state,
      Play play
  ) throws IlegalPlay {
    var possiblePositionsForKing = findKing(state, play.getPlayerColor());
    if (possiblePositionsForKing.isEmpty()) {
      return;
    }
    Position ownKingPosition = possiblePositionsForKing.get();

    if (isPositionThreatenedBy(state, ownKingPosition, play.getPlayerColor().opposite())) {
      throw new chess.game.rules.validation.CantLetOwnKingInCheck(play.getPlayerColor(),
          ownKingPosition);
    }
  }
}
