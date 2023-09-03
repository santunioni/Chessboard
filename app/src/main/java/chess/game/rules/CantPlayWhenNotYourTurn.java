package chess.game.rules;

import chess.game.board.BoardHistory;
import chess.game.plays.Play;
import chess.game.rules.validation.NotYourTurn;

public class CantPlayWhenNotYourTurn {
    public static void validateHistoryBeforePlay(BoardHistory history, Play play) throws NotYourTurn {
        if (play.getPlayerColor() != history.nextTurnPlayerColor()) {
            throw new NotYourTurn(play);
        }
    }
}
