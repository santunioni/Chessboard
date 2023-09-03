package chess.game.plays;

import chess.game.board.BoardHistory;
import chess.game.board.BoardState;
import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.pieces.King;
import chess.game.pieces.Rook;
import chess.game.plays.validation.CantCastleOnKingThatAlreadyMoved;
import chess.game.plays.validation.CantCastleOnRookThatAlreadyMoved;
import chess.game.plays.validation.CantCastleToInvalidPosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CastleValidationTest {
    private BoardState board;
    private BoardHistory history;

    @BeforeEach
    void setUp() {
        this.board = new BoardState();
        this.history = new BoardHistory();
    }

    @Test
    void shouldFailValidationWhenKingNotInPosition() {
        // Given
        board.placePiece("e2", new King(Color.WHITE));
        board.placePiece("h1", new Rook(Color.WHITE));

        // When
        var castle = new Castle(Color.WHITE, new Position("h1"));

        // Then
        assertThrows(CantCastleOnKingThatAlreadyMoved.class, () -> castle.actOn(board, history));
    }

    @Test
    void shouldFailValidationWhenRookNotInPosition() {
        // Given
        board.placePiece("e1", new King(Color.WHITE));
        board.placePiece("h2", new Rook(Color.WHITE));

        // When
        var castle = new Castle(Color.WHITE, new Position("h1"));

        // Then
        assertThrows(CantCastleOnRookThatAlreadyMoved.class, () -> castle.actOn(board, history));
    }

    @Test
    void shouldFailValidationWhenCastlingToPositionOtherThanRooks() {
        // Given
        board.placePiece("e1", new King(Color.WHITE));
        board.placePiece("h2", new Rook(Color.WHITE));

        // When
        var castle = new Castle(Color.WHITE, new Position("h2"));

        // Then
        assertThrows(CantCastleToInvalidPosition.class, () -> castle.actOn(board, history));
    }
}
