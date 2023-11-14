package chess.domain.assertions;

import static org.junit.jupiter.api.Assertions.assertFalse;

import chess.domain.board.Board;
import chess.domain.board.PieceColor;
import chess.domain.board.PieceFactory;
import chess.domain.board.PieceType;
import chess.domain.grid.Position;
import chess.domain.play.Capture;
import chess.domain.play.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayLegalityAssertionTest {

  private final PieceFactory pieceFactory = new PieceFactory();
  private Board board;

  @BeforeEach
  void setUp() {
    this.board = new Board();
  }


  @Test
  void shouldNotAllowBlackToCaptureWhiteIfItIsNotItsTurn() {
    // Given
    this.board.placePieceAt("a2", this.pieceFactory.createRooks(PieceColor.BLACK).get(0));
    this.board.placePieceAt("a1", this.pieceFactory.createRooks(PieceColor.WHITE).get(0));

    // When
    var capture =
        new Capture(PieceType.ROOK, PieceColor.BLACK, new Position("a2"), new Position("a1"));

    // Then
    assertFalse(new PlayLegalityAssertion(capture).test(board));
  }

  @Test
  void shouldNotAllowPlayerToPutItsOwnKingInCheck() {
    // Given
    this.board.placePieceAt("e1", this.pieceFactory.createKing(PieceColor.WHITE));
    this.board.placePieceAt("f1", this.pieceFactory.createBishops(PieceColor.WHITE).get(0));
    this.board.placePieceAt("h1", this.pieceFactory.createRooks(PieceColor.BLACK).get(0));

    // When
    var move = new Move(PieceType.BISHOP, PieceColor.WHITE, new Position("f1"), new Position("e2"));

    // Then
    assertFalse(new PlayLegalityAssertion(move).test(board));
  }

  @Test
  void shouldRequirePawnPromotion() {
    // Given
    this.board.placePieceAt("a7", this.pieceFactory.createPawns(PieceColor.WHITE).get(0));

    // When
    var move = new Move(PieceType.PAWN, PieceColor.WHITE, new Position("a7"), new Position("a8"));

    // Then
    assertFalse(new PlayLegalityAssertion(move).test(board));
  }
}
