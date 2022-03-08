import {Movement} from "./position";

export class Player {
    getInput(): Movement {
        return {from: {row: 1, col: 1}, to: {row: 2, col: 2}}
}
}