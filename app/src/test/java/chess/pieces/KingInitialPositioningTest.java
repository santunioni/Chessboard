package chess.pieces;

import chess.board.Color;
import chess.board.position.File;
import chess.board.position.Position;
import chess.board.position.Rank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KingInitialPositioningTest {
    @Test
    void shouldPlaceWhiteKingOnE1() {
        assertEquals(new Position(File.E, Rank.ONE), King.getInitialPosition(Color.WHITE));
    }

    @Test
    void shouldPlaceBlackKingOnE8() {
        assertEquals(new Position(File.E, Rank.EIGHT), King.getInitialPosition(Color.BLACK));
    }
}
