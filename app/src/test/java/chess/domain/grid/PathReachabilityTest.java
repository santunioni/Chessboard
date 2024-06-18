package chess.domain.grid;

import chess.domain.board.Board;
import chess.domain.board.PieceColor;
import chess.domain.board.PieceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PathReachabilityTest {
    private final PieceFactory pieceFactory = new PieceFactory();
    private Board board;

    @BeforeEach
    void setUp() {
        this.board = new Board();
    }

    @Test
    void shouldNotReachA5FromA1IfPieceInA5() {
        this.board.placePieceAt("a5", this.pieceFactory.createPawns(PieceColor.WHITE).get(0));
        var path = new Position("a1").pathTo(new Position("a5")).orElseThrow();
        assertTrue(path.isBlockedOn(this.board));
    }

    @Test
    void shouldNotReachA5FromA1IfPieceInA4() {
        this.board.placePieceAt("a4", this.pieceFactory.createPawns(PieceColor.WHITE).get(0));
        var path = new Position("a1").pathTo(new Position("a5")).orElseThrow();
        assertTrue(path.isBlockedOn(this.board));
    }

    @Test
    void shouldAlwaysBeClearIfZeroSteps() {
        var path = new Path(new Position("a1"), Direction.DIAGONAL_UP_RIGHT, 0);
        assertTrue(path.isClearedOn(this.board));
    }

    @ParameterizedTest
    @ArgumentsSource(PathDirectionCases.class)
    void shouldReachTargetAtExpectedDirection(Position from, Position target,
                                              Direction expectedDirection) {
        var path = from.pathTo(target).orElseThrow();
        assertEquals(expectedDirection, path.getDirection());
        assertTrue(path.isClearedOn(this.board));
    }

    @Test
    void shouldNotFindPathFromA1ToD5() {
        assertTrue(new Position("a1").pathTo(new Position("d5")).isEmpty());
    }

    static class PathDirectionCases implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new Position("b2"), new Position("b5"), Direction.VERTICAL_UP),
                    Arguments.of(new Position("b2"), new Position("d4"), Direction.DIAGONAL_UP_RIGHT),
                    Arguments.of(new Position("b2"), new Position("e2"), Direction.HORIZONTAL_RIGHT),
                    Arguments.of(new Position("b2"), new Position("c1"), Direction.DIAGONAL_DOWN_RIGHT),
                    Arguments.of(new Position("b5"), new Position("b2"), Direction.VERTICAL_DOWN),
                    Arguments.of(new Position("d4"), new Position("b2"), Direction.DIAGONAL_DOWN_LEFT),
                    Arguments.of(new Position("e2"), new Position("b2"), Direction.HORIZONTAL_LEFT),
                    Arguments.of(new Position("c1"), new Position("b2"), Direction.DIAGONAL_UP_LEFT)
            );
        }
    }
}
