package chess.domain.plays;

@FunctionalInterface
public interface PlayIteratorCallback {
  void call(Play play);
}
