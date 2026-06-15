import React from 'react';
import { useNavigate } from 'react-router-dom';

function Sidebar() {

  const navigate = useNavigate();

  return (
    <div className="sidebar">

      <button onClick={() => navigate('/dashboard')}>
        Dashboard
      </button>

      <button onClick={() => navigate('/tests')}>
        Tests
      </button>

      <button onClick={() => navigate('/attempts')}>
        Attempts
      </button>

      <button onClick={() => navigate('/profile')}>
        Profile
      </button>

      <button onClick={() => navigate('/resume')}>
        Resume
      </button>

    </div>
  );
}

export default Sidebar;