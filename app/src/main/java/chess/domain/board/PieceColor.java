package chess.domain.board;

import chess.domain.grid.Direction;
import chess.domain.grid.File;
import chess.domain.grid.Position;
import chess.domain.grid.Rank;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the side of the player. (White or Black)
 */
public enum PieceColor {
  WHITE,
  BLACK;

  public PieceColor opposite() {
    return this == WHITE ? BLACK : WHITE;
  }

  public Position kingInitialPosition() {
    return this == WHITE ? new Position("e1") : new Position("e8");
  }

  public Position queenInitialPosition() {
    return this == WHITE ? new Position("d1") : new Position("d8");
  }

  public List<Position> knightInitialPositions() {
    return this.equals(PieceColor.WHITE) ? List.of(new Position("b1"), new Position("g1")) :
        List.of(new Position("b8"), new Position("g8"));
  }

  public List<Position> bishopInitialPositions() {
    return this.equals(PieceColor.WHITE) ? List.of(new Position("c1"), new Position("f1")) :
        List.of(new Position("c8"), new Position("f8"));
  }

  public List<Position> rookInitialPositions() {
    return this.equals(PieceColor.WHITE) ? List.of(new Position("a1"), new Position("h1")) :
        List.of(new Position("a8"), new Position("h8"));
  }

  public Rank pawnStartRank() {
    return this == PieceColor.WHITE ? Rank.TWO : Rank.SEVEN;
  }

  public List<Position> pawnInitialPositions() {
    var positions = new ArrayList<Position>();
    for (var file : File.values()) {
      positions.add(new Position(file, this.pawnStartRank()));
    }
    return positions;
  }

  public Direction pawnWalkDirection() {
    return this == PieceColor.WHITE ? Direction.VERTICAL_UP : Direction.VERTICAL_DOWN;
  }
}
