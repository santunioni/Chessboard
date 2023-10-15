package chess.game.board;

import chess.game.board.pieces.Piece;
import chess.game.grid.Position;
import chess.game.plays.Play;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public class Board implements ReadonlyBoard {
  private final UUID id;
  private final HashMap<Position, Piece> currentPositionToPiece;
  private final List<Play> stack;

  public Board(UUID id, HashMap<Position, Piece> currentPositionToPiece, List<Play> stack) {
    this.id = id;
    this.currentPositionToPiece = currentPositionToPiece;
    this.stack = stack;
  }

  public Board() {
    this.id = UUID.randomUUID();
    this.currentPositionToPiece = new HashMap<>();
    this.stack = new ArrayList<>();
  }

  public Optional<Piece> getPieceAt(Position position) {
    return Optional.ofNullable(currentPositionToPiece.get(position));
  }

  public Stream<Piece> getPieces() {
    return this.currentPositionToPiece.values().stream();
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

      @Override
      public Stream<Piece> getPieces() {
        return Board.this.getPieces();
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

  public boolean equals(Object that) {
    if (that instanceof Board) {
      return this.id.equals(((Board) that).id);
    }
    return false;
  }
}
