export default function Log({ turns }) {
    // for (const turn of turns) {
    //     const { square, player } = turn;
    //     const { row, col } = square;
    //     return <ol id="log">
    //         <li>{row + ', ' + col + " by " + player}</li>
    //     </ol>
    // }
    return (
        <ol id="log">
            {turns.map(turn => <li key={`${turn.square.row}${turn.square.col}`}>{turn.player} selected {turn.square.row}, {turn.square.col}</li>)}
        </ol>
    );
}