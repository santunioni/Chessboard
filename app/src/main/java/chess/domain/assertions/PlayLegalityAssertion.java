package chess.domain.assertions;

import chess.domain.board.ReadonlyBoard;
import chess.domain.plays.Play;

public class PlayLegalityAssertion implements BoardAssertion {
  private final Play play;
  private final BoardAssertion boardStateIsValidAssertion = new BoardStateIsValidAssertion();

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
    ReadonlyBoard simulatedState = board.simulate(this.play);
    return this.boardStateIsValidAssertion.test(simulatedState);
  }
}
