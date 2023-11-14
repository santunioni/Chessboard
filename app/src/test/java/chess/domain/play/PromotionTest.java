package chess.domain.play;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.board.Board;
import chess.domain.board.PieceColor;
import chess.domain.board.PieceFactory;
import chess.domain.board.PieceType;
import chess.domain.grid.Position;
import chess.domain.play.validation.PlayValidationError;
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
    this.board.placePieceAt("a6", this.pieceFactory.createPawns(PieceColor.WHITE).get(0));

    var promotion = new Promotion(
        new Move(PieceType.PAWN, PieceColor.WHITE, new Position("a6"), new Position("a7")),
        PieceType.QUEEN
    );

    assertFalse(promotion.canActOnCurrentState(board));
  }

  @Test
  void shouldNotPromoteOtherThanPawn() {
    this.board.placePieceAt("a7", this.pieceFactory.createBishops(PieceColor.WHITE).get(0));

    var promotion = new Promotion(
        new Move(PieceType.PAWN, PieceColor.WHITE, new Position("a7"), new Position("b8")),
        PieceType.QUEEN
    );

    assertFalse(promotion.canActOnCurrentState(board));
  }

  @Test
  void shouldNotPromoteToInvalidPiece() {
    this.board.placePieceAt("a7", this.pieceFactory.createPawns(PieceColor.WHITE).get(0));

    var promotion = new Promotion(
        new Move(PieceType.PAWN, PieceColor.WHITE, new Position("a7"), new Position("b8")),
        PieceType.QUEEN
    );

    assertFalse(promotion.canActOnCurrentState(board));
  }

  @Test
  void shouldPromoteWhitePawnAtA7ToQueen() throws PlayValidationError {
    this.board.placePieceAt("a7", this.pieceFactory.createPawns(PieceColor.WHITE).get(0));

    var promotion = new Promotion(
        new Move(PieceType.PAWN, PieceColor.WHITE, new Position("a7"), new Position("a8")),
        PieceType.QUEEN
    );
    this.board.makePlay(promotion);

    assertTrue(board.getPieceAt(new Position("a8"), PieceColor.WHITE, PieceType.QUEEN).isPresent());
  }

  @Test
  void shouldReturnAlgebraicNotation() {
    var promotion = new Promotion(
        new Move(PieceType.PAWN, PieceColor.WHITE, new Position("a7"), new Position("a8")),
        PieceType.QUEEN
    );
    assertEquals("a7a8=Q", promotion.toDto().algebraicNotation());
  }
}
