import api from './api';

const leaderboardService = {
  getTopScorers: (limit = 10) =>
    api.get('/leaderboard/top-scorers', { params: { limit } }),
  
  getUserRank: () =>
    api.get('/leaderboard/user-rank'),
};

export default leaderboardService;