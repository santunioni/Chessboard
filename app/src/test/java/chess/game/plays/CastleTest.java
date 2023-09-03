package chess.game.plays;

import chess.game.board.BoardHistory;
import chess.game.board.BoardState;
import chess.game.pieces.Color;
import chess.game.pieces.King;
import chess.game.pieces.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CastleTest {
    private BoardState board;
    private BoardHistory history;

    @BeforeEach
    void setUp() {
        this.board = new BoardState();
        this.history = new BoardHistory();
    }

    @Test
    void shouldFailWhenKingAlreadyMoved() {
        board.placePiece("e1", new King(Color.WHITE));
        board.placePiece("h1", new Rook(Color.WHITE));
    }


}
