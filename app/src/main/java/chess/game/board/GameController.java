package chess.game.board;

import chess.game.grid.Position;
import chess.game.pieces.Piece;
import chess.game.pieces.PieceProperties;
import chess.game.plays.Play;
import chess.game.plays.PlayDto;
import chess.game.plays.PlayDtoToPlayMapper;
import chess.game.plays.validation.PlayValidationError;
import chess.game.rules.PlayValidatorAgainstAllChessRules;
import chess.game.rules.validation.IlegalPlay;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The only way that ui should interact with the chess game.
 */
public class GameController {
  private final Board board;
  private final PlayHistory history;

  public GameController(Board board, PlayHistory history) {
    this.board = board;
    this.history = history;
  }

  public Optional<PieceProperties> getPieceAt(Position position) {
    Optional<Piece> pieceOptional = this.board.getPieceAt(position);
    if (pieceOptional.isPresent()) {
      var piece = pieceOptional.get();
      return Optional.of(piece.toProperties());
    }
    return Optional.empty();
  }

  public List<PlayDto> getPlaysFor(Position position) {
    var playingPieceOptional = this.board.getPieceAt(position);
    return playingPieceOptional.map(piece -> piece.getPlays(this.board, this.history))
        .orElse(new HashSet<>()).stream().map(Play::toDto).collect(Collectors.toList());
  }

  public void makePlay(PlayDto playDto) throws PlayValidationError, IlegalPlay {
    var play = new PlayDtoToPlayMapper(this.board).createPlayFromDto(playDto);
    PlayValidatorAgainstAllChessRules.validateNextPlay(this.board,
        this.history, play);
    play.actOn(this.board, this.history);
  }
}
