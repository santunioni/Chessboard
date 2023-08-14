package chess.board.path;

import chess.board.position.File;
import chess.board.position.Position;
import chess.board.position.Rank;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardPathTest {

    @Test
    void shouldReturnDiagonalUpRightFromA1() {
        var path = new BoardPath(new Position(File.A, Rank.ONE), BoardPathOrientation.DIAGONAL_UP_RIGHT).toPositionList();

        var expectedPath = List.of(
                new Position(File.B, Rank.TWO),
                new Position(File.C, Rank.THREE),
                new Position(File.D, Rank.FOUR),
                new Position(File.E, Rank.FIVE),
                new Position(File.F, Rank.SIX),
                new Position(File.G, Rank.SEVEN),
                new Position(File.H, Rank.EIGHT)
        );

        assertEquals(expectedPath, path);
    }

    @Test
    void shouldReturnDiagonalUpLeftFromH2() {
        var path = new BoardPath(new Position(File.H, Rank.TWO), BoardPathOrientation.DIAGONAL_UP_LEFT).toPositionList();

        var expectedPath = List.of(
                new Position(File.G, Rank.THREE),
                new Position(File.F, Rank.FOUR),
                new Position(File.E, Rank.FIVE),
                new Position(File.D, Rank.SIX),
                new Position(File.C, Rank.SEVEN),
                new Position(File.B, Rank.EIGHT)
        );

        assertEquals(expectedPath, path);
    }

    @Test
    void shouldReturnDiagonalDownLeftFromF8() {
        var path = new BoardPath(new Position(File.F, Rank.EIGHT), BoardPathOrientation.DIAGONAL_DOWN_LEFT).toPositionList();

        var expectedPath = List.of(
                new Position(File.E, Rank.SEVEN),
                new Position(File.D, Rank.SIX),
                new Position(File.C, Rank.FIVE),
                new Position(File.B, Rank.FOUR),
                new Position(File.A, Rank.THREE)
        );

        assertEquals(expectedPath, path);
    }

    @Test
    void shouldReturnDiagonalDownRightFromA5() {
        var path = new BoardPath(new Position(File.A, Rank.FIVE), BoardPathOrientation.DIAGONAL_DOWN_RIGHT).toPositionList();

        var expectedPath = List.of(
                new Position(File.B, Rank.FOUR),
                new Position(File.C, Rank.THREE),
                new Position(File.D, Rank.TWO),
                new Position(File.E, Rank.ONE)
        );

        assertEquals(expectedPath, path);
    }


    @Test
    void shouldReturnVerticalUpFromA1() {
        var path = new BoardPath(new Position(File.A, Rank.ONE), BoardPathOrientation.VERTICAL_UP).toPositionList();

        var expectedPath = List.of(
                new Position(File.A, Rank.TWO),
                new Position(File.A, Rank.THREE),
                new Position(File.A, Rank.FOUR),
                new Position(File.A, Rank.FIVE),
                new Position(File.A, Rank.SIX),
                new Position(File.A, Rank.SEVEN),
                new Position(File.A, Rank.EIGHT)
        );

        assertEquals(expectedPath, path);
    }

    @Test
    void shouldReturnVerticalDownFromB5() {
        var path = new BoardPath(new Position(File.B, Rank.FIVE), BoardPathOrientation.VERTICAL_DOWN).toPositionList();

        var expectedPath = List.of(
                new Position(File.B, Rank.FOUR),
                new Position(File.B, Rank.THREE),
                new Position(File.B, Rank.TWO),
                new Position(File.B, Rank.ONE)
        );

        assertEquals(expectedPath, path);
    }

    @Test
    void shouldReturnHorizontalRightFromA1() {
        var path = new BoardPath(new Position(File.A, Rank.ONE), BoardPathOrientation.HORIZONTAL_RIGHT).toPositionList();

        var expectedPath = List.of(
                new Position(File.B, Rank.ONE),
                new Position(File.C, Rank.ONE),
                new Position(File.D, Rank.ONE),
                new Position(File.E, Rank.ONE),
                new Position(File.F, Rank.ONE),
                new Position(File.G, Rank.ONE),
                new Position(File.H, Rank.ONE)
        );

        assertEquals(expectedPath, path);
    }

    @Test
    void shouldReturnHorizontalLeftFromE2() {
        var path = new BoardPath(new Position(File.E, Rank.TWO), BoardPathOrientation.HORIZONTAL_LEFT).toPositionList();

        var expectedPath = List.of(
                new Position(File.D, Rank.TWO),
                new Position(File.C, Rank.TWO),
                new Position(File.B, Rank.TWO),
                new Position(File.A, Rank.TWO)
        );

        assertEquals(expectedPath, path);
    }

    @Test
    void shouldReturnEmptyPathToLeftOfA1T() {
        var path = new BoardPath(new Position(File.A, Rank.ONE), BoardPathOrientation.HORIZONTAL_LEFT).toPositionList();

        assertEquals(0, path.size());
    }
}
