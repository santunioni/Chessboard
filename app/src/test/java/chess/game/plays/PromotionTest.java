package chess.game.plays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.game.board.Board;
import chess.game.board.BoardInitializer;
import chess.game.board.PlayHistory;
import chess.game.grid.Position;
import chess.game.pieces.Bishop;
import chess.game.pieces.Color;
import chess.game.pieces.Pawn;
import chess.game.pieces.Type;
import chess.game.plays.validation.PlayValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PromotionTest {
  private Board board;
  private PlayHistory history;

  @BeforeEach
  void setUp() {
    this.board = new BoardInitializer().placeKings().getBoard();
    this.history = new PlayHistory();
  }


  @Test
  void shouldNotPromoteOnInvalidRank() {
    this.board.placePiece("a7", new Pawn(Color.WHITE));

    var promotion = new Promotion(Color.WHITE, new Position("a7"),
        Type.QUEEN);

    assertThrows(PlayValidationError.class, () -> promotion.actOn(this.board, this.history));
  }

  @Test
  void shouldNotPromotePieceThatIsNotAPawn() {
    this.board.placePiece("a8", new Bishop(Color.WHITE));

    var promotion = new Promotion(Color.WHITE, new Position("b8"),
        Type.QUEEN);

    assertThrows(PlayValidationError.class, () -> promotion.actOn(this.board, this.history));
  }

  @Test
  void shouldNotPromoteToInvalidPiece() {
    this.board.placePiece("a8", new Pawn(Color.WHITE));

    var promotion = new Promotion(Color.WHITE, new Position("b8"),
        Type.KING);

    assertThrows(PlayValidationError.class, () -> promotion.actOn(this.board, this.history));
  }

  @Test
  void shouldPromoteWhitePawnAtA8ToQueen() throws PlayValidationError {
    this.board.placePiece("a8", new Pawn(Color.WHITE));

    var promotion = new Promotion(Color.WHITE, new Position("a8"),
        Type.QUEEN);
    promotion.actOn(this.board, this.history);

    var whiteQueen = this.board.getPieceAt("a8").orElseThrow();
    assertEquals(Type.QUEEN, whiteQueen.getType());
    assertEquals(Color.WHITE, whiteQueen.getColor());
  }
}
