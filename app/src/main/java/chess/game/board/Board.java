package chess.game.board;

import chess.game.assertions.IsPlayLegalAssertion;
import chess.game.board.pieces.Piece;
import chess.game.grid.Position;
import chess.game.plays.Play;
import chess.game.plays.validation.PlayValidationError;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public class Board implements ReadonlyBoard {
  private final String id;
  private final BiMap<Position, Piece> currentPositionToPiece;
  private final List<Play> stack;

  public Board(String id, BiMap<Position, Piece> currentPositionToPiece, List<Play> stack) {
    this.id = id;
    this.currentPositionToPiece = currentPositionToPiece;
    this.stack = stack;
  }

  public Board() {
    this.id = UUID.randomUUID().toString();
    this.currentPositionToPiece = HashBiMap.create();
    this.stack = new ArrayList<>();
  }

  public String getId() {
    return id;
  }

  public Optional<Piece> getPieceAt(Position position) {
    return Optional.ofNullable(currentPositionToPiece.get(position));
  }

  public Stream<Map.Entry<Position, Piece>> getPieces() {
    return this.currentPositionToPiece.entrySet().stream();
  }

  public void placePiece(Position position, Piece piece) {
    this.removePieceFromSquare(position);
    this.currentPositionToPiece.put(position, piece);
    piece.placeInBoard(this);
  }

  public void placePiece(String position, Piece piece) {
    this.placePiece(new Position(position), piece);
  }

  public void removePieceFromSquare(Position position) {
    Optional.ofNullable(this.currentPositionToPiece.remove(position))
        .ifPresent(piece -> piece.placeInBoard(null));
  }

  public Position getPositionOf(Piece piece) {
    return this.currentPositionToPiece.inverse().get(piece);
  }

  private Board copy() {
    BiMap<Position, Piece> copiedState = HashBiMap.create();
    this.currentPositionToPiece.forEach(
        (position, piece) -> copiedState.put(position, piece.copy()));

    Board copiedBoard =
        new Board(UUID.randomUUID().toString(), copiedState, new ArrayList<>(this.stack));

    copiedState.forEach((position, piece) -> piece.placeInBoard(copiedBoard));

    return copiedBoard;
  }

  public boolean equals(Object that) {
    if (that instanceof Board) {
      return this.id.equals(((Board) that).id);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.id.hashCode();
  }

  public ReadonlyBoard simulate(Play play) throws PlayValidationError {
    Board copy = this.copy();
    play.actOn(copy);
    copy.stack.add(play);
    return copy;
  }

  public void makePlay(Play play) throws PlayValidationError {
    if (new IsPlayLegalAssertion(play).test(this)) {
      play.actOn(this);
      this.stack.add(play);
    } else {
      throw new PlayValidationError("Invalid play");
    }
  }

  public Optional<Play> getLastPlay() {
    if (!this.stack.isEmpty()) {
      return Optional.of(this.stack.get(this.stack.size() - 1));
    }
    return Optional.empty();
  }

  public Iterable<Play> history() {
    return this.stack;
  }
}
