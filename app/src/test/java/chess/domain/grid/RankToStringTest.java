package chess.domain.grid;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RankToStringTest {
    @ParameterizedTest
    @ArgumentsSource(RankToStringCases.class)
    void shouldReturnRankStringRepresentation(Rank rank, String expectedRepresentation) {
        assertEquals(expectedRepresentation, rank.toString());
    }

    static class RankToStringCases implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(Rank.ONE, "1"),
                    Arguments.of(Rank.TWO, "2"),
                    Arguments.of(Rank.THREE, "3"),
                    Arguments.of(Rank.FOUR, "4"),
                    Arguments.of(Rank.FIVE, "5"),
                    Arguments.of(Rank.SIX, "6"),
                    Arguments.of(Rank.SEVEN, "7"),
                    Arguments.of(Rank.EIGHT, "8")
            );
        }
    }
}
