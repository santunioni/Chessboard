package chess.game.board;

import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.game.board.pieces.Color;
import chess.game.board.pieces.King;
import chess.game.board.pieces.Pawn;
import chess.game.board.pieces.PieceFactory;
import chess.game.board.pieces.Rook;
import chess.game.grid.Position;
import chess.game.plays.Capture;
import chess.game.plays.Move;
import chess.game.plays.validation.PawnShouldBePromotedValidationError;
import chess.game.rules.PlayValidator;
import chess.game.rules.validation.CantLetOwnKingInCheckValidationError;
import chess.game.rules.validation.NotYourTurn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayValidatorTest {

  private Board board;
  private PlayValidator playValidator;
  private final PieceFactory pieceFactory = new PieceFactory();

  @BeforeEach
  void setUp() {
    this.board = new Board();
    this.playValidator = new PlayValidator(this.board);
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
    this.board.placePiece("f1", this.pieceFactory.createBishops(Color.WHITE).get(0));
    this.board.placePiece("h1", new Rook(Color.BLACK));

    // When
    var move = new Move(Color.WHITE, new Position("f1"), new Position("e2"));

    // Then
    assertThrows(CantLetOwnKingInCheckValidationError.class,
        () -> this.playValidator.validateNextPlay(move));
  }

  @Test
  void shouldRequirePawnPromotion() {
    // Given
    this.board.placePiece("a7", new Pawn(Color.WHITE));

    // When
    var move = new Move(Color.WHITE, new Position("a7"), new Position("a8"));

    // Then
    assertThrows(PawnShouldBePromotedValidationError.class,
        () -> this.playValidator.validateNextPlay(move));
  }
}
