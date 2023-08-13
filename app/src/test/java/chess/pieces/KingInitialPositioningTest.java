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
        King king = new King(Color.WHITE);
        assertEquals(new Position(File.E, Rank.ONE), king.getPosition());
    }

    @Test
    void shouldPlaceBlackKingOnE8() {
        King king = new King(Color.BLACK);
        assertEquals(new Position(File.E, Rank.EIGHT), king.getPosition());
    }
}
