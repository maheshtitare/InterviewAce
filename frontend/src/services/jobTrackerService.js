import api from './api';

const jobTrackerService = {
  addJobApplication: (data) =>
    api.post('/student/job-applications', data),
  
  getJobApplications: (status, page = 0, size = 10) =>
    api.get('/student/job-applications', { params: { status, page, size } }),
  
  updateJobApplication: (jobId, data) =>
    api.put(`/student/job-applications/${jobId}`, data),
  
  deleteJobApplication: (jobId) =>
    api.delete(`/student/job-applications/${jobId}`),
};

export default jobTrackerService;