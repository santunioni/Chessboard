package chess.pieces;

import chess.game.board.BoardState;
import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.pieces.Pawn;
import chess.game.pieces.Rook;
import chess.game.plays.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RookMoveTest {
    private BoardState board;

    @BeforeEach
    void setUp() {
        this.board = new BoardState();
    }

    @Test
    void shouldBeAbleToMoveVerticallyAndHorizontally() {
        var rook = new Rook(Color.BLACK);
        this.board.placePiece("d4", rook);

        var expectedValidMoves = List.of(
                new Move(Color.BLACK, new Position("d4"), new Position("a4")),
                new Move(Color.BLACK, new Position("d4"), new Position("b4")),
                new Move(Color.BLACK, new Position("d4"), new Position("c4")),
                new Move(Color.BLACK, new Position("d4"), new Position("e4")),
                new Move(Color.BLACK, new Position("d4"), new Position("f4")),
                new Move(Color.BLACK, new Position("d4"), new Position("g4")),
                new Move(Color.BLACK, new Position("d4"), new Position("h4")),

                new Move(Color.BLACK, new Position("d4"), new Position("d1")),
                new Move(Color.BLACK, new Position("d4"), new Position("d2")),
                new Move(Color.BLACK, new Position("d4"), new Position("d3")),
                new Move(Color.BLACK, new Position("d4"), new Position("d5")),
                new Move(Color.BLACK, new Position("d4"), new Position("d6")),
                new Move(Color.BLACK, new Position("d4"), new Position("d7")),
                new Move(Color.BLACK, new Position("d4"), new Position("d8"))
        );

        assertEquals(expectedValidMoves, rook.getPossiblePlays());
    }

    @Test
    void shouldBeBlockedByItsTeamMates() {
        var rook = new Rook(Color.WHITE);
        this.board.placePiece("b1", rook);
        this.board.placePiece("b2", new Pawn(Color.WHITE));
        this.board.placePiece("d1", new Pawn(Color.WHITE));

        var expectedValidMoves = List.of(
                new Move(Color.WHITE, new Position("b1"), new Position("a1")),
                new Move(Color.WHITE, new Position("b1"), new Position("c1"))
        );

        assertEquals(expectedValidMoves, rook.getPossiblePlays());
    }
}
