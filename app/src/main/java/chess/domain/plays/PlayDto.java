package chess.domain.plays;

import chess.domain.grid.Position;

public record PlayDto(PlayName playName, Position from, Position uiHighlightPosition) {

}
