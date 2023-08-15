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
        assertEquals(new Position(File.D, Rank.ONE), Queen.getInitialPosition(Color.WHITE));
    }

    @Test
    void shouldPlaceBlackQueenOnD8() {
        assertEquals(new Position(File.D, Rank.EIGHT), Queen.getInitialPosition(Color.BLACK));
    }
}
