package chess.game.rules;

import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.game.board.Board;
import chess.game.board.PlayHistory;
import chess.game.grid.Position;
import chess.game.pieces.Bishop;
import chess.game.pieces.Color;
import chess.game.pieces.King;
import chess.game.pieces.Rook;
import chess.game.plays.Move;
import chess.game.rules.validation.CantLetOwnKingInCheck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayValidatorAgainstAllChessRulesTest {

  private Board state;
  private PlayHistory history;

  @BeforeEach
  void setUp() {
    this.state = new Board();
    this.history = new PlayHistory();
  }

  @Test
  void shouldNotAllowPlayerToPutItsOwnKingInCheck() {
    // Given
    this.state.placePiece("e1", new King(Color.WHITE));
    this.state.placePiece("f1", new Bishop(Color.WHITE));
    this.state.placePiece("h1", new Rook(Color.BLACK));

    // Then
    assertThrows(CantLetOwnKingInCheck.class,
        () -> PlayValidatorAgainstAllChessRules.validateNextPlay(this.state, this.history,
            new Move(Color.WHITE, new Position("f1"), new Position("e2"))));
  }
}
