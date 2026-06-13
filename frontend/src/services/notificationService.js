import api from './api';

const notificationService = {
  getNotifications: (isRead, page = 0, size = 10) =>
    api.get('/notifications', { params: { isRead, page, size } }),
  
  markAsRead: (notificationId) =>
    api.put(`/notifications/${notificationId}/read`, {}),
  
  markAllAsRead: () =>
    api.put('/notifications/mark-all-read', {}),
};

export default notificationService;