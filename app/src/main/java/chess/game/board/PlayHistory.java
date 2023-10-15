package chess.game.board;

import chess.game.board.pieces.Color;
import chess.game.plays.Play;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;

public class PlayHistory implements Iterable<Play> {

  private final List<Play> stack = new ArrayList<>();

  public PlayHistory() {

  }

  public void push(Play play) {
    this.stack.add(play);
  }

  public PlayHistory copy() {
    var copy = new PlayHistory();
    copy.stack.addAll(this.stack);
    return copy;
  }

  public Optional<Play> getLastPlay() {
    if (!this.stack.isEmpty()) {
      return Optional.of(this.stack.get(this.stack.size() - 1));
    }
    return Optional.empty();
  }

  public Color nextTurnPlayerColor() {
    return this.getLastPlay().map(play -> play.getPlayerColor().opposite()).orElse(Color.WHITE);
  }

  @Nonnull
  public Iterator<Play> iterator() {
    return this.stack.iterator();
  }
}
