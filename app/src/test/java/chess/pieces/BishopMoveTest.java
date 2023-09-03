package chess.pieces;

import chess.game.board.BoardState;
import chess.game.grid.Position;
import chess.game.pieces.Bishop;
import chess.game.pieces.Color;
import chess.game.pieces.Pawn;
import chess.game.plays.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BishopMoveTest {

    private BoardState board;

    @BeforeEach
    void setUp() {
        this.board = new BoardState();
    }

    @Test
    void shouldBeAbleToMoveDiagonally() {
        var bishop = new Bishop(Color.BLACK);
        this.board.placePiece("d4", bishop);

        var expectedValidMoves = Set.of(
                new Move(Color.BLACK, new Position("d4"), new Position("c3")),
                new Move(Color.BLACK, new Position("d4"), new Position("b2")),
                new Move(Color.BLACK, new Position("d4"), new Position("a1")),
                new Move(Color.BLACK, new Position("d4"), new Position("c5")),
                new Move(Color.BLACK, new Position("d4"), new Position("b6")),
                new Move(Color.BLACK, new Position("d4"), new Position("a7")),
                new Move(Color.BLACK, new Position("d4"), new Position("e3")),
                new Move(Color.BLACK, new Position("d4"), new Position("f2")),
                new Move(Color.BLACK, new Position("d4"), new Position("g1")),
                new Move(Color.BLACK, new Position("d4"), new Position("e5")),
                new Move(Color.BLACK, new Position("d4"), new Position("f6")),
                new Move(Color.BLACK, new Position("d4"), new Position("g7")),
                new Move(Color.BLACK, new Position("d4"), new Position("h8"))
        );

        assertEquals(expectedValidMoves, bishop.getPossiblePlays());
    }

    @Test
    void shouldBeBlockedByItsTeamMates() {
        var bishop = new Bishop(Color.WHITE);
        this.board.placePiece("b1", bishop);
        this.board.placePiece("d3", new Pawn(Color.WHITE));

        var expectedValidMoves = Set.of(
                new Move(Color.WHITE, new Position("b1"), new Position("a2")),
                new Move(Color.WHITE, new Position("b1"), new Position("c2"))
        );

        assertEquals(expectedValidMoves, bishop.getPossiblePlays());
    }
}
