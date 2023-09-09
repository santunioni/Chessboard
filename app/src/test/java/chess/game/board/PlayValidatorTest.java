package chess.game.board;

import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.game.grid.Position;
import chess.game.pieces.Bishop;
import chess.game.pieces.Color;
import chess.game.pieces.King;
import chess.game.pieces.Rook;
import chess.game.plays.Capture;
import chess.game.plays.Move;
import chess.game.rules.PlayValidator;
import chess.game.rules.validation.CantLetOwnKingInCheck;
import chess.game.rules.validation.NotYourTurn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayValidatorTest {

  private Board board;
  private PlayValidator playValidator;

  @BeforeEach
  void setUp() {
    this.board = new Board();
    this.playValidator = new PlayValidator(this.board, new PlayHistory());
  }


  @Test
  void shouldNotAllowBlackToCaptureWhiteIfItIsNotItsTurn() {
    // Given
    this.board.placePiece("a2", new Rook(Color.BLACK));
    this.board.placePiece("a1", new Rook(Color.WHITE));

    // When
    var capture = new Capture(Color.BLACK, new Position("a2"), new Position("a1"));

    // Then
    assertThrows(NotYourTurn.class, () -> this.playValidator.validateNextPlay(capture));
  }

  @Test
  void shouldNotAllowPlayerToPutItsOwnKingInCheck() {
    // Given
    this.board.placePiece("e1", new King(Color.WHITE));
    this.board.placePiece("f1", new Bishop(Color.WHITE));
    this.board.placePiece("h1", new Rook(Color.BLACK));

    // When
    var move = new Move(Color.WHITE, new Position("f1"), new Position("e2"));

    // Then
    assertThrows(CantLetOwnKingInCheck.class, () -> this.playValidator.validateNextPlay(move));
  }
}
