package chess.board;

import chess.board.position.File;
import chess.board.position.Position;
import chess.board.position.Rank;
import chess.pieces.Color;
import chess.pieces.Type;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardStateFactoryTest {
    @Test
    void shouldPlaceWhiteQueenOnD1() {
        var board = new BoardStateFactory().createFreshBoardState();
        var piece = board.getPieceAt("d1").orElseThrow();

        assertEquals(Type.QUEEN, piece.getType());
        assertEquals(Color.WHITE, piece.getColor());
    }

    @Test
    void shouldPlaceBlackQueenOnD8() {
        var board = new BoardStateFactory().createFreshBoardState();
        var piece = board.getPieceAt("d8").orElseThrow();

        assertEquals(Type.QUEEN, piece.getType());
        assertEquals(Color.BLACK, piece.getColor());
    }


    @Test
    void shouldPlaceWhiteKingOnE1() {
        var board = new BoardStateFactory().createFreshBoardState();
        var piece = board.getPieceAt("e1").orElseThrow();

        assertEquals(Type.KING, piece.getType());
        assertEquals(Color.WHITE, piece.getColor());
    }

    @Test
    void shouldPlaceBlackKingOnE8() {
        var board = new BoardStateFactory().createFreshBoardState();
        var piece = board.getPieceAt("e8").orElseThrow();

        assertEquals(Type.KING, piece.getType());
        assertEquals(Color.BLACK, piece.getColor());
    }

    @Test
    void shouldPlaceWhiteRooksOnA1AndH1() {
        var board = new BoardStateFactory().createFreshBoardState();

        for (var position : List.of(new Position("a1"), new Position("h1"))) {
            var piece = board.getPieceAt(position).orElseThrow();

            assertEquals(Type.ROOK, piece.getType());
            assertEquals(Color.WHITE, piece.getColor());
        }
    }

    @Test
    void shouldPlaceBlackRooksOnA8AndH8() {
        var board = new BoardStateFactory().createFreshBoardState();

        for (var position : List.of(new Position("a8"), new Position("h8"))) {
            var piece = board.getPieceAt(position).orElseThrow();

            assertEquals(Type.ROOK, piece.getType());
            assertEquals(Color.BLACK, piece.getColor());
        }
    }

    @Test
    void shouldPlaceWhiteKnightsOnB1AndG1() {
        var board = new BoardStateFactory().createFreshBoardState();

        for (var position : List.of(new Position("b1"), new Position("g1"))) {
            var piece = board.getPieceAt(position).orElseThrow();

            assertEquals(Type.KNIGHT, piece.getType());
            assertEquals(Color.WHITE, piece.getColor());
        }
    }

    @Test
    void shouldPlaceBlackKnightsOnB8AndG8() {
        var board = new BoardStateFactory().createFreshBoardState();

        for (var position : List.of(new Position("b8"), new Position("g8"))) {
            var piece = board.getPieceAt(position).orElseThrow();

            assertEquals(Type.KNIGHT, piece.getType());
            assertEquals(Color.BLACK, piece.getColor());
        }
    }

    @Test
    void shouldPlaceWhiteBishopsOnC1AndF1() {
        var board = new BoardStateFactory().createFreshBoardState();

        for (var position : List.of(new Position("c1"), new Position("f1"))) {
            var piece = board.getPieceAt(position).orElseThrow();

            assertEquals(Type.BISHOP, piece.getType());
            assertEquals(Color.WHITE, piece.getColor());
        }
    }

    @Test
    void shouldPlaceBlackBishopsOnC8AndF8() {
        var board = new BoardStateFactory().createFreshBoardState();

        for (var position : List.of(new Position("c8"), new Position("f8"))) {
            var piece = board.getPieceAt(position).orElseThrow();

            assertEquals(Type.BISHOP, piece.getType());
            assertEquals(Color.BLACK, piece.getColor());
        }
    }

    @Test
    void shouldPlaceWhitePawnsOnSecondRank() {
        var board = new BoardStateFactory().createFreshBoardState();

        for (var file : File.values()) {
            var position = new Position(file, Rank.TWO);
            var piece = board.getPieceAt(position).orElseThrow();

            assertEquals(Type.PAWN, piece.getType());
            assertEquals(Color.WHITE, piece.getColor());
        }
    }

    @Test
    void shouldPlaceBlackPawnsOnSeventhRank() {
        var board = new BoardStateFactory().createFreshBoardState();

        for (var file : File.values()) {
            var position = new Position(file, Rank.SEVEN);
            var piece = board.getPieceAt(position).orElseThrow();

            assertEquals(Type.PAWN, piece.getType());
            assertEquals(Color.BLACK, piece.getColor());
        }
    }

    @Test
    void shouldPlaceEmptySquaresOnThirdToSixthRank() {
        var board = new BoardStateFactory().createFreshBoardState();

        for (var file : File.values()) {
            for (var rank : List.of(Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX)) {
                var position = new Position(file, rank);
                assertTrue(board.getPieceAt(position).isEmpty());
            }
        }
    }
}
