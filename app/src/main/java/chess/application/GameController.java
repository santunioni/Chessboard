package chess.application;

import chess.domain.board.BoardRepository;
import chess.domain.board.Piece;
import chess.domain.board.PieceSpecification;
import chess.domain.grid.Position;
import chess.domain.play.Play;
import chess.domain.play.PlayDto;
import chess.domain.play.PlayFactory;
import chess.domain.play.PlayFactoryFromAlgebraicNotation;
import chess.domain.play.validation.PlayValidationError;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The only way that ui should interact with the chess game.
 */
public class GameController {
  private final BoardRepository boardRepository;
  private final PlayFactoryFromAlgebraicNotation<Play> playFactory =
      new PlayFactoryFromAlgebraicNotation<>(new PlayFactory());

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

  public Set<PlayDto> getPlaysFor(String boardId, Position position) {
    var board = this.boardRepository.getBoard(boardId);
    return board.getPieceAt(position).map(Piece::getSuggestedPlays).orElse(new HashSet<>()).stream()
        .map(Play::toDto).collect(Collectors.toUnmodifiableSet());
  }

  public void makePlay(String boardId, PlayDto playDto) throws PlayValidationError {
    var board = this.boardRepository.getBoard(boardId);
    var play = this.playFactory.createPlayFromLongAlgebraicNotation(playDto.pieceColor(),
        playDto.algebraicNotation());
    board.makePlay(play);
  }
}
