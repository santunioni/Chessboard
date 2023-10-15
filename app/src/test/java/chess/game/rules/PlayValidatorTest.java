package chess.game.rules;

import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.game.board.Board;
import chess.game.board.pieces.Bishop;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.King;
import chess.game.board.pieces.Rook;
import chess.game.grid.Position;
import chess.game.plays.Move;
import chess.game.rules.validation.CantLetOwnKingInCheckValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayValidatorTest {

  private Board state;

  @BeforeEach
  void setUp() {
    this.state = new Board();
  }

  @Test
  void shouldNotAllowPlayerToPutItsOwnKingInCheck() {
    // Given
    this.state.placePiece("e1", new King(Color.WHITE));
    this.state.placePiece("f1", new Bishop(Color.WHITE));
    this.state.placePiece("h1", new Rook(Color.BLACK));

    // Then
    assertThrows(CantLetOwnKingInCheckValidationError.class,
        () -> new PlayValidator(this.state).validateNextPlay(
            new Move(Color.WHITE, new Position("f1"), new Position("e2"))));
  }
}
