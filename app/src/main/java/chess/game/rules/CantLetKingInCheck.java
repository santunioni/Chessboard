package chess.game.rules;

import static chess.game.plays.PlayFunctions.isPositionThreatenedBy;

import chess.game.board.Board;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.PieceSpecification;
import chess.game.board.pieces.PieceType;
import chess.game.grid.Position;
import chess.game.plays.Play;
import chess.game.rules.validation.CantLetOwnKingInCheckValidationError;
import chess.game.rules.validation.IlegalPlay;
import java.util.Optional;


public class CantLetKingInCheck {


  private CantLetKingInCheck() {
  }

  private static Optional<Position> findKing(Board state, Color color) {
    var possiblePositionsForKing = state.findPositionsWithPiece(new PieceSpecification(color,
        PieceType.KING));
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
      throw new CantLetOwnKingInCheckValidationError(play.getPlayerColor(),
          ownKingPosition);
    }
  }
}
