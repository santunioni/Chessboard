package chess.game.rules;

import chess.game.plays.Play;
import chess.game.plays.validation.PlayValidationError;
import chess.game.rules.validation.IlegalPlay;

public interface ChessRulesPlayValidator {

    void validatePlayAgainstChessRules(
            Play play
    ) throws PlayValidationError, IlegalPlay;
}
