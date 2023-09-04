package chess.game.grid;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PositionToStringTest {
  @Test
  void shouldOutputStringRepresentationAs_a1() {
    Position position = new Position(File.A, Rank.ONE);
    assertEquals("a1", position.toString());
  }
}
