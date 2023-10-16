package chess.game.assertions;

import chess.game.board.ReadonlyBoard;

public class BoardStateIsValidAssertion implements BoardAssertion {
  private final LastPlayerKingIsNotCheckedAssertion lastPlayerKingIsNotCheckedAssertion =
      new LastPlayerKingIsNotCheckedAssertion();

  private final PawnsOnFinalRankArePromotedAssertion pawnsOnFinalPositionArePromotedAssertion =
      new PawnsOnFinalRankArePromotedAssertion();

  public boolean test(ReadonlyBoard board) {
    return this.lastPlayerKingIsNotCheckedAssertion.test(board)
        && this.pawnsOnFinalPositionArePromotedAssertion.test(board);
  }
}
