import {Position} from "./position";
import {Chessboard} from "./chessboard";

export class Piece {

    public constructor(
        private board: Chessboard
    ) {

    }

    public move(position: Position): void {
        this.board.getPiece(position)
    }


}
