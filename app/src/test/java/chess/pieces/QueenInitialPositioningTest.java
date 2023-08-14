package chess.pieces;

import chess.board.Color;
import chess.board.position.File;
import chess.board.position.Position;
import chess.board.position.Rank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueenInitialPositioningTest {
    @Test
    void shouldPlaceWhiteQueenOnD1() {
        var queen = new Queen(Color.WHITE);
        assertEquals(new Position(File.D, Rank.ONE), queen.position);
    }

    @Test
    void shouldPlaceBlackQueenOnD8() {
        var queen = new Queen(Color.BLACK);
        assertEquals(new Position(File.D, Rank.EIGHT), queen.position);
    }
}
