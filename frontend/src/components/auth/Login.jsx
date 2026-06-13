import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import authService from '../../services/authService';
import './Auth.css';

function Login() {
  const [loginType, setLoginType] = useState('email');
  const [email, setEmail] = useState('');
  const [mobileNumber, setMobileNumber] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setLoading(true);

    try {
      let response;
      if (loginType === 'email') {
        response = await authService.loginEmail(email, password);
      } else {
        response = await authService.loginMobile(mobileNumber, password);
      }

     localStorage.setItem('token', response.data.token);

localStorage.setItem(
  'user',
  JSON.stringify({
    fullName: response.data.fullName,
    email: response.data.email,
    role: response.data.role,
    profileCompletion: 0
  })
);

navigate('/dashboard');
    } catch (err) {
      setError(err.response?.data?.message || 'Login failed');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-container">
      <div className="auth-card">
        <h1>InterviewAce</h1>
        <h2>Login</h2>
        
        {error && <div className="error-message">{error}</div>}

        <div className="login-type-selector">
          <button className={loginType === 'email' ? 'active' : ''} onClick={() => setLoginType('email')}>Email</button>
          <button className={loginType === 'mobile' ? 'active' : ''} onClick={() => setLoginType('mobile')}>Mobile</button>
        </div>

        <form onSubmit={handleSubmit}>
          {loginType === 'email' ? (
            <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} required />
          ) : (
            <input type="text" placeholder="Mobile Number" value={mobileNumber} onChange={(e) => setMobileNumber(e.target.value)} required />
          )}
          <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} required />
          <button type="submit" disabled={loading}>{loading ? 'Logging in...' : 'Login'}</button>
        </form>

        <p>Don't have an account? <Link to="/register">Register here</Link></p>
      </div>
    </div>
  );
}

export default Login;