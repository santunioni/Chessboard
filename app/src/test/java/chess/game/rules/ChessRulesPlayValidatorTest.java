package chess.game.rules;

import chess.game.board.BoardHistory;
import chess.game.board.BoardState;
import chess.game.grid.Position;
import chess.game.pieces.Bishop;
import chess.game.pieces.Color;
import chess.game.pieces.King;
import chess.game.pieces.Rook;
import chess.game.plays.Move;
import chess.game.rules.validation.CantLetOwnKingInCheckIlegalBoardStateError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChessRulesPlayValidatorTest {

    private BoardState boardState;
    private ChessRulesPlayValidator chessRulesPlayValidator;

    @BeforeEach
    void setUp() {
        this.boardState = new BoardState();
        BoardHistory boardHistory = new BoardHistory();
        this.chessRulesPlayValidator = new ChessRulesPlayValidator(this.boardState, boardHistory);
    }

    @Test
    void shouldNotAllowPlayerToPutItsOwnKingInCheck() {
        // Given
        this.boardState.placePiece("e1", new King(Color.WHITE));
        this.boardState.placePiece("f1", new Bishop(Color.WHITE));
        this.boardState.placePiece("h1", new Rook(Color.BLACK));

        // Then
        assertThrows(CantLetOwnKingInCheckIlegalBoardStateError.class, () -> this.chessRulesPlayValidator.validatePlayAgainstChessRules(new Move(Color.WHITE, new Position("f1"), new Position("e2"))));
    }
}
