package chess.domain.pieces;

import chess.domain.board.Board;
import chess.domain.board.PieceColor;
import chess.domain.board.PieceFactory;
import chess.domain.grid.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueenThreatensTest {

    private final PieceFactory pieceFactory = new PieceFactory();
    private Board board;

    @BeforeEach
    void setUp() {
        this.board = new Board();
    }


    @Test
    public void shouldThreatenDiagonally() {
        var queen = this.pieceFactory.createQueen(PieceColor.BLACK);
        this.board.placePieceAt("d4", queen);

        assertTrue(queen.threatens(new Position("f6")));
    }

    @Test
    public void shouldThreatenVertically() {
        var queen = this.pieceFactory.createQueen(PieceColor.BLACK);
        this.board.placePieceAt("d4", queen);

        assertTrue(queen.threatens(new Position("d5")));
    }

    @Test
    public void shouldThreatenHorizontally() {
        var queen = this.pieceFactory.createQueen(PieceColor.BLACK);
        this.board.placePieceAt("d4", queen);

        assertTrue(queen.threatens(new Position("e4")));
    }

    @Test
    public void shouldNotThreatenIfEnemyIsBetween() {
        var queen = this.pieceFactory.createQueen(PieceColor.BLACK);
        this.board.placePieceAt("d4", queen);
        this.board.placePieceAt("f6", this.pieceFactory.createPawns(PieceColor.WHITE).get(0));

        assertFalse(queen.threatens(new Position("g7")));
    }

    @Test
    public void shouldNotThreatenIfAllyIsBetween() {
        var queen = this.pieceFactory.createQueen(PieceColor.BLACK);
        this.board.placePieceAt("d4", queen);
        this.board.placePieceAt("f6", this.pieceFactory.createPawns(PieceColor.BLACK).get(0));

        assertFalse(queen.threatens(new Position("g7")));
    }
}
