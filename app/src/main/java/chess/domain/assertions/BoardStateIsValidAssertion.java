package chess.domain.assertions;

import chess.domain.board.ReadonlyBoard;

public class BoardStateIsValidAssertion implements BoardAssertion {
  private final BoardAssertion assertion = new LastPlayerKingIsNotCheckedAssertion()
      .and(new PawnsOnFinalRankArePromotedAssertion());

  public boolean test(ReadonlyBoard board) {
    return this.assertion.test(board);
  }
}
