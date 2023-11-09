package chess.domain.pieces;

import chess.domain.assertions.PlayLegalityAssertion;
import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.Direction;
import chess.domain.grid.Position;
import chess.domain.plays.Play;
import java.util.Set;
import java.util.stream.Collectors;

public class Piece {

  /**
   * The initial position of the piece. Serves at the piece's identifier in the board.
   */
  private final Position initialPosition;
  private final PieceSpecification specification;
  private final MovePattern movePattern;
  protected ReadonlyBoard board;

  public Piece(Position initialPosition, Color color, PieceType pieceType) {
    this.initialPosition = initialPosition;
    this.specification = new PieceSpecification(color, pieceType);
    this.movePattern = selectMovePattern(this);
  }

  private static MovePattern selectMovePattern(Piece piece) {
    return switch (piece.type()) {
      case PAWN -> new PawnMovePattern(piece);
      case ROOK -> new DirectionalMovePattern(
          Set.of(Direction.VERTICAL_UP, Direction.VERTICAL_DOWN, Direction.HORIZONTAL_LEFT,
              Direction.HORIZONTAL_RIGHT), piece);
      case KNIGHT -> new KnightMovePattern(piece);
      case BISHOP -> new DirectionalMovePattern(Direction.diagonals(), piece);
      case QUEEN -> new DirectionalMovePattern(Direction.allDirections(), piece);
      case KING -> new KingMovePattern(piece);
    };
  }

  public Position getInitialPosition() {
    return initialPosition;
  }

  public Position idInBoard() {
    return this.initialPosition;
  }

  @Override
  public int hashCode() {
    return initialPosition.hashCode();
  }

  public Position currentPosition() {
    return this.board.getPositionOf(this);
  }

  public void placeInBoard(ReadonlyBoard board) {
    this.board = board;
  }

  public PieceSpecification getSpecification() {
    return specification;
  }

  public Piece copy() {
    return new Piece(this.idInBoard(), this.color(), this.type());
  }

  public boolean couldMoveToIfEmpty(Position position) {
    return this.movePattern.couldMoveToIfEmpty(position);
  }

  public boolean threatens(Position position) {
    return this.movePattern.threatens(position);
  }

  public Set<Play> getSuggestedPlays() {
    return this.movePattern.getSuggestedPlays().stream()
        .filter(play -> new PlayLegalityAssertion(play).test(this.board))
        .collect(Collectors.toUnmodifiableSet());
  }

  public Color color() {
    return this.specification.color();
  }

  public PieceType type() {
    return this.specification.pieceType();
  }
}
