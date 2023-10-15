package chess.game.rules;

import chess.game.board.Board;
import chess.game.board.pieces.Piece;
import chess.game.board.pieces.PieceSpecification;
import chess.game.board.pieces.PieceType;
import chess.game.plays.Play;
import chess.game.rules.validation.CantLetOwnKingInCheckValidationError;
import chess.game.rules.validation.IlegalPlay;


public class CantLetKingInCheck {


  private CantLetKingInCheck() {
  }

  public static void validateStateAfterPlay(Board board, Play play) throws IlegalPlay {
    var ownKingPosition =
        board.getSingleOf(new PieceSpecification(play.getPlayerColor(), PieceType.KING))
            .map(Piece::currentPosition);

    if (ownKingPosition.isEmpty()) {
      return;
    }

    if (board.isPositionThreatenedBy(ownKingPosition.get(), play.getPlayerColor().opposite())) {
      throw new CantLetOwnKingInCheckValidationError(play.getPlayerColor(), ownKingPosition.get());
    }
  }
}
