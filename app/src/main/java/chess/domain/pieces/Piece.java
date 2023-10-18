package chess.domain.pieces;

import chess.domain.board.ReadonlyBoard;
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


  public abstract boolean couldMoveToIfEmpty(Position position);

  public boolean threatens(Position position) {
    return this.couldMoveToIfEmpty(position);
  }

  protected abstract Set<Play> getSuggestedPlaysIncludingPossiblyInvalid();

  public Set<Play> getSuggestedPlays() {
    return this.getSuggestedPlaysIncludingPossiblyInvalid().stream()
        .filter(play -> play.isLegalOn(this.board))
        .collect(Collectors.toUnmodifiableSet());
  }

  public Color color() {
    return this.specification.color();
  }
}
