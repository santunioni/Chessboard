package chess.game.plays;

import chess.game.board.BoardState;

public interface Play {
    void actUpon(BoardState boardState) throws IlegalPlay;

    PlayDTO toDTO();
}
