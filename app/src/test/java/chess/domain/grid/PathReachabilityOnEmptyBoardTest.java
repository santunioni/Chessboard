package chess.domain.grid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.board.Board;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

public class PathReachabilityOnEmptyBoardTest {
  private final Board board = new Board();

  @ParameterizedTest
  @ArgumentsSource(PathReachabilityOnEmptyBoardTest.PathReachabilityCases.class)
  void shouldReachTargetAtExpectedDirection(Position from, Position target,
                                            Direction expectedDirection) {
    var path = from.pathTo(target).orElseThrow();
    assertEquals(expectedDirection, path.getDirection());
    assertTrue(path.isClearOn(this.board));
  }

  @Test
  void shouldNotReachD5FromA1GoingAnyDirection() {
    assertTrue(new Position("a1").pathTo(new Position("d5")).isEmpty());
  }

  static class PathReachabilityCases implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
      return Stream.of(
          Arguments.of(new Position("b2"), new Position("b5"), Direction.VERTICAL_UP),
          Arguments.of(new Position("b2"), new Position("d4"), Direction.DIAGONAL_UP_RIGHT),
          Arguments.of(new Position("b2"), new Position("e2"), Direction.HORIZONTAL_RIGHT),
          Arguments.of(new Position("b2"), new Position("c1"), Direction.DIAGONAL_DOWN_RIGHT),
          Arguments.of(new Position("b5"), new Position("b2"), Direction.VERTICAL_DOWN),
          Arguments.of(new Position("d4"), new Position("b2"), Direction.DIAGONAL_DOWN_LEFT),
          Arguments.of(new Position("e2"), new Position("b2"), Direction.HORIZONTAL_LEFT),
          Arguments.of(new Position("c1"), new Position("b2"), Direction.DIAGONAL_UP_LEFT)
      );
    }
  }
}
