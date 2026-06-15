import React, { useEffect, useState } from 'react';
import './Education.css';
import api from '../services/api';


function Education() {

  const [educations, setEducations] = useState([]);

  const [formData, setFormData] = useState({
    collegeName: '',
    degree: '',
    fieldOfStudy: '',
    cgpa: '',
    startYear: '',
    endYear: ''
  });

  useEffect(() => {
    loadEducations();
  }, []);

  const loadEducations = async () => {

    try {

      const response =
        await api.get('/education');

      setEducations(response.data);

    } catch (error) {

      console.error(
        'Load Education Error:',
        error
      );
    }
  };

  const handleChange = (e) => {

    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleAddEducation = async () => {

    try {

      await api.post(
        '/education',
        {
          collegeName:
            formData.collegeName,
          degree:
            formData.degree,
          fieldOfStudy:
            formData.fieldOfStudy,
          cgpa:
            Number(formData.cgpa),
          startYear:
            Number(formData.startYear),
          endYear:
            Number(formData.endYear)
        }
      );

      setFormData({
        collegeName: '',
        degree: '',
        fieldOfStudy: '',
        cgpa: '',
        startYear: '',
        endYear: ''
      });

      loadEducations();

    } catch (error) {

      console.error(
        'Add Education Error:',
        error
      );
    }
  };

  const handleDeleteEducation = async (
    id
  ) => {

    try {

      await api.delete(
        `/education/${id}`
      );

      loadEducations();

    } catch (error) {

      console.error(
        'Delete Education Error:',
        error
      );
    }
  };

 return (

  <div className="education-container">

<div className="education-card">

  <h1 className="education-title">
    Education
  </h1>

  <div className="education-form">

    <input
      type="text"
      name="collegeName"
      placeholder="College Name"
      value={formData.collegeName}
      onChange={handleChange}
    />

    <input
      type="text"
      name="degree"
      placeholder="Degree"
      value={formData.degree}
      onChange={handleChange}
    />

    <input
      type="text"
      name="fieldOfStudy"
      placeholder="Field Of Study"
      value={formData.fieldOfStudy}
      onChange={handleChange}
    />

    <input
      type="number"
      name="cgpa"
      placeholder="CGPA"
      value={formData.cgpa}
      onChange={handleChange}
    />

    <input
      type="number"
      name="startYear"
      placeholder="Start Year"
      value={formData.startYear}
      onChange={handleChange}
    />

    <input
      type="number"
      name="endYear"
      placeholder="End Year"
      value={formData.endYear}
      onChange={handleChange}
    />

  </div>

  <button
    className="add-education-btn"
    onClick={handleAddEducation}
  >
    Add Education
  </button>

  <div className="education-grid">

    {educations.map((education) => (

      <div
        key={education.id}
        className="education-item"
      >

        <h3>
          {education.collegeName}
        </h3>

        <p>
          Degree: {education.degree}
        </p>

        <p>
          Field: {education.fieldOfStudy}
        </p>

        <p>
          CGPA: {education.cgpa}
        </p>

        <p>
          Duration:
          {' '}
          {education.startYear}
          {' - '}
          {education.endYear}
        </p>

        <button
          className="delete-btn"
          onClick={() =>
            handleDeleteEducation(
              education.id
            )
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

export default Education;