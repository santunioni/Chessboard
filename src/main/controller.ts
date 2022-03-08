import {Player} from "./player";

export class Controller {
    private currentPlayer: Player

    public constructor(
        private playerWhite: Player,
        private playerBlack: Player
    ) {
        this.currentPlayer = playerWhite
    }

    private changePlayer(): void {
        if (this.currentPlayer === this.playerBlack) {
            this.currentPlayer = this.playerWhite
        } else {
            this.currentPlayer = this.playerBlack
        }
    }

    public runRound(): void {
        const movement = this.currentPlayer.getInput()
        this.changePlayer()
}
}