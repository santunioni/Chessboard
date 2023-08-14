package chess.pieces;

import chess.board.Color;
import chess.plays.Displacement;
import chess.board.position.Position;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnightDisplacementTest {

    @Test
    public void shouldReturnAllPossibleMovesInL() {
        var knight = new Knight(Color.WHITE, new Position("d4"));

        var expectedValidMoves = Set.of(
                new Displacement(new Position("d4"), new Position("e6")),
                new Displacement(new Position("d4"), new Position("c6")),

                new Displacement(new Position("d4"), new Position("b5")),
                new Displacement(new Position("d4"), new Position("b3")),

                new Displacement(new Position("d4"), new Position("c2")),
                new Displacement(new Position("d4"), new Position("e2")),

                new Displacement(new Position("d4"), new Position("f3")),
                new Displacement(new Position("d4"), new Position("f5"))
        );

        assertEquals(expectedValidMoves, knight.getValidMoves());
    }
}