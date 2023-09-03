package chess.game.rules;

import chess.game.board.BoardHistory;
import chess.game.board.BoardState;
import chess.game.plays.Play;
import chess.game.plays.validation.PlayValidationError;
import chess.game.rules.validation.IlegalPlay;
import chess.game.rules.validation.NotYourTurn;

import java.util.List;

public class PlayValidatorAgainstAllChessRules implements ChessRulesPlayValidator {
    private final BoardState boardState;
    private final BoardHistory boardHistory;
    private final List<ChessRulesPlayValidator> rules;

    public PlayValidatorAgainstAllChessRules(BoardState boardState, BoardHistory boardHistory) {
        this.boardState = boardState;
        this.boardHistory = boardHistory;
        var nextPlayerTurn = boardHistory.nextTurnPlayerColor();
        this.rules = List.of(
                play -> {
                    if (play.getPlayerColor() != nextPlayerTurn) {
                        throw new NotYourTurn(play);
                    }
                },
                new CantPutOwnKingInCheckValidation(this.boardState)
        );
    }

    public void validatePlayAgainstChessRules(Play play) throws PlayValidationError, IlegalPlay {
        play.actUpon(this.boardState);
        this.boardHistory.push(play);

        for (var rule : rules) {
            rule.validatePlayAgainstChessRules(play);
        }
    }
}
