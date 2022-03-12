import { createMock, DeepMocked } from "@golevelup/ts-jest"
import { Board } from "../../main/board"
import { Controller } from "../../main/controller"
import { Player } from "../../main/player"

describe("Game controller should be able to ask players to move pieces around the chessboard.", () => {
  let playerWhite: DeepMocked<Player>
  let playerBlack: DeepMocked<Player>
  let board: DeepMocked<Board>
  let controller: Controller

  beforeEach(() => {
    playerWhite = createMock<Player>()
    playerBlack = createMock<Player>()
    board = createMock<Board>()
    const players = { white: playerWhite, black: playerBlack }
    controller = new Controller(players, board)
  })

  it("Should ask player white to start playing", () => {
    // Act
    controller.runRound()

    // Assert
    expect(playerWhite.getMove).toHaveBeenCalled()
  })

  it("Should ask player black to play after first move", () => {
    // Arrange
    controller.runRound()

    // Act
    controller.runRound()

    // Assert
    expect(playerBlack.getMove).toHaveBeenCalled()
  })

  it("should move a piece after player move", () => {
    // Arrange
    const move = { from: { row: 1, col: 1 }, to: { row: 8, col: 1 } }
    playerWhite.getMove.mockReturnValueOnce(move)

    // Act
    controller.runRound()

    // Assert
    expect(board.move).toHaveBeenNthCalledWith(1, move)
  })
})
