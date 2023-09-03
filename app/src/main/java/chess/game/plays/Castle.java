package chess.game.plays;

import chess.game.board.BoardHistory;
import chess.game.board.BoardState;
import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.pieces.Piece;
import chess.game.pieces.Type;
import chess.game.plays.validation.CantCastleOnKingThatAlreadyMoved;
import chess.game.plays.validation.CantCastleOnRookNotInitialPosition;
import chess.game.plays.validation.CantCastleOnRookThatAlreadyMoved;
import chess.game.plays.validation.PlayValidationError;

public class Castle implements Play {

    private final Color color;
    private final Position to;

    public Castle(Color color, Position to) {
        this.color = color;
        this.to = to;
    }

    private Position getKingPosition() {
        return new Position(this.getPlayerColor() == Color.WHITE ? "e1" : "e8");
    }

    private Position getRookPosition() throws CantCastleOnRookNotInitialPosition {
        if ((this.color == Color.WHITE && !this.to.equals(new Position("a1")) && !new Position("f1").equals(this.to)) ||
                (this.color == Color.BLACK && !this.to.equals(new Position("a8")) && !new Position("f8").equals(this.to))) {
            throw new CantCastleOnRookNotInitialPosition(color, to);
        }
        return this.to;
    }

    private Piece getRook(BoardState state) throws CantCastleOnRookThatAlreadyMoved, CantCastleOnRookNotInitialPosition {
        var rookOptional = state.getPieceAt(this.getRookPosition());
        if (rookOptional.isEmpty() || rookOptional.get().getType() != Type.ROOK || rookOptional.get().getColor() != this.color) {
            throw new CantCastleOnRookThatAlreadyMoved(this.color, to);
        }
        return rookOptional.get();
    }

    private Piece getKing(BoardState state) throws CantCastleOnKingThatAlreadyMoved {
        var kingOptional = state.getPieceAt(this.getKingPosition());
        if (kingOptional.isEmpty() || kingOptional.get().getType() != Type.KING || kingOptional.get().getColor() != this.color) {
            throw new CantCastleOnKingThatAlreadyMoved(this.color);
        }
        return kingOptional.get();
    }


    public void actOn(BoardState boardState, BoardHistory boardHistory) throws PlayValidationError {
        var rook = this.getRook(boardState);
        var king = this.getKing(boardState);


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
                return Castle.this.getKingPosition();
            }

            public Position getTo() {
                return Castle.this.to;
            }
        };
    }
}
