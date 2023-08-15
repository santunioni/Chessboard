package chess.board.position;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FileToStringCases implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(File.A, "a"),
                Arguments.of(File.B, "b"),
                Arguments.of(File.C, "c"),
                Arguments.of(File.D, "d"),
                Arguments.of(File.E, "e"),
                Arguments.of(File.F, "f"),
                Arguments.of(File.G, "g"),
                Arguments.of(File.H, "h")
        );
    }
}


public class FileToStringTest {
    @ParameterizedTest
    @ArgumentsSource(FileToStringCases.class)
    void shouldReturnFileStringRepresentation(File file, String expectedRepresentation) {
        assertEquals(expectedRepresentation, file.toString());
    }
}
