package chess.plays;


import chess.board.BoardState;
import chess.board.position.Position;


public record Displacement(Position from, Position to) implements ChessPlay {

    public Displacement(String from, String to) {
        this(new Position(from), new Position(to));
    }

    public void actUpon(BoardState boardState) throws IlegalPlay {
        var piece = boardState.getPieceAt(from).orElseThrow(() -> new IlegalPlay(this, "No piece at " + from));

        var targetPositionOccupation = boardState.getPieceAt(to);
        if (targetPositionOccupation.isPresent()) {
            throw new IlegalPlay(this, "Cant move to " + to + " because it is ocuppied by " + targetPositionOccupation + ".");
        }

        boardState.removePieceFromSquare(from);
        boardState.placePiece(to, piece);
    }
}
