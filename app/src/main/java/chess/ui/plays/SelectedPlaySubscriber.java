package chess.ui.plays;

import chess.domain.plays.PlayDto;

public interface SelectedPlaySubscriber {
  void notifySelectedPlay(PlayDto play);
}
