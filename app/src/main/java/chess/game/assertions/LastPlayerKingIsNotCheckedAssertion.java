package chess.game.assertions;

import chess.game.board.ReadonlyBoard;
import chess.game.board.pieces.PieceSpecification;
import chess.game.board.pieces.PieceType;

public class LastPlayerKingIsNotCheckedAssertion implements BoardAssertion {

  public boolean test(ReadonlyBoard board) {
    var nextPlayer = board.nextTurnPlayerColor();
    var lastPlayer = nextPlayer.opposite();

    return board.getSingleOf(new PieceSpecification(lastPlayer, PieceType.KING)).map(
        king -> !new IsPositionThreatenedByColorAssertion(nextPlayer,
            king.currentPosition()).test(board)).orElse(true);
  }
}
