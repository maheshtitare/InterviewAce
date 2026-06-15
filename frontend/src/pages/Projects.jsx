import React, { useEffect, useState } from 'react';
import './Projects.css';
import api from '../services/api';

function Projects() {

  const [projects, setProjects] = useState([]);

  const [formData, setFormData] = useState({
    title: '',
    description: '',
    technologies: '',
    githubUrl: '',
    liveUrl: '',
    startDate: '',
    endDate: ''
  });

  useEffect(() => {
    loadProjects();
  }, []);

  const loadProjects = async () => {

    try {

      const response =
        await api.get('/projects');

      setProjects(response.data);

    } catch (error) {

      console.error(
        'Load Projects Error:',
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

  const handleAddProject = async () => {

    try {

      await api.post(
        '/projects',
        formData
      );

      setFormData({
        title: '',
        description: '',
        technologies: '',
        githubUrl: '',
        liveUrl: '',
        startDate: '',
        endDate: ''
      });

      loadProjects();

    } catch (error) {

      console.error(
        'Add Project Error:',
        error
      );
    }
  };

  const handleDeleteProject = async (
    id
  ) => {

    try {

      await api.delete(
        `/projects/${id}`
      );

      loadProjects();

    } catch (error) {

      console.error(
        'Delete Project Error:',
        error
      );
    }
  };

  return (

  <div className="projects-container">

<div className="projects-card">

  <h1 className="projects-title">
    Projects
  </h1>

  <div className="project-form">

    <input
      type="text"
      name="title"
      placeholder="Project Title"
      value={formData.title}
      onChange={handleChange}
    />

    <input
      type="text"
      name="description"
      placeholder="Description"
      value={formData.description}
      onChange={handleChange}
    />

    <input
      type="text"
      name="technologies"
      placeholder="Technologies"
      value={formData.technologies}
      onChange={handleChange}
    />

    <input
      type="text"
      name="githubUrl"
      placeholder="GitHub URL"
      value={formData.githubUrl}
      onChange={handleChange}
    />

    <input
      type="text"
      name="liveUrl"
      placeholder="Live URL"
      value={formData.liveUrl}
      onChange={handleChange}
    />

    <input
      type="date"
      name="startDate"
      value={formData.startDate}
      onChange={handleChange}
    />

    <input
      type="date"
      name="endDate"
      value={formData.endDate}
      onChange={handleChange}
    />

  </div>

  <button
    className="add-project-btn"
    onClick={handleAddProject}
  >
    Add Project
  </button>

  <div className="projects-grid">

    {projects.map((project) => (

      <div
        key={project.id}
        className="project-item"
      >

        <h3>{project.title}</h3>

        <p>{project.description}</p>

        <p>
          Technologies:
          {' '}
          {project.technologies}
        </p>

        <p>
          GitHub:
          {' '}
          {project.githubUrl}
        </p>

        <p>
          Live:
          {' '}
          {project.liveUrl}
        </p>

        <p>
          Duration:
          {' '}
          {project.startDate}
          {' - '}
          {project.endDate}
        </p>

        <button
          className="delete-btn"
          onClick={() =>
            handleDeleteProject(project.id)
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

export default Projects;