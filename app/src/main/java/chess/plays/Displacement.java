package chess.plays;


import chess.board.position.Position;

public record Displacement(Position from, Position to) {

    public Displacement(String from, String to) {
        this(new Position(from), new Position(to));
    }

}
