package chess.game.plays;

import chess.game.board.BoardHistory;
import chess.game.board.BoardState;
import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.pieces.Type;
import chess.game.plays.validation.*;

import static chess.game.plays.PlayFunctions.isPositionThreatenedBy;

public class Castle implements Play {

    private final Color color;
    private final Position to;

    public Castle(Color color, Position to) {
        this.color = color;
        this.to = to;
    }

    private Position getKingPosition(BoardState state) throws CantCastleOnKingThatAlreadyMoved {
        var kingPosition = new Position(this.getPlayerColor() == Color.WHITE ? "e1" : "e8");
        var kingOptional = state.getPieceAt(kingPosition);
        if (kingOptional.isEmpty() || kingOptional.get().getType() != Type.KING || kingOptional.get().getColor() != this.color) {
            throw new CantCastleOnKingThatAlreadyMoved(this.color);
        }
        return kingPosition;
    }

    private Position getRookPosition(BoardState state) throws CantCastleToInvalidPosition, CantCastleOnRookThatAlreadyMoved {
        if ((this.color == Color.WHITE && !this.to.equals(new Position("a1")) && !new Position("h1").equals(this.to)) || (this.color == Color.BLACK && !this.to.equals(new Position("a8")) && !new Position("h8").equals(this.to))) {
            throw new CantCastleToInvalidPosition(color, this.to);
        }

        var rookOptional = state.getPieceAt(this.to);
        if (rookOptional.isEmpty() || rookOptional.get().getType() != Type.ROOK || rookOptional.get().getColor() != this.color) {
            throw new CantCastleOnRookThatAlreadyMoved(this.color, this.to);
        }

        return this.to;
    }

    public void actOn(BoardState boardState, BoardHistory boardHistory) throws PlayValidationError {
        var rookPosition = this.getRookPosition(boardState);
        var kingPosition = this.getKingPosition(boardState);

        for (var play : boardHistory) {
            PlayDTO playDTO = play.toDTO();
            if (playDTO.getFrom().equals(kingPosition)) {
                throw new CantCastleOnKingThatAlreadyMoved(this.color);
            }
            if (playDTO.getFrom().equals(rookPosition)) {
                throw new CantCastleOnRookThatAlreadyMoved(this.color, rookPosition);
            }
        }

        if (isPositionThreatenedBy(boardState, kingPosition, this.color.opposite())) {
            throw new CantCastleWhileInCheck(this.color);
        }
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
                return new Position(Castle.this.color == Color.WHITE ? "e1" : "e8");
            }

            public Position getTo() {
                return Castle.this.to;
            }
        };
    }
}
