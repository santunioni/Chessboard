package chess.game.rules;

import chess.game.board.BoardHistory;
import chess.game.board.BoardState;
import chess.game.grid.Position;
import chess.game.pieces.Bishop;
import chess.game.pieces.Color;
import chess.game.pieces.King;
import chess.game.pieces.Rook;
import chess.game.plays.Move;
import chess.game.rules.validation.CantLetOwnKingInCheck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayValidatorAgainstAllChessRulesTest {

    private BoardState state;
    private BoardHistory history;

    @BeforeEach
    void setUp() {
        this.state = new BoardState();
        this.history = new BoardHistory();
    }

    @Test
    void shouldNotAllowPlayerToPutItsOwnKingInCheck() {
        // Given
        this.state.placePiece("e1", new King(Color.WHITE));
        this.state.placePiece("f1", new Bishop(Color.WHITE));
        this.state.placePiece("h1", new Rook(Color.BLACK));

        // Then
        assertThrows(CantLetOwnKingInCheck.class, () -> PlayValidatorAgainstAllChessRules.validateNextPlay(this.state, this.history, new Move(Color.WHITE, new Position("f1"), new Position("e2"))));
    }
}
