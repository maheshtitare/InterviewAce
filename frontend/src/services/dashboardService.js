import api from './api';

const dashboardService = {
  getStudentDashboard: () =>
    api.get('/tests/dashboard'),

  getAdminDashboard: () =>
    api.get('/tests/admin/dashboard'),

  getLeaderboard: () =>
    api.get('/tests/leaderboard'),

  getCategoryAnalysis: () =>
    api.get('/tests/admin/category-analysis'),

  getDifficultyAnalysis: () =>
    api.get('/tests/admin/difficulty-analysis'),

  getAttempts: () =>
  api.get('/tests/attempts'),
  
};

export default dashboardService;