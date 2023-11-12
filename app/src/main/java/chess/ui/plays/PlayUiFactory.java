package chess.ui.plays;

import chess.domain.grid.Position;
import chess.domain.grid.Rank;
import chess.domain.pieces.Color;
import chess.domain.pieces.PieceType;
import chess.domain.plays.CastleSide;
import chess.domain.plays.GenericPlayFactory;
import chess.domain.plays.PlayFactoryFromAlgebraicNotation;
import chess.ui.grid.GridUiLayer;
import javax.swing.JLabel;

public class PlayUiFactory extends PlayFactoryFromAlgebraicNotation<JLabel> {

  public PlayUiFactory(GridUiLayer grid) {
    super(new GenericPlayFactory<>() {
      private JLabel createJlabelForPlay(Position highlightPosition) {
        var moveUi = new JLabel();
        moveUi.setBounds(grid.getPositionRectangle(highlightPosition, 0.8));
        moveUi.setOpaque(true);
        return moveUi;
      }

      public JLabel createMove(PieceType type, Color color, Position from, Position to) {
        return this.createJlabelForPlay(to);
      }

      public JLabel createCapture(PieceType type, Color color, Position from, Position to) {
        return this.createJlabelForPlay(to);
      }

      public JLabel createPromotionAfterMove(Color color, Position from, Position to,
                                             PieceType promotedToType) {
        return null;
      }

      public JLabel createPromotionAfterCapture(Color color, Position from, Position to,
                                                PieceType promotedToType) {
        return this.createPromotionAfterMove(color, from, to, promotedToType);
      }

      public JLabel createCastle(Color color, CastleSide castleSide) {
        final Rank rank = color == Color.WHITE ? Rank.ONE : Rank.EIGHT;
        return this.createJlabelForPlay(new Position(castleSide.toRookFile(), rank));
      }

      public JLabel createEnPassant(Color color, Position from, Position to) {
        return this.createJlabelForPlay(to);
      }
    });
  }
}
