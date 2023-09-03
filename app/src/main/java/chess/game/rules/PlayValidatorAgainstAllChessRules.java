package chess.game.rules;

import chess.game.board.BoardHistory;
import chess.game.board.BoardState;
import chess.game.plays.Play;
import chess.game.plays.validation.PlayValidationError;
import chess.game.rules.validation.IlegalPlay;

public class PlayValidatorAgainstAllChessRules {
    public static void validateNextPlay(BoardState state, BoardHistory history, Play play) throws PlayValidationError, IlegalPlay {
        CantPlayWhenNotYourTurn.validateHistoryBeforePlay(history, play);

        var stateCopy = state.copy();
        play.actOn(stateCopy);

        CantLetOwnKingInCheck.validateStateAfterPlay(stateCopy, play);
    }
}
