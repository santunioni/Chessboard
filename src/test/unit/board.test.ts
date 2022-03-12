import { Board, EmptyPlaceOnBoard } from "../../main/board"
import { createMock, DeepMocked } from "@golevelup/ts-jest"
import { Piece } from "../../main/piece"

describe("Board should be able to move pieces and check whether a move is allowed", () => {
  let board: Board
  let piece: DeepMocked<Piece>

  beforeEach(() => {
    board = new Board()
  })

  it("getPiece should return null if there is no piece on that position", () => {
    // Assert
    expect(board.getPiece({ row: 2, col: 2 })).toBe(null)
  })

  it("should place a piece on the board", () => {
    // Arrange
    piece = createMock<Piece>()

    // Act
    board.placePiece(piece, { row: 2, col: 2 })

    // Assert
    expect(board.getPiece({ row: 2, col: 2 })).toStrictEqual(piece)
  })

  it("should move piece in the board to new position", () => {
    // Arrange
    piece = createMock<Piece>()
    board.placePiece(piece, { row: 2, col: 2 })

    // Act
    board.move({ from: { row: 2, col: 2 }, to: { row: 2, col: 4 } })

    // Assert
    expect(board.getPiece({ row: 2, col: 2 })).toBe(null)
    expect(board.getPiece({ row: 2, col: 4 })).toStrictEqual(piece)
  })

  it("should raise an exception if trying to move a piece that is not there", () => {
    // Arrange
    const moving = () =>
      board.move({ from: { row: 2, col: 2 }, to: { row: 2, col: 4 } })

    // Act Assert
    expect(moving).toThrow(EmptyPlaceOnBoard)
  })
})
