package chess.game.pieces;

import chess.game.board.BoardHistory;
import chess.game.board.BoardPlacement;
import chess.game.board.BoardState;
import chess.game.grid.Position;
import chess.game.plays.Play;
import chess.game.plays.validation.PlayValidationError;
import chess.game.rules.PlayValidatorAgainstAllChessRules;
import chess.game.rules.validation.IlegalPlay;
import java.util.HashSet;
import java.util.Set;

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

  public Set<Play> getPlays(BoardState state, BoardHistory history) {
    var plays = new HashSet<Play>();
    for (var play : this.getPossiblePlays()) {
      try {
        PlayValidatorAgainstAllChessRules.validateNextPlay(state.copy(), history.copy(), play);
        plays.add(play);
      } catch (IlegalPlay | PlayValidationError ignored) {
      }
    }
    return plays;
  }
}
