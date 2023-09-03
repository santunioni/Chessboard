package chess.game.plays;

import chess.game.board.BoardState;
import chess.game.pieces.Color;
import chess.game.plays.validation.PlayValidationError;

public interface Play {
    void actOn(BoardState boardState) throws PlayValidationError;

    boolean isValid(BoardState boardState);

    Color getPlayerColor();

    PlayDTO toDTO();
}
