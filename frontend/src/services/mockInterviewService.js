import api from './api';

const mockInterviewService = {
  addMockInterview: (data) =>
    api.post('/student/mock-interviews', data),
  
  getMockInterviews: (status, page = 0, size = 10) =>
    api.get('/student/mock-interviews', { params: { status, page, size } }),
  
  updateMockInterview: (mockId, data) =>
    api.put(`/student/mock-interviews/${mockId}`, data),
  
  deleteMockInterview: (mockId) =>
    api.delete(`/student/mock-interviews/${mockId}`),
};

export default mockInterviewService;