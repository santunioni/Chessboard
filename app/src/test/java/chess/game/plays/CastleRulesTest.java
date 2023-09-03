package chess.game.plays;

import chess.game.board.BoardHistory;
import chess.game.board.BoardState;
import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.pieces.King;
import chess.game.pieces.Rook;
import chess.game.plays.validation.CantCastleOnKingThatAlreadyMoved;
import chess.game.plays.validation.CantCastleOnRookThatAlreadyMoved;
import chess.game.plays.validation.CantCastleWhileInCheck;
import chess.game.plays.validation.PlayValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CastleRulesTest {
    private BoardState board;
    private BoardHistory history;

    @BeforeEach
    void setUp() {
        this.board = new BoardState();
        this.history = new BoardHistory();
        board.placePiece("e1", new King(Color.WHITE));
        board.placePiece("a1", new Rook(Color.WHITE));
        board.placePiece("h1", new Rook(Color.WHITE));
    }

    @Test
    void shouldFailWhenKingAlreadyMoved() throws PlayValidationError {
        // Given
        new Move(Color.WHITE, new Position("e1"), new Position("e2")).actOn(board, history);
        new Move(Color.WHITE, new Position("e2"), new Position("e1")).actOn(board, history);

        // When
        var castle = new Castle(Color.WHITE, new Position("h1"));

        // Then
        assertThrows(CantCastleOnKingThatAlreadyMoved.class, () -> castle.actOn(board, history));
    }

    @Test
    void shouldFailWhenRookAlreadyMoved() throws PlayValidationError {
        // Given
        new Move(Color.WHITE, new Position("h1"), new Position("h2")).actOn(board, history);
        new Move(Color.WHITE, new Position("h2"), new Position("h1")).actOn(board, history);

        // When
        var castle = new Castle(Color.WHITE, new Position("h1"));

        // Then
        assertThrows(CantCastleOnRookThatAlreadyMoved.class, () -> castle.actOn(board, history));
    }

    @Test
    void shouldFailIfKingIsInCheck() {
        // Given
        board.placePiece("e2", new Rook(Color.BLACK));

        // When
        var castle = new Castle(Color.WHITE, new Position("h1"));

        // Then
        assertThrows(CantCastleWhileInCheck.class, () -> castle.actOn(board, history));
    }

}
