package chess.game.plays;

@FunctionalInterface
public interface PlayIteratorCallback {
  void call(Play play);
}
