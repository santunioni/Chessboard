package chess.ui.plays;

import chess.domain.board.PieceColor;
import chess.domain.board.PieceType;
import chess.domain.grid.Position;
import chess.domain.grid.Rank;
import chess.domain.play.CastleSide;
import chess.domain.play.GenericPlayFactory;
import chess.domain.play.PlayDto;
import chess.domain.play.PlayFactoryFromAlgebraicNotation;
import chess.domain.play.validation.PlayValidationError;
import chess.ui.grid.GridLayer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;
import javax.swing.JLabel;

public class PlayComponentFactory {
  private final GridLayer grid;
  private final PlayFactoryFromAlgebraicNotation<PlayComponent> playFactoryFromAlgebraicNotation =
      new PlayFactoryFromAlgebraicNotation<>(new GenericPlayFactory<>() {
        private PlayComponent createSimplePlayUi(Position highlightPosition) {
          PlayComponent
              playComponent = new PlayComponent(grid.getPositionRectangle(highlightPosition, 0.8));
          playComponent.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
              playComponent.select();
            }
          });
          return playComponent;
        }

        public PlayComponent createMove(PieceType type, PieceColor color, Position from,
                                        Position to) {
          return this.createSimplePlayUi(to);
        }

        public PlayComponent createCapture(PieceType type, PieceColor color, Position from,
                                           Position to) {
          return this.createSimplePlayUi(to);
        }

        public PlayComponent createPromotionAfterMove(PieceColor color, Position from, Position to,
                                                      PieceType promotedToType) {
          return null;
        }

        public PlayComponent createPromotionAfterCapture(PieceColor color, Position from,
                                                         Position to,
                                                         PieceType promotedToType) {
          return this.createPromotionAfterMove(color, from, to, promotedToType);
        }

        public PlayComponent createCastle(PieceColor color, CastleSide castleSide) {
          final Rank rank = color == PieceColor.WHITE ? Rank.ONE : Rank.EIGHT;
          return this.createSimplePlayUi(new Position(castleSide.toRookFile(), rank));
        }

        public PlayComponent createEnPassant(PieceColor color, Position from, Position to) {
          return this.createSimplePlayUi(to);
        }
      });
  private SelectedPlaySubscriber selectedPlaySubscriber = (playDto) -> {
  };

  public PlayComponentFactory(GridLayer grid) {
    this.grid = grid;
  }

  public void setSelectedPlaySubscriber(SelectedPlaySubscriber selectedPlaySubscriber) {
    this.selectedPlaySubscriber = selectedPlaySubscriber;
  }

  public Optional<JLabel> createPlayUiFromDto(PlayDto play) {
    try {
      PlayComponent playComponent =
          this.playFactoryFromAlgebraicNotation.createPlayFromLongAlgebraicNotation(
              play.pieceColor(),
              play.algebraicNotation());
      playComponent.onSelectedPlay(() -> this.selectedPlaySubscriber.notifySelectedPlay(play));
      return Optional.of(playComponent);
    } catch (PlayValidationError e) {
      System.out.println(e.getMessage());
      return Optional.empty();
    }
  }
}
