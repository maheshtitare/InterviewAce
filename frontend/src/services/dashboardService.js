import api from './api';

const dashboardService = {
  getStudentDashboard: () =>
    api.get('/dashboard/student'),
  
  getAdminDashboard: () =>
    api.get('/dashboard/admin'),
};

export default dashboardService;