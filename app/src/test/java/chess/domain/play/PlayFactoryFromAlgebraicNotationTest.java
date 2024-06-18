package chess.domain.play;

import chess.domain.board.PieceColor;
import chess.domain.board.PieceType;
import chess.domain.grid.Position;
import chess.domain.play.validation.PlayValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayFactoryFromAlgebraicNotationTest {

    private final PlayFactoryFromAlgebraicNotation<Play>
            playFactoryFromAlgebraicNotation = new PlayFactoryFromAlgebraicNotation<>(new PlayFactory());

    @Test
    void shouldReturnBishopCapturesPieceOnE5() throws PlayValidationError {
        assertEquals(
                new Capture(PieceType.BISHOP, PieceColor.WHITE, new Position("d4"), new Position("e5")),
                playFactoryFromAlgebraicNotation.createPlayFromLongAlgebraicNotation(PieceColor.WHITE,
                        "Bd4xe5"));
    }

    @Test
    void shouldReturnRookCapturesD7() throws PlayValidationError {
        assertEquals(
                new Capture(PieceType.ROOK, PieceColor.WHITE, new Position("d3"), new Position("d7")),
                playFactoryFromAlgebraicNotation.createPlayFromLongAlgebraicNotation(PieceColor.WHITE,
                        "Rd3xd7"));
    }

    @Test
    void shouldReturnKnightMovesToC3() throws PlayValidationError {
        assertEquals(
                new Move(PieceType.KNIGHT, PieceColor.WHITE, new Position("b1"), new Position("c3")),
                playFactoryFromAlgebraicNotation.createPlayFromLongAlgebraicNotation(PieceColor.WHITE,
                        "Nb1c3"));
    }

    @Test
    void shouldReturnWhiteKingCastleOnRight() throws PlayValidationError {
        assertEquals(new Castle(PieceColor.WHITE, CastleSide.KING_SIDE),
                playFactoryFromAlgebraicNotation.createPlayFromLongAlgebraicNotation(PieceColor.WHITE,
                        "0-0"));
    }

    @Test
    void shouldReturnWhiteKingCastleOnLeft() throws PlayValidationError {
        assertEquals(new Castle(PieceColor.WHITE, CastleSide.QUEEN_SIDE),
                playFactoryFromAlgebraicNotation.createPlayFromLongAlgebraicNotation(PieceColor.WHITE,
                        "0-0-0"));
    }

    @ParameterizedTest
    @ArgumentsSource(AlgebraicExpressionCases.class)
    void shouldCreatePlayThatIsRepresentedAsSameAlgebraicExpression(String algebraic)
            throws PlayValidationError {
        assertEquals(algebraic,
                playFactoryFromAlgebraicNotation.createPlayFromLongAlgebraicNotation(PieceColor.WHITE,
                                algebraic)
                        .toDto().algebraicNotation());
    }

    static class AlgebraicExpressionCases implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of("Bd4xe5"),
                    Arguments.of("Rd3xd7"),
                    Arguments.of("Nb1c3"),
                    Arguments.of("0-0"),
                    Arguments.of("0-0-0"),
                    Arguments.of("e4e5"),
                    Arguments.of("Qd1h5"),
                    Arguments.of("Kd1c1"),
                    Arguments.of("e5xd6 e.p."),
                    Arguments.of("b2xa1=Q"),
                    Arguments.of("b2a2=N")
            );
        }
    }
}
