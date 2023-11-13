package chess.domain.play;

import chess.domain.board.PieceColor;

public record PlayDto(PieceColor pieceColor, String algebraicNotation) {

}
