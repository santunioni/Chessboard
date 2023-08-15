package chess.board;

import chess.board.position.File;
import chess.board.position.Position;
import chess.board.position.Rank;
import chess.pieces.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardStateFactoryTest {
    @Test
    void shouldPlaceWhiteQueenOnD1() {
        var board = new BoardStateFactory().createFreshBoardState();
        var piece = (Queen) board.getPieceAt("d1").orElseThrow();

        assertInstanceOf(Queen.class, piece);
        assertEquals(Color.WHITE, piece.getColor());
    }

    @Test
    void shouldPlaceBlackQueenOnD8() {
        var board = new BoardStateFactory().createFreshBoardState();
        var piece = (Queen) board.getPieceAt("d8").orElseThrow();

        assertInstanceOf(Queen.class, piece);
        assertEquals(Color.BLACK, piece.getColor());
    }


    @Test
    void shouldPlaceWhiteKingOnE1() {
        var board = new BoardStateFactory().createFreshBoardState();
        var piece = (King) board.getPieceAt("e1").orElseThrow();

        assertInstanceOf(King.class, piece);
        assertEquals(Color.WHITE, piece.getColor());
    }

    @Test
    void shouldPlaceBlackKingOnE8() {
        var board = new BoardStateFactory().createFreshBoardState();
        var piece = (King) board.getPieceAt("e8").orElseThrow();

        assertInstanceOf(King.class, piece);
        assertEquals(Color.BLACK, piece.getColor());
    }

    @Test
    void shouldPlaceWhiteRooksOnA1AndH1() {
        var board = new BoardStateFactory().createFreshBoardState();

        for (var position : List.of(new Position("a1"), new Position("h1"))) {
            var piece = (Rook) board.getPieceAt(position).orElseThrow();
            assertInstanceOf(Rook.class, piece);
            assertEquals(Color.WHITE, piece.getColor());
        }
    }

    @Test
    void shouldPlaceBlackRooksOnA8AndH8() {
        var board = new BoardStateFactory().createFreshBoardState();

        for (var position : List.of(new Position("a8"), new Position("h8"))) {
            var piece = (Rook) board.getPieceAt(position).orElseThrow();
            assertInstanceOf(Rook.class, piece);
            assertEquals(Color.BLACK, piece.getColor());
        }
    }

    @Test
    void shouldPlaceWhiteKnightsOnB1AndG1() {
        var board = new BoardStateFactory().createFreshBoardState();

        for (var position : List.of(new Position("b1"), new Position("g1"))) {
            var piece = (Knight) board.getPieceAt(position).orElseThrow();
            assertInstanceOf(Knight.class, piece);
            assertEquals(Color.WHITE, piece.getColor());
        }
    }

    @Test
    void shouldPlaceBlackKnightsOnB8AndG8() {
        var board = new BoardStateFactory().createFreshBoardState();

        for (var position : List.of(new Position("b8"), new Position("g8"))) {
            var piece = (Knight) board.getPieceAt(position).orElseThrow();
            assertInstanceOf(Knight.class, piece);
            assertEquals(Color.BLACK, piece.getColor());
        }
    }

    @Test
    void shouldPlaceWhiteBishopsOnC1AndF1() {
        var board = new BoardStateFactory().createFreshBoardState();

        for (var position : List.of(new Position("c1"), new Position("f1"))) {
            var piece = (Bishop) board.getPieceAt(position).orElseThrow();
            assertInstanceOf(Bishop.class, piece);
            assertEquals(Color.WHITE, piece.getColor());
        }
    }

    @Test
    void shouldPlaceBlackBishopsOnC8AndF8() {
        var board = new BoardStateFactory().createFreshBoardState();

        for (var position : List.of(new Position("c8"), new Position("f8"))) {
            var piece = (Bishop) board.getPieceAt(position).orElseThrow();
            assertInstanceOf(Bishop.class, piece);
            assertEquals(Color.BLACK, piece.getColor());
        }
    }

    @Test
    void shouldPlaceWhitePawnsOnSecondRank() {
        var board = new BoardStateFactory().createFreshBoardState();

        for (var file : File.values()) {
            var position = new Position(file, Rank.TWO);
            var piece = (Pawn) board.getPieceAt(position).orElseThrow();
            assertInstanceOf(Pawn.class, piece);
            assertEquals(Color.WHITE, piece.getColor());
        }
    }

    @Test
    void shouldPlaceBlackPawnsOnSeventhRank() {
        var board = new BoardStateFactory().createFreshBoardState();

        for (var file : File.values()) {
            var position = new Position(file, Rank.SEVEN);
            var piece = (Pawn) board.getPieceAt(position).orElseThrow();
            assertInstanceOf(Pawn.class, piece);
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
