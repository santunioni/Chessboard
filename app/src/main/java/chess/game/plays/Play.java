package chess.game.plays;

import chess.game.board.BoardHistory;
import chess.game.board.BoardState;
import chess.game.pieces.Color;
import chess.game.plays.validation.PlayValidationError;

public interface Play {
    Runnable validateAndGetRunnable(BoardState boardState, BoardHistory boardHistory) throws PlayValidationError;

    default boolean passesValidation(BoardState boardState, BoardHistory boardHistory) {
        try {
            this.validateAndGetRunnable(boardState, boardHistory);
            return true;
        } catch (PlayValidationError ignored) {
            return false;
        }
    }

    default void actOn(BoardState boardState, BoardHistory boardHistory) throws PlayValidationError {
        var action = this.validateAndGetRunnable(boardState, boardHistory);
        action.run();
    }

    Color getPlayerColor();

    PlayDTO toDTO();
}
