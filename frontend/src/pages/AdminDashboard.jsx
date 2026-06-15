import React, { useEffect, useState } from "react";
import "./AdminDashboard.css";
import axios from "axios";

function AdminDashboard() {

const [dashboard, setDashboard] = useState({
totalUsers: 0,
totalTests: 0,
totalAttempts: 0,
averageScore: 0
});

const [categories, setCategories] = useState([]);

const [difficulties, setDifficulties] = useState([]);

useEffect(() => {
loadAdminDashboard();
loadCategoryAnalysis();
loadDifficultyAnalysis();
}, []);

const loadAdminDashboard = async () => {
try {


  const response = await axios.get(
    "http://localhost:8080/api/tests/admin/dashboard"
  );

  setDashboard(response.data);

} catch (error) {
  console.error(error);
}


};

const loadCategoryAnalysis = async () => {
try {

  const response = await axios.get(
    "http://localhost:8080/api/tests/admin/category-analysis"
  );

  setCategories(response.data);

} catch (error) {
  console.error(error);
}

};

const loadDifficultyAnalysis = async () => {
try {

  const response = await axios.get(
    "http://localhost:8080/api/tests/admin/difficulty-analysis"
  );

  setDifficulties(response.data);

} catch (error) {
  console.error(error);
}

};

return (
  <div className="admin-container">

    <div className="admin-card">

      <h2 className="admin-title">
        Admin Dashboard
      </h2>

      <div className="admin-stats">

        <div className="stat-box">
          <h4>Total Users</h4>
          <h2>{dashboard.totalUsers}</h2>
        </div>

        <div className="stat-box">
          <h4>Total Tests</h4>
          <h2>{dashboard.totalTests}</h2>
        </div>

        <div className="stat-box">
          <h4>Total Attempts</h4>
          <h2>{dashboard.totalAttempts}</h2>
        </div>

        <div className="stat-box">
          <h4>Average Score</h4>
          <h2>{dashboard.averageScore}</h2>
        </div>

      </div>

      <div className="analysis-card">

        <h3>Category Analysis</h3>

        <table className="analysis-table">

          <thead>
            <tr>
              <th>Category</th>
              <th>Total Questions</th>
            </tr>
          </thead>

          <tbody>
            {categories.map((item, index) => (
              <tr key={index}>
                <td>{item.category}</td>
                <td>{item.totalQuestions}</td>
              </tr>
            ))}
          </tbody>

        </table>

      </div>

      <div className="analysis-card">

        <h3>Difficulty Analysis</h3>

        <table className="analysis-table">

          <thead>
            <tr>
              <th>Difficulty</th>
              <th>Total Questions</th>
            </tr>
          </thead>

          <tbody>
            {difficulties.map((item, index) => (
              <tr key={index}>
                <td>{item.difficulty}</td>
                <td>{item.totalQuestions}</td>
              </tr>
            ))}
          </tbody>

        </table>

      </div>

    </div>

  </div>
);
}

export default AdminDashboard;
