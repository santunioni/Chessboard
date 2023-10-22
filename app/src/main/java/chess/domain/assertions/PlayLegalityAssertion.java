package chess.domain.assertions;

import chess.domain.board.ReadonlyBoard;
import chess.domain.plays.Play;

public class PlayLegalityAssertion implements BoardAssertion {
  private final Play play;

  public PlayLegalityAssertion(Play play) {
    this.play = play;
  }

  public boolean test(ReadonlyBoard board) {
    return this.isMyTurnToPlayNow(board)
        && this.play.canActOnCurrentState(board)
        && this.leavesBoardInLegalState(board);
  }

  private boolean isMyTurnToPlayNow(ReadonlyBoard board) {
    return this.play.getPlayerColor() == board.nextTurnPlayerColor();
  }

  private boolean leavesBoardInLegalState(ReadonlyBoard board) {
    var newState = board.simulate(this.play);
    return new BoardStateIsValidAssertion().test(newState);
  }
}
