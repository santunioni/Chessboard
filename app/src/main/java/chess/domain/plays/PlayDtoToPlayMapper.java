package chess.domain.plays;

import chess.domain.board.Board;
import chess.domain.plays.validation.PlayValidationError;

public class PlayDtoToPlayMapper {

  private final Board board;

  public PlayDtoToPlayMapper(Board board) {
    this.board = board;
  }

  public Play createPlayFromDto(PlayDto playDto) throws PlayValidationError {
    PlayName playName = playDto.playName();
    var piece = this.board.getPieceAt(playDto.from());
    return switch (playName) {
      case MOVE -> new Move(piece.map(p -> p.color()).orElseThrow(), playDto.from(),
          playDto.uiHighlightPosition());
      case CAPTURE -> new Capture(piece.map(p -> p.color()).orElseThrow(), playDto.from(),
          playDto.uiHighlightPosition());
      case CASTLE -> new Castle(piece.map(p -> p.color()).orElseThrow(),
          playDto.uiHighlightPosition());
      case EN_PASSANT -> new EnPassant(piece.map(p -> p.color()).orElseThrow(),
          playDto.from(),
          playDto.uiHighlightPosition());
      default -> throw new PlayValidationError("PlayName not found");
    };
  }
}
