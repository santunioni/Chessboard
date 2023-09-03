package chess.game.plays;

import chess.game.board.BoardHistory;
import chess.game.board.BoardState;
import chess.game.pieces.Color;
import chess.game.plays.validation.PlayValidationError;

public interface Play {
    void actOn(BoardState boardState, BoardHistory boardHistory) throws PlayValidationError;

    boolean isValid(BoardState boardState, BoardHistory boardHistory);

    Color getPlayerColor();

    PlayDTO toDTO();
}
