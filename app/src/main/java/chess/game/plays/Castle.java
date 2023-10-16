package chess.game.plays;

import chess.game.board.Board;
import chess.game.board.pieces.Color;
import chess.game.board.pieces.King;
import chess.game.board.pieces.Piece;
import chess.game.board.pieces.PieceType;
import chess.game.grid.Direction;
import chess.game.grid.Path;
import chess.game.grid.Position;
import chess.game.plays.validation.CantCastleOnKingThatAlreadyMoved;
import chess.game.plays.validation.CantCastleOnRookThatAlreadyMoved;
import chess.game.plays.validation.CantCastleOverOccupiedSquares;
import chess.game.plays.validation.CantCastleToInvalidPosition;
import chess.game.plays.validation.CantCastleWhileInCheck;
import chess.game.plays.validation.CantCastleWhilePassingThroughCheck;
import chess.game.plays.validation.PlayValidationError;
import java.util.Iterator;

public record Castle(Color color, Position to) implements Play {

  private Position getKingPosition(Board state) throws CantCastleOnKingThatAlreadyMoved {
    var kingPosition = King.initialPositionFor(this.color);
    var kingOptional = state.getPieceAt(kingPosition);
    if (kingOptional.isEmpty()
        || kingOptional.get().getSpecification().pieceType() != PieceType.KING
        || kingOptional.get().getSpecification().color() != this.color) {
      throw new CantCastleOnKingThatAlreadyMoved(this.color);
    }
    return kingPosition;
  }

  private Position getRookPosition(Board state)
      throws CantCastleToInvalidPosition, CantCastleOnRookThatAlreadyMoved {
    if ((this.color == Color.WHITE && !this.to.equals(new Position("a1"))
        && !new Position("h1").equals(this.to))
        || (this.color == Color.BLACK && !this.to.equals(new Position("a8"))
        && !new Position("h8").equals(this.to))) {
      throw new CantCastleToInvalidPosition(color, this.to);
    }

    var rookOptional = state.getPieceAt(this.to);
    if (rookOptional.isEmpty()
        || rookOptional.get().getSpecification().pieceType() != PieceType.ROOK
        || rookOptional.get().getSpecification().color() != this.color) {
      throw new CantCastleOnRookThatAlreadyMoved(this.color, this.to);
    }

    return this.to;
  }

  public Runnable validateAndGetAction(Board board)
      throws PlayValidationError {
    final Position rookPosition = this.getRookPosition(board);
    final Position kingPosition = this.getKingPosition(board);
    final Direction direction = kingPosition.directionTo(rookPosition).orElseThrow();

    final Path kingPath = new Path(kingPosition, direction, kingPosition.stepsTo(rookPosition) - 1);
    final Iterator<Position> kingPathIterator = kingPath.iterator();
    final Position kingFirstStep = kingPathIterator.next();
    final Position kingSecondStep = kingPathIterator.next();

    final Piece king = board.getPieceAt(kingPosition).orElseThrow();
    final Piece rook = board.getPieceAt(rookPosition).orElseThrow();

    if (kingPath.isBlockedOn(board)) {
      throw new CantCastleOverOccupiedSquares(this.color, this.to);
    }

    if (board.isPositionThreatenedBy(kingPosition, this.color.opposite())) {
      throw new CantCastleWhileInCheck(this.color);
    }

    if (board.isPositionThreatenedBy(kingFirstStep, this.color.opposite())) {
      throw new CantCastleWhilePassingThroughCheck(this.color, this.to);
    }

    for (Play oldPlay : board.history()) {
      PlayDto oldPlayDto = oldPlay.toDto();
      if (oldPlayDto.getFrom().equals(kingPosition)) {
        throw new CantCastleOnKingThatAlreadyMoved(this.color);
      }
      if (oldPlayDto.getFrom().equals(rookPosition)) {
        throw new CantCastleOnRookThatAlreadyMoved(this.color, rookPosition);
      }
    }

    return () -> {
      board.removePieceFromSquare(kingPosition);
      board.removePieceFromSquare(rookPosition);
      board.placePiece(kingFirstStep, rook);
      board.placePiece(kingSecondStep, king);
    };
  }

  public Color getPlayerColor() {
    return this.color;
  }

  public PlayDto toDto() {
    return new PlayDto() {
      public PlayName getName() {
        return PlayName.CASTLE;
      }

      public Position getFrom() {
        return King.initialPositionFor(Castle.this.color);
      }

      public Position getTo() {
        return Castle.this.to;
      }
    };
  }
}
