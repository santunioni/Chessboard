package chess.pieces;

import chess.board.Color;
import chess.board.InMemoryPositionBoardPlacement;
import chess.plays.Displacement;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnightDisplacementTest {

    @Test
    public void shouldReturnAllPossibleMovesInL() {
        var knight = new Knight(Color.WHITE);
        knight.placeInBoard(new InMemoryPositionBoardPlacement("d4"));

        var expectedValidMoves = Set.of(
                new Displacement("d4", "e6"),
                new Displacement("d4", "c6"),

                new Displacement("d4", "b5"),
                new Displacement("d4", "b3"),

                new Displacement("d4", "c2"),
                new Displacement("d4", "e2"),

                new Displacement("d4", "f3"),
                new Displacement("d4", "f5")
        );

        assertEquals(expectedValidMoves, knight.getValidMoves());
    }
}