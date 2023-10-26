package chess.domain.pieces;

import chess.domain.assertions.PlayLegalityAssertion;
import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.Direction;
import chess.domain.grid.Position;
import chess.domain.plays.Play;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Piece {

  /**
   * The initial position of the piece. Serves at the piece's identifier in the board.
   */
  private final Position initialPosition;
  private final PieceSpecification specification;
  protected ReadonlyBoard board;

  public Piece(Position initialPosition, Color color, PieceType pieceType) {
    this.initialPosition = initialPosition;
    this.specification = new PieceSpecification(color, pieceType);
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

  public abstract Piece copy();


  public boolean couldMoveToIfEmpty(Position position) {
    return this.movePattern().couldMoveToIfEmpty(position);
  }

  public boolean threatens(Position position) {
    return this.movePattern().threatens(position);
  }

  public Set<Play> getSuggestedPlays() {
    return this.movePattern().getSuggestedPlays().stream()
        .filter(play -> new PlayLegalityAssertion(play).test(this.board))
        .collect(Collectors.toUnmodifiableSet());
  }

  public Color color() {
    return this.specification.color();
  }

  public PieceType type() {
    return this.specification.pieceType();
  }

  private MovePattern movePattern() {
    return switch (this.type()) {
      case PAWN -> new PawnMovePattern(this);
      case ROOK -> new DirectionalMovePattern(
          Set.of(Direction.VERTICAL_UP, Direction.VERTICAL_DOWN, Direction.HORIZONTAL_LEFT,
              Direction.HORIZONTAL_RIGHT), this);
      case KNIGHT -> new KnightMovePattern(this);
      case BISHOP -> new DirectionalMovePattern(Direction.diagonals(), this);
      case QUEEN -> new DirectionalMovePattern(Direction.allDirections(), this);
      case KING -> new KingMovePattern(this);
    };
  }
}
