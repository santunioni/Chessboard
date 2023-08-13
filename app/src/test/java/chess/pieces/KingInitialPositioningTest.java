package chess.pieces;

import chess.board.File;
import chess.board.PlayerSide;
import chess.board.Position;
import chess.board.Rank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KingInitialPositioningTest {
    @Test
    void shouldPlaceWhiteKingOnE1() {
        King king = new King(PlayerSide.WHITE);
        assertEquals(new Position(File.E, Rank.ONE), king.getPosition());
    }

    @Test
    void shouldPlaceBlackKingOnE8() {
        King king = new King(PlayerSide.BLACK);
        assertEquals(new Position(File.E, Rank.EIGHT), king.getPosition());
    }
}
