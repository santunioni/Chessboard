package chess.domain.pieces;

import chess.domain.assertions.PlayLegalityAssertion;
import chess.domain.board.ReadonlyBoard;
import chess.domain.grid.Position;
import chess.domain.move.MovePattern;
import chess.domain.move.MovePatternSelector;
import chess.domain.plays.Play;
import java.util.Set;
import java.util.stream.Collectors;

public class Piece {

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

  public Piece copy() {
    return new Piece(this.idInBoard(), this.color(), this.type());
  }

  public boolean couldMoveToIfEmpty(Position position) {
    return this.movePattern().couldMoveToIfEmpty(this.currentPosition(),
        position,
        this.board);
  }

  public boolean threatens(Position position) {
    return this.movePattern().threatens(this.currentPosition(), position,
        this.board);
  }

  public Set<Play> getSuggestedPlays() {
    return this.movePattern().getSuggestedPlays(this.currentPosition(),
            this.board)
        .stream()
        .filter(play -> new PlayLegalityAssertion(play).test(this.board))
        .collect(Collectors.toUnmodifiableSet());
  }

  private MovePattern movePattern() {
    return MovePatternSelector.selectForPieceType(this.color(), this.type());
  }

  public Color color() {
    return this.specification.color();
  }

  public PieceType type() {
    return this.specification.pieceType();
  }
}
