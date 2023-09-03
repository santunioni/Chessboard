package chess.game.board;

import chess.game.grid.Position;
import chess.game.pieces.Piece;
import chess.game.pieces.PieceProperties;
import chess.game.plays.Play;
import chess.game.plays.PlayDTO;
import chess.game.plays.PlayDTOToPlayMapper;
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
public class BoardController {
    private final BoardState boardState;
    private final BoardHistory boardHistory;

    public BoardController(BoardState boardState, BoardHistory boardHistory) {
        this.boardState = boardState;
        this.boardHistory = boardHistory;
    }

    public Optional<PieceProperties> getPieceAt(Position position) {
        Optional<Piece> pieceOptional = this.boardState.getPieceAt(position);
        if (pieceOptional.isPresent()) {
            var piece = pieceOptional.get();
            return Optional.of(piece.toProperties());
        }
        return Optional.empty();
    }

    public List<PlayDTO> getPlaysFor(Position position) {
        var playingPieceOptional = this.boardState.getPieceAt(position);
        return playingPieceOptional.map(piece -> piece.getPlays(this.boardState, this.boardHistory)).orElse(new HashSet<>()).stream().map(Play::toDTO).collect(Collectors.toList());
    }

    public void makePlay(PlayDTO playDTO) throws PlayValidationError, IlegalPlay {
        this.makePlay(new PlayDTOToPlayMapper(this.boardState).createPlayFromDTO(playDTO));
    }

    public void makePlay(Play play) throws PlayValidationError, IlegalPlay {
        PlayValidatorAgainstAllChessRules.validateNextPlay(this.boardState.copy(), this.boardHistory.copy(), play);
        play.actOn(this.boardState, this.boardHistory);
    }
}
