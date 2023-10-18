package chess.domain.assertions;

import static org.junit.jupiter.api.Assertions.assertFalse;

import chess.domain.board.Board;
import chess.domain.grid.Position;
import chess.domain.pieces.Color;
import chess.domain.pieces.PieceFactory;
import chess.domain.plays.Capture;
import chess.domain.plays.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IsPlayLegalAssertionTest {

  private final PieceFactory pieceFactory = new PieceFactory();
  private Board board;

  @BeforeEach
  void setUp() {
    this.board = new Board();
  }


  @Test
  void shouldNotAllowBlackToCaptureWhiteIfItIsNotItsTurn() {
    // Given
    this.board.placePiece("a2", this.pieceFactory.createRooks(Color.BLACK).get(0));
    this.board.placePiece("a1", this.pieceFactory.createRooks(Color.WHITE).get(0));

    // When
    var capture = new Capture(Color.BLACK, new Position("a2"), new Position("a1"));

    // Then
    assertFalse(capture.isLegalOn(board));
  }

  @Test
  void shouldNotAllowPlayerToPutItsOwnKingInCheck() {
    // Given
    this.board.placePiece("e1", this.pieceFactory.createKing(Color.WHITE));
    this.board.placePiece("f1", this.pieceFactory.createBishops(Color.WHITE).get(0));
    this.board.placePiece("h1", this.pieceFactory.createRooks(Color.BLACK).get(0));

    // When
    var move = new Move(Color.WHITE, new Position("f1"), new Position("e2"));

    // Then
    assertFalse(move.isLegalOn(board));
  }

  @Test
  void shouldRequirePawnPromotion() {
    // Given
    this.board.placePiece("a7", this.pieceFactory.createPawns(Color.WHITE).get(0));

    // When
    var move = new Move(Color.WHITE, new Position("a7"), new Position("a8"));

    // Then
    assertFalse(move.isLegalOn(board));
  }
}
