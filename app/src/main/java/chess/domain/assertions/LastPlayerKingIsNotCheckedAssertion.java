package chess.domain.assertions;

import chess.domain.board.PieceType;
import chess.domain.board.ReadonlyBoard;

public class LastPlayerKingIsNotCheckedAssertion implements BoardAssertion {

  public boolean test(ReadonlyBoard board) {
    var nextPlayer = board.nextTurnPlayerColor();
    var lastPlayer = nextPlayer.opposite();
    var lastPlayersKing = board.getPieces(lastPlayer, PieceType.KING).findFirst();

    return lastPlayersKing.map(king -> !new ColorThreatensPositionAssertion(nextPlayer,
        king.currentPosition()).test(board)).orElse(true);
  }
}
