package chess.pieces;

import chess.board.BoardState;
import chess.plays.Displacement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnightDisplacementTest {

    private BoardState board;

    @BeforeEach
    void setUp() {
        this.board = new BoardState();
    }

    @Test
    public void shouldReturnAllPossibleMovesInL() {
        var knight = new Knight(Color.WHITE);
        this.board.placePiece("d4", knight);

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