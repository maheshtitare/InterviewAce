import React, { useEffect, useState } from 'react';
import './Attempts.css';
import dashboardService from '../services/dashboardService';

function Attempts() {

  const [attempts, setAttempts] = useState([]);

  useEffect(() => {
    loadAttempts();
  }, []);

  const loadAttempts = async () => {

    try {

      const response =
        await dashboardService.getAttempts();

      setAttempts(response.data);

    } catch (error) {

      console.error(
        'Attempts API Error:',
        error
      );
    }
  };

  return (
  <div className="attempts-container">

    <div className="attempts-card">

      <h2 className="attempts-title">
        Attempt History
      </h2>

      <div className="attempts-grid">

        {attempts.map((attempt) => (

          <div
            key={attempt.attemptId}
            className="attempt-item"
          >

            <h3>
              Attempt #{attempt.attemptId}
            </h3>

            <p className="attempt-info">
              <strong>Score:</strong>
              {' '}
              {attempt.score}
            </p>

            <p className="attempt-info">
              <strong>Correct:</strong>
              {' '}
              {attempt.correctAnswers}
            </p>

            <p className="attempt-info">
              <strong>Wrong:</strong>
              {' '}
              {attempt.wrongAnswers}
            </p>

          </div>

        ))}

      </div>

    </div>

  </div>
);
}

export default Attempts;