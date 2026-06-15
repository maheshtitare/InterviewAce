import api from './api';

const testService = {
  getAvailableTests: (category, page = 0, size = 10) =>
    api.get('/student/tests', { params: { category, page, size } }),
  
  startTest: (testId) =>
  api.post(`/tests/start/${testId}`),  
  getTestQuestions: (attemptId) =>
    api.get(`/student/test-attempts/${attemptId}/questions`),
  
  submitAnswer: (attemptId, questionId, userAnswer, isMarkedForReview) =>
    api.post(`/student/test-attempts/${attemptId}/answer`, {
      questionId, userAnswer, isMarkedForReview
    }),
  
  submitTest: (attemptId) =>
    api.post(`/student/test-attempts/${attemptId}/submit`, {}),
  
  getTestResult: (attemptId) =>
    api.get(`/student/test-attempts/${attemptId}/result`),
};

export default testService;