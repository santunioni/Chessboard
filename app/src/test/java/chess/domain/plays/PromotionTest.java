package chess.domain.plays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.board.Board;
import chess.domain.grid.Position;
import chess.domain.pieces.Color;
import chess.domain.pieces.PieceFactory;
import chess.domain.pieces.PieceSpecification;
import chess.domain.pieces.PieceType;
import chess.domain.plays.validation.PlayValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PromotionTest {
  private final PieceFactory pieceFactory = new PieceFactory();
  private Board board;

  @BeforeEach
  void setUp() {
    this.board = new Board();
  }

  @Test
  void shouldNotPromoteFromInvalidRank() {
    this.board.placePiece("a6", this.pieceFactory.createPawns(Color.WHITE).get(0));

    var promotion = new Promotion(Color.WHITE, new Position("a6"),
        PieceType.QUEEN);

    assertFalse(promotion.isLegalOn(board));
  }

  @Test
  void shouldNotPromoteOtherThanPawn() {
    this.board.placePiece("a7", this.pieceFactory.createBishops(Color.WHITE).get(0));

    var promotion = new Promotion(Color.WHITE, new Position("b8"),
        PieceType.QUEEN);

    assertFalse(promotion.isLegalOn(board));
  }

  @Test
  void shouldNotPromoteToInvalidPiece() {
    this.board.placePiece("a7", this.pieceFactory.createPawns(Color.WHITE).get(0));

    var promotion = new Promotion(Color.WHITE, new Position("b8"),
        PieceType.KING);

    assertFalse(promotion.isLegalOn(board));
  }

  @Test
  void shouldPromoteWhitePawnAtA7ToQueen() throws PlayValidationError {
    this.board.placePiece("a7", this.pieceFactory.createPawns(Color.WHITE).get(0));

    var promotion = new Promotion(Color.WHITE, new Position("a7"), PieceType.QUEEN);
    this.board.makePlay(promotion);

    assertTrue(
        board
            .getPieceAt(new Position("a8"), new PieceSpecification(Color.WHITE, PieceType.QUEEN))
            .isPresent()
    );
  }
}
