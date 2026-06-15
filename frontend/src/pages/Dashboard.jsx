import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import dashboardService from '../services/dashboardService';

import Navbar from '../components/layout/Navbar';
import Sidebar from '../components/layout/Sidebar';
import '../components/layout/Layout.css';

import './Dashboard.css';

function Dashboard() {
  const [user, setUser] = useState(null);

  const [dashboardData, setDashboardData] = useState({
    totalAttempts: 0,
    bestScore: 0,
    averageScore: 0,
    latestScore: 0
  });

  const [leaderboard, setLeaderboard] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    const storedUser = localStorage.getItem('user');

    if (storedUser) {
      setUser(JSON.parse(storedUser));

      loadDashboard();
      loadLeaderboard();
    } else {
      navigate('/login');
    }
  }, [navigate]);

  const loadDashboard = async () => {
    try {
      const response =
        await dashboardService.getStudentDashboard();

      setDashboardData(response.data);
    } catch (error) {
      console.error('Dashboard API Error:', error);
    }
  };

  const loadLeaderboard = async () => {
    try {
      const response =
        await dashboardService.getLeaderboard();

      setLeaderboard(response.data);
    } catch (error) {
      console.error('Leaderboard API Error:', error);
    }
  };

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');

    navigate('/login');
  };

  return (
  <div className="dashboard-container">

  <Navbar />

  <div className="dashboard-layout">

    <Sidebar />

    <div className="dashboard-main">

    <header className="dashboard-header">
      <h1>InterviewAce Dashboard</h1>

      <button onClick={handleLogout}>
        Logout
      </button>
    </header>

   {user && (
  <div className="dashboard-content">
    

        <div className="welcome-card">
          <h2>Welcome, {user.fullName} 👋</h2>

          <p>{user.email}</p>

          <p>Role: {user.role}</p>

          <p>
            Profile Completion:
            {" "}
            {user.profileCompletion}%
          </p>
        </div>

        <div className="stats-grid">

          <div className="stat-card">
            <h4>Total Attempts</h4>
            <h2>{dashboardData.totalAttempts}</h2>
          </div>

          <div className="stat-card">
            <h4>Best Score</h4>
            <h2>{dashboardData.bestScore}</h2>
          </div>

          <div className="stat-card">
            <h4>Average Score</h4>
            <h2>{dashboardData.averageScore}</h2>
          </div>

          <div className="stat-card">
            <h4>Latest Score</h4>
            <h2>{dashboardData.latestScore}</h2>
          </div>

        </div>

        <div className="leaderboard-card">

          <h3>Leaderboard</h3>

          <br />

          <table className="leaderboard-table">

            <thead>
              <tr>
                <th>Rank</th>
                <th>Attempt ID</th>
                <th>Score</th>
              </tr>
            </thead>

            <tbody>
              {leaderboard.map((item, index) => (
                <tr key={item.attemptId}>
                  <td>{index + 1}</td>
                  <td>{item.attemptId}</td>
                  <td>{item.score}</td>
                </tr>
              ))}
            </tbody>

          </table>

        </div>

        <div className="actions-grid">

          <button
            className="action-btn"
            onClick={() => navigate('/attempts')}
          >
            Attempt History
          </button>

          <button
            className="action-btn"
            onClick={() => navigate('/tests')}
          >
            Available Tests
          </button>

          <button
            className="action-btn"
            onClick={() => navigate('/profile')}
          >
            Profile
          </button>

          <button
            className="action-btn"
            onClick={() => navigate('/skills')}
          >
            Skills
          </button>

          <button
            className="action-btn"
            onClick={() => navigate('/education')}
          >
            Education
          </button>

          <button
            className="action-btn"
            onClick={() => navigate('/projects')}
          >
            Projects
          </button>

          <button
            className="action-btn"
            onClick={() => navigate('/experience')}
          >
            Experience
          </button>

          <button
            className="action-btn"
            onClick={() => navigate('/resume')}
          >
            Resume
          </button>

          <button
            className="action-btn"
            onClick={() => navigate('/admin-dashboard')}
          >
            Admin Dashboard
          </button>

        </div>

            </div>
    )}

    </div>

  </div>

</div>
);
}


export default Dashboard;