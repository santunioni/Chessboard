package chess.pieces;

public interface LocatedPiece extends Piece {

    void placeInBoard(BoardPlacement boardPlacement);
}
