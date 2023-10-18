package chess.domain.board;

import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.grid.File;
import chess.domain.grid.Position;
import chess.domain.grid.Rank;
import chess.domain.pieces.Color;
import chess.domain.pieces.PieceSpecification;
import chess.domain.pieces.PieceType;
import java.util.List;
import org.junit.jupiter.api.Test;

public class BoardInitializerTest {
  @Test
  void shouldPlaceWhiteQueenOnD1() {
    var board = new BoardInitializer().placeAll().getBoard();
    assertTrue(
        board
            .getPieceAt(new Position("d1"), new PieceSpecification(Color.WHITE, PieceType.QUEEN))
            .isPresent()
    );
  }

  @Test
  void shouldPlaceBlackQueenOnD8() {
    var board = new BoardInitializer().placeAll().getBoard();
    assertTrue(
        board
            .getPieceAt(new Position("d8"), new PieceSpecification(Color.BLACK, PieceType.QUEEN))
            .isPresent()
    );
  }


  @Test
  void shouldPlaceWhiteKingOnE1() {
    var board = new BoardInitializer().placeAll().getBoard();
    assertTrue(
        board
            .getPieceAt(new Position("e1"), new PieceSpecification(Color.WHITE, PieceType.KING))
            .isPresent()
    );
  }

  @Test
  void shouldPlaceBlackKingOnE8() {
    var board = new BoardInitializer().placeAll().getBoard();
    assertTrue(
        board
            .getPieceAt(new Position("e8"), new PieceSpecification(Color.BLACK, PieceType.KING))
            .isPresent()
    );
  }

  @Test
  void shouldPlaceWhiteRooksOnA1AndH1() {
    var board = new BoardInitializer().placeAll().getBoard();

    for (var position : List.of(new Position("a1"), new Position("h1"))) {
      assertTrue(
          board
              .getPieceAt(position, new PieceSpecification(Color.WHITE, PieceType.ROOK))
              .isPresent()
      );
    }
  }

  @Test
  void shouldPlaceBlackRooksOnA8AndH8() {
    var board = new BoardInitializer().placeAll().getBoard();

    for (var position : List.of(new Position("a8"), new Position("h8"))) {
      assertTrue(
          board
              .getPieceAt(position, new PieceSpecification(Color.BLACK, PieceType.ROOK))
              .isPresent()
      );
    }
  }

  @Test
  void shouldPlaceWhiteKnightsOnB1AndG1() {
    var board = new BoardInitializer().placeAll().getBoard();

    for (var position : List.of(new Position("b1"), new Position("g1"))) {
      assertTrue(
          board
              .getPieceAt(position, new PieceSpecification(Color.WHITE, PieceType.KNIGHT))
              .isPresent()
      );
    }
  }

  @Test
  void shouldPlaceBlackKnightsOnB8AndG8() {
    var board = new BoardInitializer().placeAll().getBoard();

    for (var position : List.of(new Position("b8"), new Position("g8"))) {
      assertTrue(
          board
              .getPieceAt(position, new PieceSpecification(Color.BLACK, PieceType.KNIGHT))
              .isPresent()
      );
    }
  }

  @Test
  void shouldPlaceWhiteBishopsOnC1AndF1() {
    var board = new BoardInitializer().placeAll().getBoard();

    for (var position : List.of(new Position("c1"), new Position("f1"))) {
      assertTrue(
          board
              .getPieceAt(position, new PieceSpecification(Color.WHITE, PieceType.BISHOP))
              .isPresent()
      );
    }
  }

  @Test
  void shouldPlaceBlackBishopsOnC8AndF8() {
    var board = new BoardInitializer().placeAll().getBoard();

    for (var position : List.of(new Position("c8"), new Position("f8"))) {
      assertTrue(
          board
              .getPieceAt(position, new PieceSpecification(Color.BLACK, PieceType.BISHOP))
              .isPresent()
      );
    }
  }

  @Test
  void shouldPlaceWhitePawnsOnSecondRank() {
    var board = new BoardInitializer().placeAll().getBoard();

    for (var file : File.values()) {
      var position = new Position(file, Rank.TWO);
      assertTrue(
          board
              .getPieceAt(position, new PieceSpecification(Color.WHITE, PieceType.PAWN))
              .isPresent()
      );
    }
  }

  @Test
  void shouldPlaceBlackPawnsOnSeventhRank() {
    var board = new BoardInitializer().placeAll().getBoard();

    for (var file : File.values()) {
      var position = new Position(file, Rank.SEVEN);
      assertTrue(
          board
              .getPieceAt(position, new PieceSpecification(Color.BLACK, PieceType.PAWN))
              .isPresent()
      );
    }
  }

  @Test
  void shouldPlaceEmptySquaresOnThirdToSixthRank() {
    var board = new BoardInitializer().placeAll().getBoard();

    for (var file : File.values()) {
      for (var rank : List.of(Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX)) {
        var position = new Position(file, rank);
        assertTrue(board.getPieceAt(position).isEmpty());
      }
    }
  }
}
