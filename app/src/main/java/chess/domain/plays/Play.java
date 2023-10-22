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


  public abstract boolean canActOnCurrentState(ReadonlyBoard board);


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
