package chess.pieces;

import chess.game.board.BoardState;
import chess.game.grid.Position;
import chess.game.pieces.Color;
import chess.game.pieces.Knight;
import chess.game.pieces.Pawn;
import chess.game.plays.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnightMoveTest {

    private BoardState board;

    @BeforeEach
    void setUp() {
        this.board = new BoardState();
    }

    @Test
    void shouldMoveInL() {
        var knight = new Knight(Color.WHITE);
        this.board.placePiece("d4", knight);

        var expectedValidMoves = List.of(
                new Move(Color.BLACK, new Position("d4"), new Position("e6")),
                new Move(Color.BLACK, new Position("d4"), new Position("c6")),

                new Move(Color.BLACK, new Position("d4"), new Position("b5")),
                new Move(Color.BLACK, new Position("d4"), new Position("b3")),

                new Move(Color.BLACK, new Position("d4"), new Position("c2")),
                new Move(Color.BLACK, new Position("d4"), new Position("e2")),

                new Move(Color.BLACK, new Position("d4"), new Position("f3")),
                new Move(Color.BLACK, new Position("d4"), new Position("f5"))
        );

        assertEquals(expectedValidMoves, knight.getPossiblePlays());
    }

    @Test
    void shouldBeBlockedByItsTeamMates() {
        var knight = new Knight(Color.WHITE);
        this.board.placePiece("b1", knight);
        this.board.placePiece("c3", new Pawn(Color.WHITE));

        var expectedValidMoves = List.of(
                new Move(Color.BLACK, new Position("b1"), new Position("a3")),
                new Move(Color.BLACK, new Position("b1"), new Position("d2"))
        );

        assertEquals(expectedValidMoves, knight.getPossiblePlays());
    }
}