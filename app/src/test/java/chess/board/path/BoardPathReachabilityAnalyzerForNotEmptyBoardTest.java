package chess.board.path;

import chess.board.BoardState;
import chess.board.position.Position;
import chess.pieces.Color;
import chess.pieces.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardPathReachabilityAnalyzerForNotEmptyBoardTest {
    private BoardState board;
    private BoardPathReachabilityAnalyzer boardPathReachabilityAnalyzer;

    @BeforeEach
    void setUp() {
        this.board = new BoardState();
        this.boardPathReachabilityAnalyzer = new BoardPathReachabilityAnalyzer(this.board);
    }

    @Test
    void shouldReachA5FromA1IfPieceInA5() {
        this.board.placePiece("a5", new Pawn(Color.WHITE));
        assertTrue(this.boardPathReachabilityAnalyzer.isReachableWalkingInOneOfDirections(
                new Position("a1"),
                BoardPathDirection.allDirections(),
                new Position("a5")
        ));
    }

    @Test
    void shouldNotReachA5FromA1IfPieceInA4() {
        this.board.placePiece("a4", new Pawn(Color.WHITE));
        assertFalse(this.boardPathReachabilityAnalyzer.isReachableWalkingInOneOfDirections(
                new Position("a1"),
                BoardPathDirection.allDirections(),
                new Position("a5")
        ));
    }

    @Test
    void shouldReachE5FromA1IfPieceInE5() {
        this.board.placePiece("e5", new Pawn(Color.WHITE));
        assertTrue(this.boardPathReachabilityAnalyzer.isReachableWalkingInOneOfDirections(
                new Position("a1"),
                BoardPathDirection.allDirections(),
                new Position("e5")
        ));
    }

    @Test
    void shouldNotReachE5FromA1IfPieceInD4() {
        this.board.placePiece("d4", new Pawn(Color.WHITE));
        assertFalse(this.boardPathReachabilityAnalyzer.isReachableWalkingInOneOfDirections(
                new Position("a1"),
                BoardPathDirection.allDirections(),
                new Position("e5")
        ));
    }
}
