import React, { useEffect, useState } from 'react';
import api from '../services/api';
import './Profile.css';

function Profile() {

const [profile, setProfile] = useState({
fullName: '',
email: '',
headline: '',
about: '',
city: '',
state: '',
country: '',
linkedinUrl: '',
githubUrl: '',
portfolioUrl: '',
profileCompletion: 0
});

const [loading, setLoading] = useState(true);

useEffect(() => {
loadProfile();
}, []);

const loadProfile = async () => {


try {

  const response =
    await api.get('/profile');

  setProfile(response.data);

} catch (error) {

  console.error(
    'Profile Load Error:',
    error
  );

} finally {

  setLoading(false);
}


};

const handleChange = (e) => {


setProfile({
  ...profile,
  [e.target.name]: e.target.value
});


};

const handleSave = async () => {

try {

  const payload = {
    headline: profile.headline,
    about: profile.about,
    city: profile.city,
    state: profile.state,
    country: profile.country,
    linkedinUrl: profile.linkedinUrl,
    githubUrl: profile.githubUrl,
    portfolioUrl: profile.portfolioUrl
  };

  const response =
    await api.put(
      '/profile',
      payload
    );

  setProfile(response.data);

  alert('Profile Updated');

} catch (error) {

  console.error(
    'Profile Update Error:',
    error
  );
}

};

if (loading) {
return <h2>Loading Profile...</h2>;
}

return (
  <div className="profile-container">

    <div className="profile-card">

      <div className="profile-header">
        <h1>My Profile</h1>
      </div>

      <div className="profile-info">

        <div className="info-box">
          <h4>Name</h4>
          <p>{profile.fullName}</p>
        </div>

        <div className="info-box">
          <h4>Email</h4>
          <p>{profile.email}</p>
        </div>

        <div className="info-box">
          <h4>Profile Completion</h4>
          <p>{profile.profileCompletion}%</p>
        </div>

      </div>

      <div className="form-grid">

        <div className="form-group">
          <label>Headline</label>
          <input
            type="text"
            name="headline"
            value={profile.headline || ''}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>City</label>
          <input
            type="text"
            name="city"
            value={profile.city || ''}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>State</label>
          <input
            type="text"
            name="state"
            value={profile.state || ''}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Country</label>
          <input
            type="text"
            name="country"
            value={profile.country || ''}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>LinkedIn URL</label>
          <input
            type="text"
            name="linkedinUrl"
            value={profile.linkedinUrl || ''}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>GitHub URL</label>
          <input
            type="text"
            name="githubUrl"
            value={profile.githubUrl || ''}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Portfolio URL</label>
          <input
            type="text"
            name="portfolioUrl"
            value={profile.portfolioUrl || ''}
            onChange={handleChange}
          />
        </div>

      </div>

      <br />

      <div className="form-group">
        <label>About</label>

        <textarea
          name="about"
          value={profile.about || ''}
          onChange={handleChange}
        />
      </div>

      <button
        className="save-btn"
        onClick={handleSave}
      >
        Save Profile
      </button>

    </div>

  </div>
);
}

export default Profile;
