package chess.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class PositionStringRepresentationTest {
    @Test
    void shouldOutputStringRepresentationAs_a1() {
        Position position = new Position(File.A, Rank.ONE);
        assertEquals("a1", position.toString());
    }
}

class PositionStringInstatiationTest {
    @Test
    void shouldInstantiateFromStringRepresentation_a1() {
        assertEquals(new Position(File.A, Rank.ONE), new Position("a1"));
    }

    @Test
    void shouldInstantiateFromStringRepresentation_g5() {
        assertEquals(new Position(File.G, Rank.FIVE), new Position("g5"));
    }

    @Test
    void shouldRejectInstantiationWithInvalidRank() {
        assertThrowsExactly(IllegalArgumentException.class, () -> new Position("a9"));
    }

    @Test
    void shouldRejectInstantiationWithInvalidFile() {
        assertThrowsExactly(IllegalArgumentException.class, () -> new Position("k7"));
    }
}
