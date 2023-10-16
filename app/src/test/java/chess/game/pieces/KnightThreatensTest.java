package chess.game.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.game.board.Board;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.PieceFactory;
import chess.game.grid.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;


public class KnightThreatensTest {
  private final PieceFactory pieceFactory = new PieceFactory();
  private Board board;

  @BeforeEach
  void setUp() {
    this.board = new Board();
  }

  @ParameterizedTest
  @ArgumentsSource(KnightThreatensInLcases.class)
  void shouldThreatenPositionDisplacedInLpattern(String knightPosition, String threatenedPosition) {
    var knight = this.pieceFactory.createKnights(Color.WHITE).get(0);
    this.board.placePiece(knightPosition, knight);
    assertTrue(knight.threatens(new Position(threatenedPosition)));
  }

  @ParameterizedTest
  @ArgumentsSource(KnightNotThreatensNotInLcases.class)
  void shouldNotThreatenPositionNotDisplacedInLpattern(String knightPosition,
                                                       String notThreatenedPosition) {
    var knight = this.pieceFactory.createKnights(Color.WHITE).get(0);
    this.board.placePiece(knightPosition, knight);
    assertFalse(knight.threatens(new Position(notThreatenedPosition)));
  }

  static class KnightThreatensInLcases implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
      return Stream.of(
          Arguments.of("d4", "e6"),
          Arguments.of("d4", "c6"),
          Arguments.of("d4", "b5"),
          Arguments.of("d4", "b3"),
          Arguments.of("d4", "c2"),
          Arguments.of("d4", "e2"),
          Arguments.of("d4", "f3"),
          Arguments.of("d4", "f5")
      );
    }
  }

  static class KnightNotThreatensNotInLcases implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
      return Stream.of(
          Arguments.of("d4", "d5"),
          Arguments.of("d4", "d3"),
          Arguments.of("d4", "e4"),
          Arguments.of("d4", "c4"),
          Arguments.of("d4", "e5"),
          Arguments.of("d4", "e3"),
          Arguments.of("d4", "c3"),
          Arguments.of("d4", "c5")
      );
    }
  }
}
