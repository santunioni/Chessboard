package chess.game.board;

import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.pieces.Piece;
import chess.game.pieces.PieceProperties;
import chess.game.pieces.Type;
import chess.game.plays.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The only way that ui should interact with the chess game.
 */
public class BoardController {
    private final BoardState boardState;

    public BoardController(BoardState boardState) {
        this.boardState = boardState;
    }

    public BoardController() {
        this(new BoardStateFactory().createFreshBoardState());
    }

    public Optional<PieceProperties> getPieceAt(Position position) {
        Optional<Piece> pieceOptional = this.boardState.getPieceAt(position);
        if (pieceOptional.isPresent()) {
            var piece = pieceOptional.get();
            return Optional.of(new PieceProperties() {
                public Color getColor() {
                    return piece.getColor();
                }

                public Type getType() {
                    return piece.getType();
                }
            });
        }
        return Optional.empty();
    }

    public List<PlayDTO> getPlaysFor(Position position) {
        List<PlayDTO> plays = new ArrayList<>();

        var playingPieceOptional = this.boardState.getPieceAt(position);
        if (playingPieceOptional.isEmpty()) {
            return plays;
        }

        var playingPiece = playingPieceOptional.get();

        for (var target : Position.values()) {
            var targetPieceOptional = this.boardState.getPieceAt(target);
            if (targetPieceOptional.isEmpty()) {
                if (playingPiece.couldMoveToIfEmpty(target)) {
                    plays.add(new Move(position, target).toDTO());
                }
            } else {
                var targetPiece = targetPieceOptional.get();
                if (playingPiece.couldAttackIfOccupiedByEnemy(target) && targetPiece.isEnemyOf(playingPiece)) {
                    plays.add(new Capture(position, target).toDTO());
                }
            }
        }

        return plays;
    }

    public void makePlay(PlayDTO playDTO) throws IlegalPlay {
        Play play = DTOToPlayFactory.createPlayFromDTO(playDTO);
        play.actUpon(this.boardState);
    }
}
