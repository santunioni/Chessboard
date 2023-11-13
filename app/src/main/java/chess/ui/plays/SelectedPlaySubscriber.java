package chess.ui.plays;

import chess.domain.play.PlayDto;

public interface SelectedPlaySubscriber {
  void notifySelectedPlay(PlayDto play);
}
