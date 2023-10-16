package chess.game.board;

import chess.game.board.pieces.Piece;
import chess.game.board.pieces.PieceSpecification;
import chess.game.grid.Position;
import chess.game.plays.Play;
import chess.game.plays.PlayDto;
import chess.game.plays.PlayDtoToPlayMapper;
import chess.game.plays.validation.PlayValidationError;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * The only way that ui should interact with the chess game.
 */
public class GameController {
  private final BoardRepository boardRepository;

  public GameController(BoardRepository boardRepository) {
    this.boardRepository = boardRepository;
  }

  public String newGame() {
    var board = this.boardRepository.createNewBoardInitializer().placeAll().getBoard();
    this.boardRepository.saveBoard(board);
    return board.getId();
  }

  public Optional<PieceSpecification> getPieceAt(String boardId, Position position) {
    var board = this.boardRepository.getBoard(boardId);
    return board.getPieceAt(position).map(Piece::getSpecification);
  }

  public List<PlayDto> getPlaysFor(String boardId, Position position) {
    var board = this.boardRepository.getBoard(boardId);
    return board.getPieceAt(position)
        .map(piece -> piece.getPlays(board))
        .orElse(new HashSet<>()).stream().map(Play::toDto).toList();
  }

  public void makePlay(String boardId, PlayDto playDto) throws PlayValidationError {
    var board = this.boardRepository.getBoard(boardId);
    var play = new PlayDtoToPlayMapper(board).createPlayFromDto(playDto);
    board.makePlay(play);
  }
}
