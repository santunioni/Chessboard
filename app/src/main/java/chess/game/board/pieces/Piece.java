package chess.game.board.pieces;

import chess.game.board.Board;
import chess.game.board.BoardPlacement;
import chess.game.board.PlayHistory;
import chess.game.grid.Position;
import chess.game.plays.Play;
import chess.game.plays.validation.PlayValidationError;
import chess.game.rules.PlayValidator;
import chess.game.rules.validation.IlegalPlay;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Piece {

  private final PieceSpecification specification;
  protected BoardPlacement board;

  public Piece(Color color, PieceType pieceType) {
    this.specification = new PieceSpecification(color, pieceType);
  }

  public void placeInBoard(BoardPlacement boardPlacement) {
    this.board = boardPlacement;
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
    return this.getPossiblePlays().stream()
        .filter(play -> {
          try {
            new PlayValidator(state, history).validateNextPlay(play);
            return true;
          } catch (IlegalPlay | PlayValidationError ignored) {
            return false;
          }
        }).collect(Collectors.toSet());
  }
}
