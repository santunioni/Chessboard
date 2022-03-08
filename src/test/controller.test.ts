import {Player} from "../main/player";
import {Controller} from "../main/controller";
import {createMock} from "@golevelup/ts-jest";




describe("Game controller should be able to ask players to move pieces around the chessboard.", () => {

    let playerWhite: Player
    let playerBlack: Player
    let controller: Controller

    beforeEach(() => {
        playerWhite = createMock<Player>()
        playerBlack = createMock<Player>()
        controller = new Controller(playerWhite, playerBlack)
    });

    it("Should ask player white to start playing", () => {
        // Act
        controller.runRound()

        // Assert
        expect(playerWhite.getInput).toHaveBeenCalled()
    })

    it("Should ask player black to play after first move", () => {
        // Arrange
        controller.runRound()

        // Act
        controller.runRound()

        // Assert
        expect(playerBlack.getInput).toHaveBeenCalled()
    })
})
