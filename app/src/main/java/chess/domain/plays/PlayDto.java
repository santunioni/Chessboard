package chess.domain.plays;

import chess.domain.grid.Position;
import chess.domain.pieces.Color;

public record PlayDto(Color color, String algebraicNotation, Position uiHighlightPosition) {

}
