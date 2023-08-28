package chess.game.board;

import chess.game.pieces.Color;
import chess.game.plays.Play;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoardHistory {

    private final List<Play> stack = new ArrayList<>();

    public BoardHistory() {

    }

    public void push(Play play) {
        this.stack.add(play);
    }

    public BoardHistory copy() {
        var copy = new BoardHistory();
        copy.stack.addAll(this.stack);
        return copy;
    }

    public Optional<Play> getLastPlay() {
        if (!this.stack.isEmpty()) {
            return Optional.of(this.stack.get(this.stack.size() - 1));
        }
        return Optional.empty();
    }

    public Color nextTurnPlayerColor() {
        if (this.stack.isEmpty()) {
            return Color.WHITE;
        }
        return this.stack.get(this.stack.size() - 1).getPlayerColor().opposite();
    }
}
