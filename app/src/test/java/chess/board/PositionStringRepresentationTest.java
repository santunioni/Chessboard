package chess.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionStringRepresentationTest {
    @Test
    void shouldOutputStringRepresentationAs_a1() {
        Position position = new Position(File.A, Rank.ONE);
        assertEquals("a1", position.toString());
    }
}