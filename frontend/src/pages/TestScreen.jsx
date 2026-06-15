import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import './TestScreen.css';
import api from '../services/api';

function TestScreen() {

const { attemptId } = useParams();

const [questions, setQuestions] = useState([]);
const [loading, setLoading] = useState(true);
const [selectedAnswers, setSelectedAnswers] = useState({});
const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
const navigate = useNavigate();

useEffect(() => {
loadQuestions();
}, []);

const loadQuestions = async () => {


try {

  const response = await api.get(
    `/tests/${attemptId}/questions`
  );

  setQuestions(response.data);

} catch (error) {

  console.error(
    'Questions API Error:',
    error
  );

} finally {

  setLoading(false);
}

};

const handleAnswerSelect = (
questionId,
selectedOption
) => {


setSelectedAnswers((prev) => ({
  ...prev,
  [questionId]: selectedOption
}));

};

const handleSubmitTest = async () => {

try {


const answers = Object.entries(
  selectedAnswers
).map(([questionId, selectedAnswer]) => ({
  questionId: Number(questionId),
  selectedAnswer
}));

const payload = {
  attemptId: Number(attemptId),
  answers
};

const response =
  await api.post(
    '/tests/submit',
    payload
  );

console.log(
  'Result:',
  response.data
);

navigate(
  '/result',
  {
    state: response.data
  }
);

} catch (error) {

console.error(
  'Submit Error:',
  error
);


}
};


if (loading) {
return ( <div> <h2>Loading Questions...</h2> </div>
);
}

if (!questions.length) {
return ( <div> <h2>No Questions Found</h2> </div>
);
}

const currentQuestion =
questions[currentQuestionIndex];

return (
  <div className="test-container">

    <div className="test-card">

      <div className="test-header">

        <div>
          <h2 className="test-title">
            Online Assessment
          </h2>

          <p>
            Attempt ID: {attemptId}
          </p>
        </div>

        <div className="progress-text">
          Question {currentQuestionIndex + 1}
          {" / "}
          {questions.length}
        </div>

      </div>

      <div className="question-card">

        <h3>
          {currentQuestion.questionText}
        </h3>

        <label className="option-item">
          <input
            type="radio"
            name={`question-${currentQuestion.id}`}
            checked={
              selectedAnswers[currentQuestion.id] === 'A'
            }
            onChange={() =>
              handleAnswerSelect(
                currentQuestion.id,
                'A'
              )
            }
          />
          {" "}
          A. {currentQuestion.optionA}
        </label>

        <label className="option-item">
          <input
            type="radio"
            name={`question-${currentQuestion.id}`}
            checked={
              selectedAnswers[currentQuestion.id] === 'B'
            }
            onChange={() =>
              handleAnswerSelect(
                currentQuestion.id,
                'B'
              )
            }
          />
          {" "}
          B. {currentQuestion.optionB}
        </label>

        <label className="option-item">
          <input
            type="radio"
            name={`question-${currentQuestion.id}`}
            checked={
              selectedAnswers[currentQuestion.id] === 'C'
            }
            onChange={() =>
              handleAnswerSelect(
                currentQuestion.id,
                'C'
              )
            }
          />
          {" "}
          C. {currentQuestion.optionC}
        </label>

        <label className="option-item">
          <input
            type="radio"
            name={`question-${currentQuestion.id}`}
            checked={
              selectedAnswers[currentQuestion.id] === 'D'
            }
            onChange={() =>
              handleAnswerSelect(
                currentQuestion.id,
                'D'
              )
            }
          />
          {" "}
          D. {currentQuestion.optionD}
        </label>

      </div>

      <div className="navigation-buttons">

        <button
          className="nav-btn"
          disabled={currentQuestionIndex === 0}
          onClick={() =>
            setCurrentQuestionIndex(
              currentQuestionIndex - 1
            )
          }
        >
          Previous
        </button>

        <button
          className="nav-btn"
          disabled={
            currentQuestionIndex ===
            questions.length - 1
          }
          onClick={() =>
            setCurrentQuestionIndex(
              currentQuestionIndex + 1
            )
          }
        >
          Next
        </button>

      </div>

      <button
        className="submit-btn"
        onClick={handleSubmitTest}
      >
        Submit Test
      </button>

      <div className="palette">

        {questions.map((question, index) => (

          <button
            key={question.id}
            className={
              `palette-btn ${
                currentQuestionIndex === index
                  ? 'active'
                  : selectedAnswers[question.id]
                  ? 'answered'
                  : ''
              }`
            }
            onClick={() =>
              setCurrentQuestionIndex(index)
            }
          >
            {index + 1}
          </button>

        ))}

      </div>

    </div>

  </div>
);
}

export default TestScreen;
