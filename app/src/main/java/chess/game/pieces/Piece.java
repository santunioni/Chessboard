package chess.game.pieces;

import chess.game.board.Board;
import chess.game.board.BoardPlacement;
import chess.game.board.PlayHistory;
import chess.game.grid.Position;
import chess.game.plays.Play;
import chess.game.plays.validation.PlayValidationError;
import chess.game.rules.PlayValidatorAgainstAllChessRules;
import chess.game.rules.validation.IlegalPlay;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Piece implements PieceProperties {

  private final Color color;
  private final Type type;
  protected BoardPlacement board;

  public Piece(Color color, Type type) {
    this.color = color;
    this.type = type;
  }

  public void placeInBoard(BoardPlacement boardPlacement) {
    this.board = boardPlacement;
  }

  public abstract Piece copy();

  public Color getColor() {
    return this.color;
  }

  public Type getType() {
    return this.type;
  }

  public abstract boolean couldMoveToIfEmpty(Position position);

  public boolean couldCaptureEnemyAt(Position position) {
    return this.couldMoveToIfEmpty(position);
  }

  public boolean isEnemyOf(Piece piece) {
    return this.color.opposite() == piece.color;
  }

  public PieceProperties toProperties() {
    return new PieceProperties() {
      public Color getColor() {
        return Piece.this.getColor();
      }

      public Type getType() {
        return Piece.this.getType();
      }
    };
  }

  protected abstract Set<Play> getPossiblePlays();

  public Set<Play> getPlays(Board state, PlayHistory history) {
    return this.getPossiblePlays().stream()
        .filter(play -> {
          try {
            PlayValidatorAgainstAllChessRules.validateNextPlay(state, history, play);
            return true;
          } catch (IlegalPlay | PlayValidationError ignored) {
            return false;
          }
        }).collect(Collectors.toSet());
  }
}
