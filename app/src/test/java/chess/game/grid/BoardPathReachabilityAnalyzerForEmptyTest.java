package chess.game.grid;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.game.board.Board;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardPathReachabilityAnalyzerForEmptyTest {
  private BoardPathReachabilityAnalyzer boardPathReachabilityAnalyzer;

  @BeforeEach
  void setUp() {
    this.boardPathReachabilityAnalyzer = new BoardPathReachabilityAnalyzer(new Board());
  }

  @Test
  void shouldReachE1FromA1GoingHorizontalRight() {
    assertTrue(this.boardPathReachabilityAnalyzer.isReachableWalkingInOneOfDirections(
        new Position("a1"),
        Set.of(Direction.HORIZONTAL_RIGHT),
        new Position("e1")
    ));
  }

  @Test
  void shouldNotReachE1FromA1GoingHorizontalLeft() {
    assertFalse(this.boardPathReachabilityAnalyzer.isReachableWalkingInOneOfDirections(
        new Position("a1"),
        Set.of(Direction.HORIZONTAL_LEFT),
        new Position("e1")
    ));
  }

  @Test
  void shouldReachA1FromE1GoingHorizontalLeft() {
    assertTrue(this.boardPathReachabilityAnalyzer.isReachableWalkingInOneOfDirections(
        new Position("e1"),
        Set.of(Direction.HORIZONTAL_LEFT),
        new Position("a1")
    ));
  }

  @Test
  void shouldNotReachA1FromE1GoingHorizontalLeft() {
    assertFalse(this.boardPathReachabilityAnalyzer.isReachableWalkingInOneOfDirections(
        new Position("e1"),
        Set.of(Direction.HORIZONTAL_RIGHT),
        new Position("a1")
    ));
  }

  @Test
  void shouldReachA5FromA1GoingVerticalUp() {
    assertTrue(this.boardPathReachabilityAnalyzer.isReachableWalkingInOneOfDirections(
        new Position("a1"),
        Set.of(Direction.VERTICAL_UP),
        new Position("a5")
    ));
  }

  @Test
  void shouldNotReachA5FromA1GoingVerticalDown() {
    assertFalse(this.boardPathReachabilityAnalyzer.isReachableWalkingInOneOfDirections(
        new Position("a1"),
        Set.of(Direction.VERTICAL_DOWN),
        new Position("a5")
    ));
  }

  @Test
  void shouldReachD4FromA1GoingDiagonalUpRight() {
    assertTrue(this.boardPathReachabilityAnalyzer.isReachableWalkingInOneOfDirections(
        new Position("a1"),
        Set.of(Direction.DIAGONAL_UP_RIGHT),
        new Position("d4")
    ));
  }

  @Test
  void shouldNotReachD5FromA1GoingAnyDirection() {
    assertFalse(this.boardPathReachabilityAnalyzer.isReachableWalkingInOneOfDirections(
        new Position("a1"),
        Direction.allDirections(),
        new Position("d5")
    ));
  }
}
