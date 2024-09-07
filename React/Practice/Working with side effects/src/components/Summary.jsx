import quizCompleteLogo from '../assets/quiz-complete.png';
import QUESTIONS from "../questions.js";


export default function Summary({ userAnswers }) {
    return (
        <div id="summary">
            <img src={quizCompleteLogo} alt="Quiz completed!!!" />
            <h2>Quiz is completed</h2>
            <div id="summary-stats">
                <p>
                    <span className='number'>10%</span>
                    <span className='text'>skipped</span>
                </p>
                <p>
                    <span className='number'>10%</span>
                    <span className='text'>answered correctly</span>
                </p>
                <p>
                    <span className='number'>10%</span>
                    <span className='text'>answered incorrectly</span>
                </p>
            </div>
            <ol>
                {userAnswers.map((answer, index) => {
                    let cssClass = 'user-answer';
                    if (answer === null) {
                        cssClass += ' skipped';
                    } else if (answer === QUESTIONS[index].answers[0]) {
                        cssClass += ' correct';
                    } else {
                        cssClass += ' wrong';
                    }
                    return (<li key={answer}>
                        <h3>{index + 1}</h3>
                        <p className='question'>{QUESTIONS[index].text}</p>
                        <p className='user-answer'>{answer ?? 'skipped'}</p>
                    </li>);
                })}

            </ol>
        </div>
    );
}