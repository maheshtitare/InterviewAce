import api from './api';

const interviewQuestionService = {
  getAllQuestions: (page = 0, size = 10) =>
    api.get('/interview-questions', { params: { page, size } }),
  
  searchQuestions: (search, category, page = 0, size = 10) =>
    api.get('/interview-questions', { params: { search, category, page, size } }),
  
  getQuestionById: (id) =>
    api.get(`/interview-questions/${id}`),
  
  bookmarkQuestion: (questionId) =>
    api.post(`/interview-questions/${questionId}/bookmark`, {}),
  
  unbookmarkQuestion: (questionId) =>
    api.delete(`/interview-questions/${questionId}/bookmark`),
  
  getBookmarkedQuestions: (page = 0, size = 10) =>
    api.get('/student/bookmarked-questions', { params: { page, size } }),
  
  markAsLearned: (questionId) =>
    api.put(`/interview-questions/${questionId}/mark-learned`, { isLearned: true }),
};

export default interviewQuestionService;