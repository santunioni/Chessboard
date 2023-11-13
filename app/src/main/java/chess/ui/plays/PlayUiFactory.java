package chess.ui.plays;

import chess.domain.grid.Position;
import chess.domain.grid.Rank;
import chess.domain.pieces.Color;
import chess.domain.pieces.PieceType;
import chess.domain.plays.CastleSide;
import chess.domain.plays.GenericPlayFactory;
import chess.domain.plays.PlayDto;
import chess.domain.plays.PlayFactoryFromAlgebraicNotation;
import chess.domain.plays.validation.PlayValidationError;
import chess.ui.grid.GridUiLayer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;
import javax.swing.JLabel;

public class PlayUiFactory {
  private final GridUiLayer grid;
  private final PlayFactoryFromAlgebraicNotation<PlayUi> playFactoryFromAlgebraicNotation =
      new PlayFactoryFromAlgebraicNotation<>(new GenericPlayFactory<>() {
        private PlayUi createSimplePlayUi(Position highlightPosition) {
          PlayUi playUi = new PlayUi(grid.getPositionRectangle(highlightPosition, 0.8));
          playUi.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
              playUi.select();
            }
          });
          return playUi;
        }

        public PlayUi createMove(PieceType type, Color color, Position from, Position to) {
          return this.createSimplePlayUi(to);
        }

        public PlayUi createCapture(PieceType type, Color color, Position from, Position to) {
          return this.createSimplePlayUi(to);
        }

        public PlayUi createPromotionAfterMove(Color color, Position from, Position to,
                                               PieceType promotedToType) {
          return null;
        }

        public PlayUi createPromotionAfterCapture(Color color, Position from, Position to,
                                                  PieceType promotedToType) {
          return this.createPromotionAfterMove(color, from, to, promotedToType);
        }

        public PlayUi createCastle(Color color, CastleSide castleSide) {
          final Rank rank = color == Color.WHITE ? Rank.ONE : Rank.EIGHT;
          return this.createSimplePlayUi(new Position(castleSide.toRookFile(), rank));
        }

        public PlayUi createEnPassant(Color color, Position from, Position to) {
          return this.createSimplePlayUi(to);
        }
      });
  private SelectedPlaySubscriber selectedPlaySubscriber = (playDto) -> {
  };

  public PlayUiFactory(GridUiLayer grid) {
    this.grid = grid;
  }

  public void subscribeToSelectedPlay(SelectedPlaySubscriber selectedPlaySubscriber) {
    this.selectedPlaySubscriber = selectedPlaySubscriber;
  }

  public Optional<JLabel> createPlayUiFromDto(PlayDto play) {
    try {
      PlayUi playUi =
          this.playFactoryFromAlgebraicNotation.createPlayFromLongAlgebraicNotation(play.color(),
              play.algebraicNotation());
      playUi.onSelectedPlay(() -> this.selectedPlaySubscriber.notifySelectedPlay(play));
      return Optional.of(playUi);
    } catch (PlayValidationError e) {
      System.out.println(e.getMessage());
      return Optional.empty();
    }
  }
}
