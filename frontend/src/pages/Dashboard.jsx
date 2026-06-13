import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Dashboard.css';

function Dashboard() {
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const storedUser = localStorage.getItem('user');
    if (storedUser) {
      setUser(JSON.parse(storedUser));
    } else {
      navigate('/login');
    }
  }, [navigate]);

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    navigate('/login');
  };

  return (
    <div className="dashboard-container">
      <header className="dashboard-header">
        <h1>InterviewAce Dashboard</h1>
        <button onClick={handleLogout}>Logout</button>
      </header>

      {user && (
        <div className="dashboard-content">
          <h2>Welcome, {user.fullName}!</h2>
          <p>Email: {user.email}</p>
          <p>Role: {user.role}</p>
          <p>Profile Completion: {user.profileCompletion}%</p>
        </div>
      )}
    </div>
  );
}

export default Dashboard;