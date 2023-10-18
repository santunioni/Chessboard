package chess.domain.plays;

import chess.domain.assertions.BoardStateIsValidAssertion;
import chess.domain.board.Board;
import chess.domain.board.ReadonlyBoard;
import chess.domain.pieces.Color;
import java.util.Objects;

public abstract class Play {
  private final Color color;

  protected Play(Color color) {
    this.color = color;
  }

  public boolean isLegalOn(ReadonlyBoard board) {
    return this.isMyTurnToPlayNow(board)
        && this.canActOnCurrentState(board)
        && this.leavesBoardInLegalState(board);
  }

  protected abstract boolean canActOnCurrentState(ReadonlyBoard board);

  private boolean isMyTurnToPlayNow(ReadonlyBoard board) {
    return this.color == board.nextTurnPlayerColor();
  }

  private boolean leavesBoardInLegalState(ReadonlyBoard board) {
    var newState = board.simulate(this);
    return new BoardStateIsValidAssertion().test(newState);
  }

  public abstract void actOn(Board board);

  public Color getPlayerColor() {
    return this.color;
  }

  public abstract PlayDto toDto();

  @Override
  public boolean equals(Object obj) {
    Play that = (Play) obj;
    return obj != null
        && this.getClass().equals(obj.getClass())
        && this.toDto().equals(that.toDto())
        && this.getPlayerColor().equals(that.getPlayerColor());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.toDto(), this.getPlayerColor(), this.getClass());
  }
}
