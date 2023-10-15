package chess.game.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.game.board.pieces.Color;
import chess.game.board.pieces.PieceType;
import chess.game.grid.File;
import chess.game.grid.Position;
import chess.game.grid.Rank;
import java.util.List;
import org.junit.jupiter.api.Test;

public class BoardInitializerTest {
  @Test
  void shouldPlaceWhiteQueenOnD1() {
    var board = new BoardInitializer().placeAll().getBoard();
    var piece = board.getPieceAt("d1").orElseThrow();

    assertEquals(PieceType.QUEEN, piece.getSpecification().pieceType());
    assertEquals(Color.WHITE, piece.getSpecification().color());
  }

  @Test
  void shouldPlaceBlackQueenOnD8() {
    var board = new BoardInitializer().placeAll().getBoard();
    var piece = board.getPieceAt("d8").orElseThrow();

    assertEquals(PieceType.QUEEN, piece.getSpecification().pieceType());
    assertEquals(Color.BLACK, piece.getSpecification().color());
  }


  @Test
  void shouldPlaceWhiteKingOnE1() {
    var board = new BoardInitializer().placeAll().getBoard();
    var piece = board.getPieceAt("e1").orElseThrow();

    assertEquals(PieceType.KING, piece.getSpecification().pieceType());
    assertEquals(Color.WHITE, piece.getSpecification().color());
  }

  @Test
  void shouldPlaceBlackKingOnE8() {
    var board = new BoardInitializer().placeAll().getBoard();
    var piece = board.getPieceAt("e8").orElseThrow();

    assertEquals(PieceType.KING, piece.getSpecification().pieceType());
    assertEquals(Color.BLACK, piece.getSpecification().color());
  }

  @Test
  void shouldPlaceWhiteRooksOnA1AndH1() {
    var board = new BoardInitializer().placeAll().getBoard();

    for (var position : List.of(new Position("a1"), new Position("h1"))) {
      var piece = board.getPieceAt(position).orElseThrow();

      assertEquals(PieceType.ROOK, piece.getSpecification().pieceType());
      assertEquals(Color.WHITE, piece.getSpecification().color());
    }
  }

  @Test
  void shouldPlaceBlackRooksOnA8AndH8() {
    var board = new BoardInitializer().placeAll().getBoard();

    for (var position : List.of(new Position("a8"), new Position("h8"))) {
      var piece = board.getPieceAt(position).orElseThrow();

      assertEquals(PieceType.ROOK, piece.getSpecification().pieceType());
      assertEquals(Color.BLACK, piece.getSpecification().color());
    }
  }

  @Test
  void shouldPlaceWhiteKnightsOnB1AndG1() {
    var board = new BoardInitializer().placeAll().getBoard();

    for (var position : List.of(new Position("b1"), new Position("g1"))) {
      var piece = board.getPieceAt(position).orElseThrow();

      assertEquals(PieceType.KNIGHT, piece.getSpecification().pieceType());
      assertEquals(Color.WHITE, piece.getSpecification().color());
    }
  }

  @Test
  void shouldPlaceBlackKnightsOnB8AndG8() {
    var board = new BoardInitializer().placeAll().getBoard();

    for (var position : List.of(new Position("b8"), new Position("g8"))) {
      var piece = board.getPieceAt(position).orElseThrow();

      assertEquals(PieceType.KNIGHT, piece.getSpecification().pieceType());
      assertEquals(Color.BLACK, piece.getSpecification().color());
    }
  }

  @Test
  void shouldPlaceWhiteBishopsOnC1AndF1() {
    var board = new BoardInitializer().placeAll().getBoard();

    for (var position : List.of(new Position("c1"), new Position("f1"))) {
      var piece = board.getPieceAt(position).orElseThrow();

      assertEquals(PieceType.BISHOP, piece.getSpecification().pieceType());
      assertEquals(Color.WHITE, piece.getSpecification().color());
    }
  }

  @Test
  void shouldPlaceBlackBishopsOnC8AndF8() {
    var board = new BoardInitializer().placeAll().getBoard();

    for (var position : List.of(new Position("c8"), new Position("f8"))) {
      var piece = board.getPieceAt(position).orElseThrow();

      assertEquals(PieceType.BISHOP, piece.getSpecification().pieceType());
      assertEquals(Color.BLACK, piece.getSpecification().color());
    }
  }

  @Test
  void shouldPlaceWhitePawnsOnSecondRank() {
    var board = new BoardInitializer().placeAll().getBoard();

    for (var file : File.values()) {
      var position = new Position(file, Rank.TWO);
      var piece = board.getPieceAt(position).orElseThrow();

      assertEquals(PieceType.PAWN, piece.getSpecification().pieceType());
      assertEquals(Color.WHITE, piece.getSpecification().color());
    }
  }

  @Test
  void shouldPlaceBlackPawnsOnSeventhRank() {
    var board = new BoardInitializer().placeAll().getBoard();

    for (var file : File.values()) {
      var position = new Position(file, Rank.SEVEN);
      var piece = board.getPieceAt(position).orElseThrow();

      assertEquals(PieceType.PAWN, piece.getSpecification().pieceType());
      assertEquals(Color.BLACK, piece.getSpecification().color());
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
