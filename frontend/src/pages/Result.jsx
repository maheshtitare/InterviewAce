import React from 'react';
import './Result.css';
import { useLocation, useNavigate } from 'react-router-dom';

function Result() {

const location = useLocation();
const navigate = useNavigate();

const result = location.state;

if (!result) {
return ( <div> <h2>No Result Found</h2>

    <button
      onClick={() => navigate('/tests')}
    >
      Back To Tests
    </button>
  </div>
);

}

return (
  <div className="result-container">

    <div className="result-card">

      <h1 className="result-title">
        Test Completed 🎉
      </h1>

      <div className="result-grid">

        <div className="result-item">
          <h4>Score</h4>
          <h2>{result.score}</h2>
        </div>

        <div className="result-item">
          <h4>Correct Answers</h4>
          <h2>{result.correctAnswers}</h2>
        </div>

        <div className="result-item">
          <h4>Wrong Answers</h4>
          <h2>{result.wrongAnswers}</h2>
        </div>

        <div className="result-item">
          <h4>Accuracy</h4>
          <h2>
            {result.accuracy.toFixed(2)}%
          </h2>
        </div>

      </div>

      <div className="result-status">

        <h2
          className={
            result.result === 'PASS'
              ? 'pass'
              : 'fail'
          }
        >
          {result.result}
        </h2>

        <p>
          Total Questions:
          {' '}
          {result.totalQuestions}
        </p>

      </div>

      <button
        className="dashboard-btn"
        onClick={() =>
          navigate('/dashboard')
        }
      >
        Back To Dashboard
      </button>

    </div>

  </div>
);
}

export default Result;
