package chess.domain.assertions;

import chess.domain.board.ReadonlyBoard;

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
