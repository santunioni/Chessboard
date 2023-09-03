package chess.game.plays;

import chess.game.board.BoardHistory;
import chess.game.board.BoardState;
import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.plays.validation.PlayValidationError;

public class Castle implements Play {

    private final Color color;
    private final Position to;

    public Castle(Color color, Position to) {
        this.color = color;
        this.to = to;
    }

    private Position getFrom() {
        return new Position(this.getPlayerColor() == Color.WHITE ? "e1" : "e8");
    }

    public void actOn(BoardState boardState, BoardHistory boardHistory) throws PlayValidationError {

    }

    public boolean isValid(BoardState boardState, BoardHistory boardHistory) {
        return false;
    }


    public Color getPlayerColor() {
        return this.color;
    }

    public PlayDTO toDTO() {
        return new PlayDTO() {
            public PlayName getName() {
                return PlayName.CASTLE;
            }

            public Position getFrom() {
                return Castle.this.getFrom();
            }

            public Position getTo() {
                return Castle.this.to;
            }
        };
    }
}
