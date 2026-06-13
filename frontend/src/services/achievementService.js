import api from './api';

const achievementService = {
  getUserAchievements: () =>
    api.get('/student/achievements'),
};

export default achievementService;