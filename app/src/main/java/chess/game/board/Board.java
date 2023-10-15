package chess.game.board;

import chess.game.board.pieces.Color;
import chess.game.board.pieces.Piece;
import chess.game.board.pieces.PieceSpecification;
import chess.game.grid.Position;
import chess.game.plays.Play;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Board implements ReadonlyBoard {
  private final HashMap<Position, Piece> currentPositionToPiece = new HashMap<>();
  private final List<Play> stack = new ArrayList<>();

  public Optional<Piece> getPieceAt(String position) {
    return this.getPieceAt(new Position(position));
  }

  public Optional<Piece> getPieceAt(Position position) {
    return Optional.ofNullable(currentPositionToPiece.get(position));
  }

  public Optional<Position> getPositionOf(Piece piece) {
    for (var entry : this.currentPositionToPiece.entrySet()) {
      if (entry.getValue().equals(piece)) {
        return Optional.of(entry.getKey());
      }
    }
    return Optional.empty();
  }

  public void placePiece(Position position, Piece piece) {
    this.removePieceFromSquare(position);
    this.currentPositionToPiece.put(position, piece);
    piece.placeInBoard(new BoardPlacement() {
      public Position getMyPosition() {
        return position;
      }

      public Optional<Piece> getPieceAt(Position position) {
        return Board.this.getPieceAt(position);
      }
    });
  }

  public void placePiece(String position, Piece piece) {
    this.placePiece(new Position(position), piece);
  }

  public void removePieceFromSquare(Position position) {
    if (this.currentPositionToPiece.containsKey(position)) {
      var piece = this.currentPositionToPiece.get(position);
      this.currentPositionToPiece.remove(position);
      piece.placeInBoard(null);
    }
  }

  public Board copy() {
    var newState = new Board();
    this.currentPositionToPiece.forEach(
        ((position, piece) -> newState.placePiece(position, piece.copy())));
    return newState;
  }

  public List<Position> findPositionsWithPiece(PieceSpecification piece) {
    var positions = new ArrayList<Position>();
    for (var entry : this.currentPositionToPiece.entrySet()) {
      if (entry.getValue().getSpecification().equals(piece)) {
        positions.add(entry.getKey());
      }
    }
    return positions;
  }

  public List<Piece> getPiecesOf(Color player) {
    List<Piece> pieces = new ArrayList<>();
    for (var entry : this.currentPositionToPiece.entrySet()) {
      if (entry.getValue().getSpecification().color().equals(player)) {
        pieces.add(entry.getValue());
      }
    }
    return pieces;
  }
}
