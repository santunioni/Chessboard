package chess.domain.pieces;

import chess.domain.board.Board;
import chess.domain.board.PieceColor;
import chess.domain.board.PieceFactory;
import chess.domain.board.PieceType;
import chess.domain.grid.Position;
import chess.domain.play.Move;
import chess.domain.play.Play;
import com.google.common.collect.HashBiMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BishopMoveTest {

    private final PieceFactory pieceFactory = new PieceFactory();
    private Board board;
    private List<Play> stack;

    @BeforeEach
    void setUp() {
        this.stack = new ArrayList<>();
        this.board = new Board(UUID.randomUUID().toString(), HashBiMap.create(), stack);
    }

    private void forwardToBlackTurn() {
        this.stack.add(
                new Move(PieceType.BISHOP, PieceColor.WHITE, new Position("h7"), new Position("h8")));
    }

    @Test
    void shouldBeAbleToMoveDiagonally() {
        forwardToBlackTurn();
        var bishop = this.pieceFactory.createBishops(PieceColor.BLACK).get(0);
        this.board.placePieceAt("d4", bishop);

        var expectedValidMoves = Set.of(
                new Move(PieceType.BISHOP, PieceColor.BLACK, new Position("d4"), new Position("c3")),
                new Move(PieceType.BISHOP, PieceColor.BLACK, new Position("d4"), new Position("b2")),
                new Move(PieceType.BISHOP, PieceColor.BLACK, new Position("d4"), new Position("a1")),
                new Move(PieceType.BISHOP, PieceColor.BLACK, new Position("d4"), new Position("c5")),
                new Move(PieceType.BISHOP, PieceColor.BLACK, new Position("d4"), new Position("b6")),
                new Move(PieceType.BISHOP, PieceColor.BLACK, new Position("d4"), new Position("a7")),
                new Move(PieceType.BISHOP, PieceColor.BLACK, new Position("d4"), new Position("e3")),
                new Move(PieceType.BISHOP, PieceColor.BLACK, new Position("d4"), new Position("f2")),
                new Move(PieceType.BISHOP, PieceColor.BLACK, new Position("d4"), new Position("g1")),
                new Move(PieceType.BISHOP, PieceColor.BLACK, new Position("d4"), new Position("e5")),
                new Move(PieceType.BISHOP, PieceColor.BLACK, new Position("d4"), new Position("f6")),
                new Move(PieceType.BISHOP, PieceColor.BLACK, new Position("d4"), new Position("g7")),
                new Move(PieceType.BISHOP, PieceColor.BLACK, new Position("d4"), new Position("h8"))
        );

        assertEquals(expectedValidMoves, bishop.getSuggestedPlays());
    }

    @Test
    void shouldBeBlockedByItsTeamMates() {
        var bishop = this.pieceFactory.createBishops(PieceColor.WHITE).get(0);
        this.board.placePieceAt("b1", bishop);
        this.board.placePieceAt("d3", this.pieceFactory.createPawns(PieceColor.WHITE).get(0));

        var expectedValidMoves = Set.of(
                new Move(PieceType.BISHOP, PieceColor.WHITE, new Position("b1"), new Position("a2")),
                new Move(PieceType.BISHOP, PieceColor.WHITE, new Position("b1"), new Position("c2"))
        );

        assertEquals(expectedValidMoves, bishop.getSuggestedPlays());
    }
}
