package chess.game.plays;

import chess.game.board.Board;
import chess.game.pieces.Piece;
import chess.game.plays.validation.PlayValidationError;

public class PlayDtoToPlayMapper {

  private final Board board;

  public PlayDtoToPlayMapper(Board board) {
    this.board = board;
  }

  public Play createPlayFromDto(PlayDto playDto) throws PlayValidationError {
    PlayName playName = playDto.getName();
    var piece = this.board.getPieceAt(playDto.getFrom());
    return switch (playName) {
      case MOVE ->
          new Move(piece.map(Piece::getColor).orElseThrow(), playDto.getFrom(), playDto.getTo());
      case CAPTURE ->
          new Capture(piece.map(Piece::getColor).orElseThrow(), playDto.getFrom(), playDto.getTo());
      case CASTLE -> new Castle(piece.map(Piece::getColor).orElseThrow(), playDto.getTo());
      case EN_PASSANT -> new EnPassant(piece.map(Piece::getColor).orElseThrow(), playDto.getFrom(),
          playDto.getTo());
      default -> throw new PlayValidationError("PlayName not found");
    };
  }
}
