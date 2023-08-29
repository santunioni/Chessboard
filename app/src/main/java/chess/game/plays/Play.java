package chess.game.plays;

import chess.game.board.BoardState;
import chess.game.pieces.Color;

public interface Play {
    void actUpon(BoardState boardState) throws IlegalPlay;

    boolean isLegal(BoardState boardState);

    Color getPlayerColor();

    PlayDTO toDTO();
}
