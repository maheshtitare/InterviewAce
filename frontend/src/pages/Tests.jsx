import React, { useEffect, useState } from 'react';
import api from '../services/api';
import './Tests.css';
import { useNavigate } from 'react-router-dom';
import testService from '../services/testService';

function Tests() {

const navigate = useNavigate();

const [tests, setTests] = useState([]);

useEffect(() => {
loadTests();
}, []);

const loadTests = async () => {


try {

  const response =
    await api.get('/tests');

  setTests(response.data);

} catch (error) {

  console.error(
    'Tests API Error:',
    error
  );
}

};

const handleStartTest = async (testId) => {

try {

  const response =
    await testService.startTest(testId);

  const attemptId = response.data;

  navigate(`/test/${attemptId}`);

} catch (error) {

  console.error(
    'Start Test Error:',
    error
  );

  alert('Failed to start test');
}

};

return (
  <div className="tests-container">

    <div className="tests-card">

      <h2 className="tests-title">
        Available Tests
      </h2>

      <div className="tests-grid">

        {tests.map((test) => (

          <div
            key={test.id}
            className="test-item"
          >

            <h3>
              {test.title}
            </h3>

            <p className="test-info">
              <strong>Category:</strong>
              {' '}
              {test.category}
            </p>

            <p className="test-info">
              <strong>Difficulty:</strong>
              {' '}
              {test.difficulty}
            </p>

            <p className="test-info">
              <strong>Questions:</strong>
              {' '}
              {test.totalQuestions}
            </p>

            <p className="test-info">
              <strong>Duration:</strong>
              {' '}
              {test.timeLimitMinutes}
              {' '}
              Minutes
            </p>

            <button
              className="start-btn"
              onClick={() =>
                handleStartTest(test.id)
              }
            >
              Start Test
            </button>

          </div>

        ))}

      </div>

    </div>

  </div>
);
}

export default Tests;
