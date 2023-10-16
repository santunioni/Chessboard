package chess.game.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.game.board.pieces.Color;
import chess.game.board.pieces.Piece;
import chess.game.board.pieces.PieceFactory;
import chess.game.board.pieces.PieceType;
import chess.game.grid.Position;
import chess.game.plays.Capture;
import chess.game.plays.Move;
import chess.game.plays.Play;
import chess.game.plays.validation.PlayValidationError;
import chess.game.rules.validation.IlegalPlay;
import com.google.common.collect.HashBiMap;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameControllerTest {
  private final PieceFactory pieceFactory = new PieceFactory();
  private Board board;
  private List<Play> stack;
  private GameController controller;

  @BeforeEach
  void setUp() {
    this.stack = new ArrayList<>();
    this.board = new Board(UUID.randomUUID().toString(), HashBiMap.create(), stack);
    this.controller = new GameController(this.board);
  }

  private void forwardToBlackTurn() {
    this.stack.add(new Move(Color.WHITE, new Position("h7"), new Position("h8")));
  }

  @Test
  void shouldAllowWhiteToMoveOnItsTurn() throws PlayValidationError, IlegalPlay {
    // Given
    this.board.placePiece("a2", this.pieceFactory.createRooks(Color.BLACK).get(0));
    this.board.placePiece("b1", this.pieceFactory.createRooks(Color.WHITE).get(0));

    // When
    var move = new Move(Color.WHITE, new Position("b1"), new Position("a1"));
    var moveDto = move.toDto();
    this.controller.makePlay(moveDto);

    // Then
    Piece pieceAtA1 = this.board.getPieceAt(new Position("a1")).orElseThrow();
    assertEquals(Color.WHITE, pieceAtA1.getSpecification().color());
    assertEquals(PieceType.ROOK, pieceAtA1.getSpecification().pieceType());
  }


  @Test
  void shouldAllowBlackToCaptureWhiteOnItsTurn() throws PlayValidationError, IlegalPlay {
    // Given
    forwardToBlackTurn();
    this.board.placePiece("a1", this.pieceFactory.createRooks(Color.WHITE).get(0));
    this.board.placePiece("a2", this.pieceFactory.createRooks(Color.BLACK).get(0));

    // When
    var capture = new Capture(Color.BLACK, new Position("a2"), new Position("a1"));
    var captureDto = capture.toDto();
    this.controller.makePlay(captureDto);

    // Then
    Piece pieceAtA1 = this.board.getPieceAt(new Position("a1")).orElseThrow();
    assertEquals(Color.BLACK, pieceAtA1.getSpecification().color());
    assertEquals(PieceType.ROOK, pieceAtA1.getSpecification().pieceType());
  }
}
