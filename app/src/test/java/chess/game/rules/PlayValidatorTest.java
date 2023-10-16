package chess.game.rules;

import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.game.board.Board;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.PieceFactory;
import chess.game.board.pieces.Rook;
import chess.game.grid.Position;
import chess.game.plays.Move;
import chess.game.rules.validation.CantLetOwnKingInCheckValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayValidatorTest {

  private final PieceFactory pieceFactory = new PieceFactory();
  private Board state;

  @BeforeEach
  void setUp() {
    this.state = new Board();
  }

  @Test
  void shouldNotAllowPlayerToPutItsOwnKingInCheck() {
    // Given
    this.state.placePiece("e1", this.pieceFactory.createKing(Color.WHITE));
    this.state.placePiece("f1", pieceFactory.createBishops(Color.WHITE).get(0));
    this.state.placePiece("h1", new Rook(Color.BLACK));

    // Then
    assertThrows(CantLetOwnKingInCheckValidationError.class,
        () -> new PlayValidator(this.state).validateNextPlay(
            new Move(Color.WHITE, new Position("f1"), new Position("e2"))));
  }
}
