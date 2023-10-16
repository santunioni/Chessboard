package chess.game.assertions;

import chess.game.board.ReadonlyBoard;
import chess.game.board.pieces.PieceSpecification;
import chess.game.board.pieces.PieceType;

public class LastPlayerKingIsNotCheckedAssertion implements BoardAssertion {

  public boolean test(ReadonlyBoard board) {
    var nextPlayer = board.nextTurnPlayerColor();
    var lastPlayer = nextPlayer.opposite();
    var lastPlayersKing = board.getSingleOf(new PieceSpecification(lastPlayer, PieceType.KING));

    return lastPlayersKing.map(piece -> !new IsPositionThreatenedByColorAssertion(nextPlayer,
        piece.currentPosition()).test(board)).orElse(true);
  }
}
