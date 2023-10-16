package chess.game.plays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.game.board.Board;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.PieceFactory;
import chess.game.board.pieces.PieceType;
import chess.game.grid.Position;
import chess.game.plays.validation.PlayValidationError;
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
  void shouldNotPromoteOnInvalidRank() {
    this.board.placePiece("a7", this.pieceFactory.createPawns(Color.WHITE).get(0));

    var promotion = new Promotion(Color.WHITE, new Position("a7"),
        PieceType.QUEEN);

    assertThrows(PlayValidationError.class, () -> promotion.actOn(this.board));
  }

  @Test
  void shouldNotPromoteOtherThanPawn() {
    this.board.placePiece("a8", this.pieceFactory.createBishops(Color.WHITE).get(0));

    var promotion = new Promotion(Color.WHITE, new Position("b8"),
        PieceType.QUEEN);

    assertThrows(PlayValidationError.class, () -> promotion.actOn(this.board));
  }

  @Test
  void shouldNotPromoteToInvalidPiece() {
    this.board.placePiece("a8", this.pieceFactory.createPawns(Color.WHITE).get(0));

    var promotion = new Promotion(Color.WHITE, new Position("b8"),
        PieceType.KING);

    assertThrows(PlayValidationError.class, () -> promotion.actOn(this.board));
  }

  @Test
  void shouldPromoteWhitePawnAtA8ToQueen() throws PlayValidationError {
    this.board.placePiece("a8", this.pieceFactory.createPawns(Color.WHITE).get(0));

    var promotion = new Promotion(Color.WHITE, new Position("a8"),
        PieceType.QUEEN);
    promotion.actOn(this.board);

    var whiteQueen = this.board.getPieceAt("a8").orElseThrow();
    assertEquals(PieceType.QUEEN, whiteQueen.getSpecification().pieceType());
    assertEquals(Color.WHITE, whiteQueen.getSpecification().color());
  }
}
