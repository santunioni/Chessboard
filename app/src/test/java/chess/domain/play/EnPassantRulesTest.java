package chess.domain.play;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.PieceColor;
import chess.domain.board.PieceType;
import chess.domain.grid.Position;
import chess.domain.play.validation.PlayValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnPassantRulesTest {
    private Board board;

    @BeforeEach
    void setUp() {
        this.board = new BoardInitializer().placeAll().getBoard();
    }

    @Test
    void shouldCaptureBlackPieceThatRecentlyJumpedTwoSquares() throws PlayValidationError {
        // Given
        board.makePlay(
                new Move(PieceType.PAWN, PieceColor.WHITE, new Position("a2"), new Position("a4")));
        board.makePlay(
                new Move(PieceType.PAWN, PieceColor.BLACK, new Position("h7"), new Position("h5")));
        board.makePlay(
                new Move(PieceType.PAWN, PieceColor.WHITE, new Position("a4"), new Position("a5")));
        board.makePlay(
                new Move(PieceType.PAWN, PieceColor.BLACK, new Position("b7"), new Position("b5")));

        // When
        board.makePlay(new EnPassant(PieceColor.WHITE, new Position("a5"), new Position("b6")));

        // Then
        assertTrue(board.getPieceAt("b5").isEmpty());
        assertTrue(board.getPieceAt(new Position("b6"), PieceColor.WHITE, PieceType.PAWN).isPresent());
    }

    @Test
    void shouldFailIfBlackPieceHadNotRecentlyMoved() throws PlayValidationError {
        // Given
        board.makePlay(
                new Move(PieceType.PAWN, PieceColor.WHITE, new Position("a2"), new Position("a4")));
        board.makePlay(
                new Move(PieceType.PAWN, PieceColor.BLACK, new Position("b7"), new Position("b5")));
        board.makePlay(
                new Move(PieceType.PAWN, PieceColor.WHITE, new Position("a4"), new Position("a5")));
        board.makePlay(
                new Move(PieceType.PAWN, PieceColor.BLACK, new Position("h7"), new Position("h5")));

        // Then
        assertThrows(PlayValidationError.class,
                () -> board.makePlay(
                        new EnPassant(PieceColor.WHITE, new Position("a5"), new Position("b6"))));
    }


    @Test
    void shouldReturnAlgebraicNotation() {
        var enPassant = new EnPassant(PieceColor.WHITE, new Position("a5"), new Position("b6"));
        assertEquals("a5xb6 e.p.", enPassant.toDto().algebraicNotation());
    }
}
