package chess.plays;

import chess.board.BoardState;
import chess.board.position.Position;
import chess.pieces.Color;
import chess.pieces.Pawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DisplacementTest {
    @Test
    void shouldMovePieceInBoard() throws IlegalPlay {
        var boardState = new BoardState();
        var pawn = new Pawn(Color.BLACK);
        boardState.placePiece("e2", pawn);

        var displacement = new Displacement("e2", "e4");
        displacement.actUpon(boardState);

        assertNull(boardState.getPieceAt(new Position("e2")).orElse(null));
        assertEquals(pawn, boardState.getPieceAt(new Position("e4")).orElseThrow());
    }

    @Test
    void shouldNotMoveIfThereIsNoPieceInPlace() {
        var boardState = new BoardState();

        var displacement = new Displacement("e2", "e4");

        assertThrows(IlegalPlay.class, () -> displacement.actUpon(boardState));
    }

    @Test
    void shouldNotMoveIfThereIsAPieceInTargetPosition() {
        var boardState = new BoardState();
        boardState.placePiece("e2", new Pawn(Color.BLACK));
        boardState.placePiece("e4", new Pawn(Color.BLACK));

        var displacement = new Displacement("e2", "e4");

        assertThrows(IlegalPlay.class, () -> displacement.actUpon(boardState));
    }
}
