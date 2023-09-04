package chess.game.pieces;

public interface PieceProperties {
  Color getColor();

  Type getType();

  default boolean isSameTypeAndColor(PieceProperties that) {
    return this.getColor() == that.getColor() && this.getType() == that.getType();
  }

}
