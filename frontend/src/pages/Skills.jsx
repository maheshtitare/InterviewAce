import React, { useEffect, useState } from 'react';
import './Skills.css';
import api from '../services/api';

function Skills() {

  const [skills, setSkills] = useState([]);

  const [formData, setFormData] = useState({
    skillName: '',
    level: '',
    yearsOfExperience: ''
  });

  useEffect(() => {
    loadSkills();
  }, []);

  const loadSkills = async () => {
    try {
      const response = await api.get('/skills');
      setSkills(response.data);
    } catch (error) {
      console.error('Load Skills Error:', error);
    }
  };

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleAddSkill = async () => {
    try {

      await api.post('/skills', {
        skillName: formData.skillName,
        level: formData.level,
        yearsOfExperience: Number(
          formData.yearsOfExperience
        )
      });

      setFormData({
        skillName: '',
        level: '',
        yearsOfExperience: ''
      });

      loadSkills();

    } catch (error) {
      console.error(
        'Add Skill Error:',
        error
      );
    }
  };

  const handleDeleteSkill = async (id) => {
    try {

      await api.delete(`/skills/${id}`);

      loadSkills();

    } catch (error) {
      console.error(
        'Delete Skill Error:',
        error
      );
    }
  };

  return (
    <div className="skills-container">

      <div className="skills-card">

        <h1 className="skills-title">
          Skills
        </h1>

        <div className="skill-form">

          <input
            type="text"
            name="skillName"
            placeholder="Skill Name"
            value={formData.skillName}
            onChange={handleChange}
          />

          <input
            type="text"
            name="level"
            placeholder="Level"
            value={formData.level}
            onChange={handleChange}
          />

          <input
            type="number"
            name="yearsOfExperience"
            placeholder="Years Of Experience"
            value={formData.yearsOfExperience}
            onChange={handleChange}
          />

        </div>

        <button
          className="add-skill-btn"
          onClick={handleAddSkill}
        >
          Add Skill
        </button>

        <div className="skills-grid">

          {skills.map((skill) => (

            <div
              key={skill.id}
              className="skill-item"
            >

              <h3>{skill.skillName}</h3>

              <p>
                Level: {skill.level}
              </p>

              <p>
                Experience:
                {' '}
                {skill.yearsOfExperience}
                {' '}
                years
              </p>

              <button
                className="delete-btn"
                onClick={() =>
                  handleDeleteSkill(skill.id)
                }
              >
                Delete
              </button>

            </div>

          ))}

        </div>

      </div>

    </div>
  );
}

export default Skills;