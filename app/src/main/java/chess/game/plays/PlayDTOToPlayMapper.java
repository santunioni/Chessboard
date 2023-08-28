package chess.game.plays;

import chess.game.board.BoardState;
import chess.game.pieces.Piece;

public class PlayDTOToPlayMapper {

    private final BoardState boardState;

    public PlayDTOToPlayMapper(BoardState boardState) {
        this.boardState = boardState;
    }

    public Play createPlayFromDTO(PlayDTO playDTO) throws IlegalPlay {
        PlayName playName = playDTO.getName();
        var piece = this.boardState.getPieceAt(playDTO.getFrom());
        return switch (playName) {
            case MOVE -> new Move(piece.map(Piece::getColor).orElseThrow(), playDTO.getFrom(), playDTO.getTo());
            case CAPTURE -> new Capture(piece.map(Piece::getColor).orElseThrow(), playDTO.getFrom(), playDTO.getTo());
            default -> throw new IlegalPlay(null, "PlayName not found");
        };
    }
}
