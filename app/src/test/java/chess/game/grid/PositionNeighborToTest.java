package chess.game.grid;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

class PositionNeighborCases implements ArgumentsProvider {
  public static Set<Position> neighborsToD4 = Set.of(
      new Position("c3"),
      new Position("d3"),
      new Position("e3"),
      new Position("e4"),
      new Position("c4"),
      new Position("c5"),
      new Position("d5"),
      new Position("e5")
  );

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
    return neighborsToD4.stream().map(neighbor -> Arguments.of(new Position("d4"), neighbor));
  }
}

class PositionNotNeighborCases implements ArgumentsProvider {


  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
    var notNeighborsToD4 = new HashSet<Position>();
    for (var position : Position.values()) {
      if (!PositionNeighborCases.neighborsToD4.contains(position)) {
        notNeighborsToD4.add(position);
      }
    }
    return notNeighborsToD4.stream().map(neighbor -> Arguments.of(new Position("d4"), neighbor));
  }
}

public class PositionNeighborToTest {
  @ParameterizedTest
  @ArgumentsSource(PositionNeighborCases.class)
  void shouldClassifyAsNeighbor(Position from, Position to) {
    assertTrue(from.isNeighborTo(to));
  }

  @ParameterizedTest
  @ArgumentsSource(PositionNotNeighborCases.class)
  void shouldClassifyAsNotNeighbor(Position from, Position to) {
    assertFalse(from.isNeighborTo(to));
  }
}
