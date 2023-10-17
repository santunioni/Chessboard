package chess.game.board.pieces;

import chess.game.assertions.IsPlayLegalAssertion;
import chess.game.board.Board;
import chess.game.board.ReadonlyBoard;
import chess.game.grid.Position;
import chess.game.plays.Play;
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

  public boolean isEnemyOf(Piece piece) {
    return this.getSpecification().color().oppositeOf(piece.specification.color());
  }

  protected abstract Set<Play> getPossiblePlays();

  public Set<Play> getPlays(Board board) {
    return this.getPossiblePlays().stream()
        .filter(play -> new IsPlayLegalAssertion(play).test(board))
        .collect(Collectors.toSet());
  }
}
