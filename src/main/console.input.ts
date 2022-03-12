// import {Input} from "./input.interface";
// import {Move} from "./position";
// import {inquirer} from 'inquirer'
//
//
// const QUESTIONS = [
//     {
//         type: 'input',
//         name: 'moveInput',
//         message: "Type your move (from, to) e.x.: A6, A8"
//     }
// ]
//
// const INPUT_MOVE_PARSER = {
//     'A': 1,
//     'B': 2,
//     'C': 3,
//     'D': 4,
//     'E': 5,
//     'F': 6,
//     'G': 7,
//     'H': 8
// }
//
// export class ConsoleInput implements Input {
//
//     getInput(): Move {
//         let input: string
//
//         inquirer.prompt(QUESTIONS).then(answers => {
//             input = answers.name
//         })
//         let [fromString, toString] = input.split(', ')
//
//         return input
//     }
//
//     parseInput() {}
// }
