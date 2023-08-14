package chess.pieces;

import chess.board.Color;
import chess.board.position.Movement;
import chess.board.position.Position;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnightValidMovesTest {

    @Test
    public void shouldReturnAllPossibleMovesInL() {
        var knight = new Knight(Color.WHITE, new Position("d4"));

        var expectedValidMoves = new HashSet<>(Set.of(
                new Movement(new Position("d4"), new Position("e6")),
                new Movement(new Position("d4"), new Position("c6")),

                new Movement(new Position("d4"), new Position("b5")),
                new Movement(new Position("d4"), new Position("b3")),

                new Movement(new Position("d4"), new Position("c2")),
                new Movement(new Position("d4"), new Position("e2")),

                new Movement(new Position("d4"), new Position("f3")),
                new Movement(new Position("d4"), new Position("f5"))
        ));

        assertEquals(expectedValidMoves, knight.getValidMoves());
    }
}