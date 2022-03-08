import {Position} from "../main/position";
import {Chessboard} from "../main/chessboard";

describe("Test case trivial", () => {

    it("Should work", () =>{
        const a = 1
        const c = 2
        expect(a+c).toBe(3)
    })


    it("", () => {
        // Arrange
        const board = new Chessboard()
        const piece = board.getPiece(new Position(1, 2))

        // Act
        const targetPosition = new Position(2, 2)
        piece.move(targetPosition)

        // Assert
        expect(board.getPiece(targetPosition)).toBe(piece)
    })
})

