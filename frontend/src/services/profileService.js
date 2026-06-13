import api from './api';

const profileService = {
  getProfile: () => api.get('/profile'),
  updateProfile: (data) => api.put('/profile/personal-details', data),
  uploadResume: (file) => {
    const formData = new FormData();
    formData.append('file', file);
    return api.post('/profile/resume', formData, {
      headers: { 'Content-Type': 'multipart/form-data' }
    });
  },
  getCompletionPercentage: () => api.get('/profile/completion-percentage'),
  addSkill: (skillName, proficiencyLevel) =>
    api.post('/profile/skills', { skillName, proficiencyLevel }),
  getSkills: () => api.get('/profile/skills'),
  addEducation: (data) => api.post('/profile/education', data),
  getEducation: () => api.get('/profile/education'),
};

export default profileService;
