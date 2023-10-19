package chess.domain.assertions;

import chess.domain.board.ReadonlyBoard;
import chess.domain.pieces.PieceType;

public class LastPlayerKingIsNotCheckedAssertion implements BoardAssertion {

  public boolean test(ReadonlyBoard board) {
    var nextPlayer = board.nextTurnPlayerColor();
    var lastPlayer = nextPlayer.opposite();
    var lastPlayersKing = board.getPieces(lastPlayer, PieceType.KING).findFirst();

    return lastPlayersKing.map(piece -> !new IsPositionThreatenedByColorAssertion(nextPlayer,
        piece.currentPosition()).test(board)).orElse(true);
  }
}
