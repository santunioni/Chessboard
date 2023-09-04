package chess.game.pieces;

/**
 * Represents the side of the player. (White or Black)
 */
public enum Color {
  WHITE,
  BLACK;

  public Color opposite() {
    return this == WHITE ? BLACK : WHITE;
  }
}
