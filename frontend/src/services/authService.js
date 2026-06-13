import api from './api';

const authService = {
  register: (fullName, email, mobileNumber, password, confirmPassword) =>
    api.post('/auth/register', { fullName, email, mobileNumber, password, confirmPassword }),
  
  loginEmail: (email, password) =>
    api.post('/auth/login-email', { email, password }),
  
  loginMobile: (mobileNumber, password) =>
    api.post('/auth/login-mobile', { mobileNumber, password }),
  
  requestOtp: (mobileNumber) =>
    api.post('/auth/forgot-password/request-otp', { mobileNumber }),
  
  verifyOtp: (resetId, otp) =>
    api.post('/auth/forgot-password/verify-otp', { resetId, otp }),
  
  setNewPassword: (token, newPassword, confirmPassword) =>
    api.post('/auth/forgot-password/set-password', { token, newPassword, confirmPassword }),
  
  changePassword: (oldPassword, newPassword, confirmPassword) =>
    api.post('/auth/change-password', { oldPassword, newPassword, confirmPassword }),
  
  logout: () => api.post('/auth/logout', {}),
};

export default authService;
