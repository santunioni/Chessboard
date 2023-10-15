package chess.game.board.pieces;

import chess.game.board.Board;
import chess.game.board.PlayHistory;
import chess.game.board.ReadonlyBoard;
import chess.game.grid.Position;
import chess.game.plays.Play;
import chess.game.plays.validation.PlayValidationError;
import chess.game.rules.PlayValidator;
import chess.game.rules.validation.IlegalPlay;
import java.util.Objects;
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

  public boolean couldCaptureEnemyAt(Position position) {
    return this.couldMoveToIfEmpty(position);
  }

  public boolean isEnemyOf(Piece piece) {
    return this.getSpecification().color().oppositeOf(piece.specification.color());
  }


  protected abstract Set<Play> getPossiblePlays();

  public Set<Play> getPlays(Board state, PlayHistory history) {
    return this.getPossiblePlays().stream().filter(play -> {
      try {
        new PlayValidator(state, history).validateNextPlay(play);
        return true;
      } catch (IlegalPlay | PlayValidationError ignored) {
        return false;
      }
    }).collect(Collectors.toSet());
  }
}
