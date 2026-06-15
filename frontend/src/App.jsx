import React from 'react';
import {
BrowserRouter as Router,
Routes,
Route,
Navigate
} from 'react-router-dom';

import Login from './components/auth/Login';
import Register from './components/auth/Register';

import Dashboard from './pages/Dashboard';
import Attempts from './pages/Attempts';
import Tests from './pages/Tests';
import TestScreen from './pages/TestScreen';
import Result from './pages/Result';
import Profile from './pages/Profile';
import Skills from './pages/Skills';
import Education from './pages/Education';
import Projects from './pages/Projects';
import Experience from "./pages/Experience";
import Resume from "./pages/Resume";
import AdminDashboard from "./pages/AdminDashboard";

import './App.css';

function App() {

const isAuthenticated =
!!localStorage.getItem('token');

return (


<Router>

  <Routes>

    <Route
      path="/login"
      element={<Login />}
    />

    <Route
      path="/register"
      element={<Register />}
    />

    <Route
      path="/dashboard"
      element={
        isAuthenticated
          ? <Dashboard />
          : <Navigate to="/login" />
      }
    />

    <Route
      path="/attempts"
      element={
        isAuthenticated
          ? <Attempts />
          : <Navigate to="/login" />
      }
    />

    <Route
      path="/tests"
      element={
        isAuthenticated
          ? <Tests />
          : <Navigate to="/login" />
      }
    />

    <Route
      path="/test/:attemptId"
      element={
        isAuthenticated
          ? <TestScreen />
          : <Navigate to="/login" />
      }
    />

    <Route
      path="/result"
      element={
        isAuthenticated
          ? <Result />
          : <Navigate to="/login" />
      }
    />

    <Route
  path="/profile"
  element={
    isAuthenticated
      ? <Profile />
      : <Navigate to="/login" />
  }
/>

<Route
  path="/skills"
  element={
    isAuthenticated
      ? <Skills />
      : <Navigate to="/login" />
  }
/>


      <Route
  path="/education"
  element={
    isAuthenticated
      ? <Education />
      : <Navigate to="/login" />
  }
/>

<Route
  path="/projects"
  element={
    isAuthenticated
      ? <Projects />
      : <Navigate to="/login" />
  }
/>

<Route
  path="/experience"
  element={<Experience />}
/>

<Route
  path="/resume"
  element={<Resume />}
/>

<Route
  path="/admin-dashboard"
  element={<AdminDashboard />}
/>

<Route
      path="/"
      element={
        isAuthenticated
          ? <Navigate to="/dashboard" />
          : <Navigate to="/login" />
      }
      />


  </Routes>

</Router>


);
}

export default App;
